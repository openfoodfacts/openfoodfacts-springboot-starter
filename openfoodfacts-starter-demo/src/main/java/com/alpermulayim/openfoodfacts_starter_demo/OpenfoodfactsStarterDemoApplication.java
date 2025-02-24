package com.alpermulayim.openfoodfacts_starter_demo;


import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OpenFoodFactsWebClientProperties.class)
public class OpenfoodfactsStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfoodfactsStarterDemoApplication.class, args);
    }

}