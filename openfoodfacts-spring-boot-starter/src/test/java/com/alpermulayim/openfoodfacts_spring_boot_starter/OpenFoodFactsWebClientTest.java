package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientConfiguration;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
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
            ))
            .withPropertyValues(
                    "openfoodfacts.base-url=https://world.openfoodfacts.org",
                    "openfoodfacts.prices-base-url=https://prices.openfoodfacts.org",
                    "openfoodfacts.search-path=/api/v2/search",
                    "openfoodfacts.product-path=/api/v2/product",
                    "openfoodfacts.prices-path=/api/v3/prices",
                    "openfoodfacts.product-image-path=/cgi/product_image_upload.pl"
            );

    @BeforeEach
    void init(){
        contextRunner.run(context->{
            webClient = context.getBean(OpenFoodFactsWebClient.class);
        });
    }

    @Test
    void whenWebClientCreatedBeanNameOpenFoodFactsWebClient(){
        contextRunner.run(context->{
            assertTrue(context.containsBean("openFoodFactsWebClient"));
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

    @Test
    void whenWebClientPropertiesBaseUrlShouldSameOnProperties(){
        contextRunner.run(context -> {
            String baseUrl = context.getEnvironment().getProperty("openfoodfacts.base-url");
            assertEquals(TestData.defaultWebClientProperties().baseUrl(),baseUrl);
        });
    }

    @Test
    void whenWebClientPropertiesSearchPathShouldSameOnProperties(){
        contextRunner.run(context -> {
            String searchPath = context.getEnvironment().getProperty("openfoodfacts.search-path");
            assertEquals(TestData.defaultWebClientProperties().searchPath(),searchPath);
        });
    }

    @Test
    void whenWebClientPropertiesProductPathShouldSameOnProperties(){
        contextRunner.run(context -> {
            String productPath = context.getEnvironment().getProperty("openfoodfacts.product-path");
            assertEquals(TestData.defaultWebClientProperties().productPath(),productPath);
        });
    }

    @Test
    void whenWebClientPropertiesPricePathSettedShouldSameOnProperties(){
        contextRunner.run(context -> {
            String pricePath = context.getEnvironment().getProperty("openfoodfacts.prices-path");
            assertEquals(TestData.defaultWebClientProperties().pricePath(),pricePath);
        });
    }

    @Test
    void whenWebClientPropertiesPriceBaseUrlSettedShouldSameOnProperties(){
        contextRunner.run(context -> {
            String priceUrl = context.getEnvironment().getProperty("openfoodfacts.prices-base-url");
            assertEquals(TestData.defaultWebClientProperties().pricesBaseUrl(),priceUrl);
        });
    }

    @Test
    void whenWebClientCalledForProductPriceWithEmptyProductCodeWillThrowsException(){
        contextRunner.run(context -> {
           assertThrows(OpenFoodFactsException.class,()->webClient.findPrice(""));
        });
    }

    @Test
    void whenWebClientCalledForProductPriceWithNullRequestWillThrowsException(){
        contextRunner.run(context -> {
            PriceRequest request = null;
            assertThrows(OpenFoodFactsException.class,()->webClient.findPrice(request));
        });
    }

    @Test
    void whenWebClientCalledForProductPriceWithNullRequestWillThrowsExceptio2(){
        contextRunner.run(context -> {
            assertThrows(OpenFoodFactsException.class,()->webClient.uploadProductImage(TestData.validImageRequest()));
        });
    }

}