package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import static org.junit.jupiter.api.Assertions.*;


public class UriUtilsTest {

    private UriUtils uriUtils;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                   UriUtils.class
            ));


    @BeforeEach
    public void initUriUtils(){
         contextRunner.run((context -> {
            uriUtils = context.getBean(UriUtils.class);
        }));
    }

    @Test
    public void whenProductsUriNullProductCodeWillThrowsException(){
        assertThrows(OpenFoodFactsException.class,()->uriUtils.productsUri(null));
    }

    @Test
    public void whenProductsUriCalledWithEmptyProductCodeWillThrowException(){
        assertThrows(OpenFoodFactsException.class,()->uriUtils.productsUri(""));
    }

    @Test
    public void whenProductsUriCalledWithProductCodeAndNullFieldsDoesNotContainFields(){
        String uri = uriUtils.productsUri(TestData.sampleProductCode(),null);
        assertFalse(uri.contains("fields"));
    }
}