package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceLocationRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alper Mulayim  <a href="https://github.com/AlperMulayim">...</a>
 */

@Component("openFoodFactsUrlUtils")
public class UriUtils {

    private String productsPath;
    private String searchPath;
    private String pricePath;

    @Autowired
    public UriUtils(OpenFoodFactsWebClientProperties properties) {
        productsPath = properties.productPath();
        searchPath = properties.searchPath();
        pricePath = properties.pricePath();
    }

    public String productsUri(String productCode, List<ProductField> fields, Optional<Language> lang){
        if(productCode == null ||productCode.isEmpty()) {
            throw new OpenFoodFactsException("ProductCode could not be null");
        }

        if(fields == null){
            return UriComponentsBuilder.fromPath(productsPath)
                    .pathSegment(productCode)
                    .build()
                    .toUriString();
        }

        Language language = lang.orElse(Language.ENGLISH);

        String fieldsStr = fields.stream()
                .map(ProductField::get)
                .collect(Collectors.joining(","));

        return UriComponentsBuilder.fromPath(productsPath)
                .pathSegment(productCode)
                .queryParam("fields",fieldsStr)
                .queryParam("lc",language.code())
                .build()
                .toUriString();
    }

    public String productsUri(String productCode, List<ProductField> fields) throws OpenFoodFactsException{
        return productsUri(productCode,fields,Optional.empty());
    }

    public String productsUri(String productCode) throws OpenFoodFactsException {

        if(productCode == null || productCode.isEmpty()){
            throw new OpenFoodFactsException("Product Code could not be null");
        }
        return UriComponentsBuilder.fromPath(productsPath)
                .pathSegment(productCode)
                .build()
                .toUriString();
    }

    public String searchUri(ProductSearchRequest searchRequest) throws InvocationTargetException, IllegalAccessException , OpenFoodFactsException{

        if(searchRequest == null){
            throw new OpenFoodFactsException("Search Request could not be null");
        }
        List<RecordComponent> components = List.of(searchRequest.getClass().getRecordComponents());
        UriComponentsBuilder uriBuilder =  UriComponentsBuilder.fromPath(searchPath);

        for(RecordComponent component : components){
            Object value = component.getAccessor().invoke(searchRequest);
            ProductSearchField field = ProductSearchField.fromFieldName(component.getName());
            if(value != null){
                uriBuilder.queryParam(field.get(),value);
            }
        }

        return uriBuilder.build().toUriString();
    }

    public String findPriceUri(String productCode){
        return UriComponentsBuilder.fromPath(pricePath)
                .queryParam("product_code", productCode)
                .build()
                .toUriString();
    }

    public String findPriceUri(PriceRequest priceRequest) throws OpenFoodFactsException{

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(pricePath);
        List<RecordComponent> recordComponents = List.of(priceRequest.getClass().getRecordComponents());

        for(RecordComponent component :recordComponents){
            try {
                Object value = component.getAccessor().invoke(priceRequest);
                JsonProperty jsonProperty = component.getAccessor().getAnnotation(JsonProperty.class);

                if(value != null){
                    uriBuilder.queryParam(jsonProperty.value(),value);
                }

            } catch (Exception e) {
                throw new OpenFoodFactsException("[OpenFoodFactsSpringBootStarter] Price request could not parsed.");
            }
        }
        return uriBuilder.build().toUriString();
    }


    public String findPriceLocationUri(PriceLocationRequest priceLocationRequest) throws OpenFoodFactsException{

        if(priceLocationRequest == null){
            throw new OpenFoodFactsException("PriceLocationRequest cannot be null");
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(pricePath);
        List<RecordComponent> recordComponents = List.of(priceLocationRequest.getClass().getRecordComponents());

        for(RecordComponent component :recordComponents){
            try {
                Object value = component.getAccessor().invoke(priceLocationRequest);
                JsonProperty jsonProperty = component.getAccessor().getAnnotation(JsonProperty.class);

                if(value != null){
                    uriBuilder.queryParam(jsonProperty.value(),value);
                }

            } catch (Exception e) {
                throw new OpenFoodFactsException("[OpenFoodFactsSpringBootStarter] PriceLocation request could not parsed.");
            }
        }
        return uriBuilder.build().toUriString();
    }

}
