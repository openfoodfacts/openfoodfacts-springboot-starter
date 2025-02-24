package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alper Mulayim  <a href="https://github.com/AlperMulayim">...</a>
 */

@Component("openFoodFactsUrlUtils")
public class UriUtils {

    private String productsPath;
    private String searchPath;


    @Autowired
    public UriUtils(OpenFoodFactsWebClientProperties properties) {
        productsPath = properties.productPath();
        searchPath = properties.searchPath();
    }


    public String productsUri(String productCode, List<ProductField> fields) throws OpenFoodFactsException{

        if(productCode == null ||productCode.isEmpty()) {
            throw new OpenFoodFactsException("ProductCode could not be null");
        }

        if(fields == null){
            return UriComponentsBuilder.fromPath(productsPath)
                    .pathSegment(productCode)
                    .build()
                    .toUriString();
        }

        String fieldsStr = fields.stream()
                .map(ProductField::get)
                .collect(Collectors.joining(","));

        return UriComponentsBuilder.fromPath(productsPath)
                .pathSegment(productCode)
                .queryParam("fields",fieldsStr)
                .build()
                .toUriString();
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

}
