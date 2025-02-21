package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */

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

    public OpenFoodFactsPageResponse search(ProductSearchRequest request)
            throws InvocationTargetException, IllegalAccessException {
        return webClient.searchProduct(request);
    }
}
