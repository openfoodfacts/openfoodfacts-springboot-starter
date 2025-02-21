package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientConfiguration;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.UriUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.*;

public class OpenFoodFactsWebClientTest {

    private OpenFoodFactsWebClient webClient;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                    OpenFoodFactsWebClientConfiguration.class,
                    RestClientAutoConfiguration.class,
                    UriUtils.class
            ));


    @BeforeEach
    public void init(){
        contextRunner.run((context)->{
            webClient = context.getBean(OpenFoodFactsWebClient.class);
        });
    }

    @Test
    public void whenWebCleintCalledWithNullProductCodeThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(null,TestData.sampleFields()));
    }
}