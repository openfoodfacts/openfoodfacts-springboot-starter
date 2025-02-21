package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("OpenFoodFactsUrlUtils")
public class UriUtils {

    private final String productsPath = "/api/v2/product/";
    private final String searchPath = "/api/v2/search";

    private String getProductPath(String productCode){
        return productsPath + productCode + ".json";
    }

    //TODO : /api/v2/product/5449000000996.json?fields=product_name,nutriscore_grade,nutriscore_score,nutriscore_version,ingredients_text,quantity
    //TODO: create url for product
    public String productsUri(String productCode, List<ProductField> fields) throws OpenFoodFactsException{

        String path = getProductPath(productCode);

        if(productCode == null ||productCode.isEmpty()) {
            throw new OpenFoodFactsException("ProductCode could not be null");
        }

        if(fields == null){
            return UriComponentsBuilder.fromPath(path)
                    .build()
                    .toUriString();
        }

        String fieldsStr = fields.stream()
                .map(field->field.get())
                .collect(Collectors.joining(","));

        return UriComponentsBuilder.fromPath(path)
                .queryParam("fields",fieldsStr)
                .build()
                .toUriString();
    }

    public String productsUri(String productCode) throws OpenFoodFactsException {
        String path = getProductPath(productCode);
        if(productCode == null || productCode.isEmpty()){
            throw new OpenFoodFactsException("Product Code could not be null");
        }
        return UriComponentsBuilder.fromPath(path)
                .build()
                .toUriString();
    }

    public String searchUri(ProductSearchRequest searchRequest) throws InvocationTargetException, IllegalAccessException {

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
