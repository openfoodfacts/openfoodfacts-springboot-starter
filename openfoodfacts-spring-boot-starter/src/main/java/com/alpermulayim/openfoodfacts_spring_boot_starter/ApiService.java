package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Component
public class ApiService {
    private RestClient restClient;

    public OpenFoodFactsResponse request(){

        restClient = RestClient.create("https://world.openfoodfacts.org");
       OpenFoodFactsResponse product = restClient.get()
                .uri("/api/v2/product/5449000000996.json?fields=product_name,nutriscore_grade,nutriscore_score,nutriscore_version,ingredients_text,quantity")
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
