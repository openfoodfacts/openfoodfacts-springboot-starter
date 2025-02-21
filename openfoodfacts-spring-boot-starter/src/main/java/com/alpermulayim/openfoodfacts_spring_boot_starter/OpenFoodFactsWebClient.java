package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.Product;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class OpenFoodFactsWebClient {
    private RestClient restClient;
    private UriUtils uriUtils;

    @Autowired
    public OpenFoodFactsWebClient() {
        this.uriUtils = new UriUtils();
        restClient = RestClient.create("https://world.openfoodfacts.org");
    }

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

       OpenFoodFactsResponse product = restClient.get()
                .uri(uriUtils.produtsUri("5449000000996",fields))
                .retrieve()
                .body(OpenFoodFactsResponse.class);


        System.out.println(product);
        System.out.println("--------------------------\n\n\n");
        return product;

//        String product1 = restClient.get()
//                .uri("/api/v2/product/5449000000996.json")
//                .retrieve()
//                .body(String.class);
//        System.out.println(product1);
    }

}
