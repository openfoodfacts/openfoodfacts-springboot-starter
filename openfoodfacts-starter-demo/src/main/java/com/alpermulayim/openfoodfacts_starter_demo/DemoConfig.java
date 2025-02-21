package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    OpenFoodFactsWebClient openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient();
    }
}
