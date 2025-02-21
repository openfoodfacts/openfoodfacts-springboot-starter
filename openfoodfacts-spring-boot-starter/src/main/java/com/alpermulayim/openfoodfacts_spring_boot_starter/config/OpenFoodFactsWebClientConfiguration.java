package com.alpermulayim.openfoodfacts_spring_boot_starter.config;


import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class OpenFoodFactsWebClientConfiguration {

    //bean will export to caller projects
    @Bean("openFoodFactsWebClient")
    OpenFoodFactsWebClient openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient();
    }
}
