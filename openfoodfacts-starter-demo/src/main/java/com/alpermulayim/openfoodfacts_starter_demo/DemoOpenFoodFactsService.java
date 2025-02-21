package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoOpenFoodFactsService {
    @Autowired
    OpenFoodFactsWebClient webClient;

    public OpenFoodFactsResponse request(){

       List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.NUTRISCORE_SCORE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.NUTRISCORE_DATA);
        fields.add(ProductField.NUTRITION_GRADES);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);
       return webClient.getProduct("5449000000996",fields);
    }

    public OpenFoodFactsResponse request(String productCode){
        return webClient.getProduct(productCode);
    }
}
