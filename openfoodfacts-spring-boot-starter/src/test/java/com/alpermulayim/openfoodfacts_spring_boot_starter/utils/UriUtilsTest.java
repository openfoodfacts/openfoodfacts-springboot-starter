package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientConfiguration;
import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class UriUtilsTest {

    private UriUtils uriUtils;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                    OpenFoodFactsWebClientConfiguration.class,
                   UriUtils.class
            ))
            .withPropertyValues(
                    "openfoodfacts.base-url=https://world.openfoodfacts.org",
                    "openfoodfacts.prices-base-url=https://prices.openfoodfacts.org",
                    "openfoodfacts.search-path=/api/v2/search",
                    "openfoodfacts.product-path=/api/v2/product",
                    "openfoodfacts.prices-path=/api/v3/prices"
            );


    @BeforeEach
    void initUriUtils(){
         contextRunner.run((context -> {
            uriUtils = context.getBean(UriUtils.class);
        }));
    }

    @Test
    void whenProductsUriNullProductCodeWillThrowsException(){
        assertThrows(OpenFoodFactsException.class,()->uriUtils.productsUri(null));
    }

    @Test
    void whenProductsUriCalledWithEmptyProductCodeWillThrowException(){
        assertThrows(OpenFoodFactsException.class,()->uriUtils.productsUri(""));
    }

    @Test
    void whenProductsUriCalledWithProductCodeAndNullFieldsDoesNotContainFields(){
        String uri = uriUtils.productsUri(TestData.sampleProductCode(),null);
        assertFalse(uri.contains("fields"));
    }

    @Test
    void whenProductSearchRequestBuildingWithBrandTagsPageAndPageSizeUrlStringWillHaveBuildedData() throws InvocationTargetException, IllegalAccessException {
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
    void whenProductSearchRequestBuildedWithFieldsListEnumURLContainsEnumsData() throws InvocationTargetException, IllegalAccessException {
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
    void whenProductSearchRequestBuildedFieldsNullNotIncludedToUrl() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .build();

        String url = uriUtils.searchUri(request);
        assertFalse(url.contains("code"));
        assertFalse(url.contains("fields"));
        assertFalse(url.contains("product_name"));
    }

    @Test
    void whenUrlCreatedForSearchShouldIncludeSearchPath() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nestle")
                .build();

        String url = uriUtils.searchUri(request);
        assertTrue(url.contains("search"));
    }

    @Test
    void whenUrlCreatedForProductShouldIncludeSearchPath(){
        String url = uriUtils.productsUri(TestData.sampleProductCode);
        assertTrue(url.contains("product"));
    }

    @Test
    void whenUrlCreatedWithNullSearchRequestWillThrowException(){
        assertThrows(OpenFoodFactsException.class,()->uriUtils.searchUri(null));
    }

    @Test
    void whenProductUrlCreatedShouldIncludeProductCode(){
       assertTrue(uriUtils.productsUri(TestData.sampleProductCode()).contains(TestData.sampleProductCode()));
    }

    @Test
    void whenProductUrlCreatedShouldIncludeProductCodeAndFields(){
        assertTrue(uriUtils.productsUri(TestData.sampleProductCode(),TestData.sampleFields()).contains(TestData.sampleProductCode()));
        assertTrue(uriUtils.productsUri(TestData.sampleProductCode(),TestData.sampleFields()).contains("fields"));
    }

    @Test
    void  whenProductFindPriceUriInvokedUriStringShouldIncludeGivenProductCode(){
        assertTrue(uriUtils.findPriceUri("1234").contains("1234"));
    }

    @Test
    void  whenProductFindPriceUriInvokedUriStringShouldIncludeProductQueryParam(){
        assertTrue(uriUtils.findPriceUri(TestData.sampleProductCode()).contains(TestData.sampleProductCode()));
    }

    @Test
    void  whenProductFindPriceUriCalledWithRequestShouldIncludRequestData() throws Exception {
        PriceRequest request = TestData.validPriceRequest();
        String uri = uriUtils.findPriceUri(request);
        List<RecordComponent> recordComponents = List.of(request.getClass().getRecordComponents());

        for(RecordComponent component: recordComponents){
            Object value = component.getAccessor().invoke(request);
            JsonProperty jsonProperty = component.getAccessor().getAnnotation(JsonProperty.class);
            if(value != null) {
                assertTrue(uri.contains(jsonProperty.value()));
                assertTrue(uri.contains(String.valueOf(value)));
            }
        }
    }

    @Test
    void whenProductsUrlCreatedWithLanguageUrlWillIncludeLanguageCode(){
       String url = uriUtils.productsUri(TestData.sampleProductCode,
               TestData.sampleFields(),
               Optional.of(TestData.langTurkish));
       assertTrue(url.contains("lc="+TestData.langTurkish.code()));
    }

    @Test
    void whenProductsUrlCreatedWithEmptyLangDefaultLangEnglishIncluded(){
        String url = uriUtils.productsUri(TestData.sampleProductCode,
                TestData.sampleFields(),
                Optional.empty());
        assertTrue(url.contains("lc="+TestData.langEnglish.code()));
    }

}