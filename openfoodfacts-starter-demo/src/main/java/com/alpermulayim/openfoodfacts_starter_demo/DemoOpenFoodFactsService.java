package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoOpenFoodFactsService {
    @Autowired
    OpenFoodFactsWebClient webClient;

    public OpenFoodFactsResponse request(){
       return webClient.request();
    }
}
