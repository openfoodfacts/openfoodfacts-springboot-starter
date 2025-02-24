package com.alpermulayim.openfoodfacts_spring_boot_starter.config;


import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpenFoodFactsWebClientProperties.class)
@ConditionalOnProperty(name = "openfoodfacts", havingValue = "true", matchIfMissing = true)
public class OpenFoodFactsWebClientConfiguration {

    private OpenFoodFactsWebClientProperties properties;

    @Autowired
    public OpenFoodFactsWebClientConfiguration(OpenFoodFactsWebClientProperties properties) {
        this.properties = properties;
    }

    //bean will export to caller projects
    @Bean("openFoodFactsWebClient")
    OpenFoodFactsWebClient openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient(properties);
    }
}
