package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.lang.reflect.InvocationTargetException;
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

    @Test
    public void whenProductSearchRequestBuildingWithBrandTagsPageAndPageSizeUrlStringWillHaveBuildedData() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .page(1)
                .pageSize(2)
                .build();

       String url =  uriUtils.searchUri(request);
       assertTrue(url.contains("page"));
       assertTrue(url.contains("page_size"));
       assertTrue(url.contains("brands"));
    }

    @Test
    public void whenProductSearchRequestBuildedWithFieldsListEnumURLContainsEnumsData() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .fields(TestData.sampleFields())
                .build();

        String url = uriUtils.searchUri(request);

        for(ProductField field : TestData.sampleFields()){
            assertTrue(url.contains(field.get()));
        }
    }

    @Test
    public void whenProductSearchRequestBuildedFieldsNullNotIncludedToUrl() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .build();

        String url = uriUtils.searchUri(request);
        assertFalse(url.contains("code"));
        assertFalse(url.contains("fields"));
        assertFalse(url.contains("product_name"));
    }

    @Test
    public void whenUrlCreatedForSearchShouldIncludeSearchPath() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .build();

        String url = uriUtils.searchUri(request);
        assertTrue(url.contains("search"));
    }

    @Test
    public void whenUrlCreatedForProductShouldIncludeSearchPath() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .build();

        String url = uriUtils.productsUri(TestData.sampleProductCode);
        assertTrue(url.contains("product"));
    }

    @Test
    public void whenUrlCreatedWithNullSearchRequestWillThrowException() throws InvocationTargetException, IllegalAccessException {
        assertThrows(OpenFoodFactsException.class,()->uriUtils.searchUri(null));
    }
}