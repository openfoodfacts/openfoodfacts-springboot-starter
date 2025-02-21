package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UriUtilsTest {

    private UriUtils uriUtils;
    private final  String sampleProductCode = "5449000000996";
    private List<ProductField> fields;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                   UriUtils.class
            ));


    @BeforeEach
    public void initUriUtils(){
         contextRunner.run((context -> {
            uriUtils = context.getBean(UriUtils.class);
        }));

        this.fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.NUTRISCORE_SCORE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.NUTRISCORE_DATA);
        fields.add(ProductField.NUTRITION_GRADES);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);
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
        String uri = uriUtils.productsUri(sampleProductCode,null);
        assertFalse(uri.contains("fields"));
    }
}