package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientConfiguration;
import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */

@Configuration
public class DemoConfig {

    OpenFoodFactsWebClientProperties properties;

    @Autowired
    public DemoConfig(OpenFoodFactsWebClientProperties properties) {
        this.properties = properties;
    }


    @Bean
    OpenFoodFactsWebClient openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient(properties);
    }
}
