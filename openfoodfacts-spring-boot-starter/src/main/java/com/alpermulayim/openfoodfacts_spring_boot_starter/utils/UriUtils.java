package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component("OpenFoodFactsUrlUtils")
public class UriUtils {

    //TODO : /api/v2/product/5449000000996.json?fields=product_name,nutriscore_grade,nutriscore_score,nutriscore_version,ingredients_text,quantity
    //TODO: create url for product
    public String produtsUri(String productNumber, List<ProductField> fields){
        String path = "/api/v2/product/" + productNumber + ".json";

        String fieldsStr = fields.stream()
                .map(field->field.get())
                .collect(Collectors.joining(","));


        return UriComponentsBuilder.fromPath(path)
                .queryParam("fields",fieldsStr)
                .build()
                .toUriString();
    }
}
