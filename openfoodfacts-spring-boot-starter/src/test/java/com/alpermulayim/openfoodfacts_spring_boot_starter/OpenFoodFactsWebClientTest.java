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
    void init(){
        contextRunner.run(context->{
            webClient = context.getBean(OpenFoodFactsWebClient.class);
        });
    }

    @Test
    void whenWebClientCreatedBeanNameOpenFoodFactsWebClient(){
        contextRunner.run(context->{
            assertTrue(context.containsBean("OpenFoodFactsWebClient"));
        });
    }

    @Test
    void whenWebClientCalledWithNullProductCodeThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(null,TestData.sampleFields()));
    }

    @Test
    void whenWebClientCalledWithEmptyProductCodeThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(TestData.emptyProductCode,TestData.sampleFields()));
    }

    @Test
    void whenWebClientCalledWithEmptyProductRequestThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(TestData.emptyProductRequest()));
    }

    @Test
    void whenWebClientCalledWithNullProductRequestThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(TestData.nullProductRequest));
    }

    @Test
    void whenWebClientCalledWithProductRequestWithNullProductThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(TestData.productRequestProductCodeNull()));
    }

    @Test
    void whenWebClientCalledWithProductRequestWithNullFieldsThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.getProduct(TestData.productRequestFieldsNull()));
    }
    @Test
    void whenWebClientCalledWithSearchRequestWithNullFieldsThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> webClient.searchProduct(null));
    }
}