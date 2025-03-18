package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ImageFacet;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ImageFormat;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MultiPartUtilsTest {
    public MultiPartUtils multiPartUtils;

    @BeforeEach
    public void  init(){
        multiPartUtils = new MultiPartUtils();
    }

    @Test
    public void whenMultipartCreatedWithNullRequestWillThrowException() {
        assertThrows(OpenFoodFactsException.class,()-> multiPartUtils.getProductSaveMultiPartFormBody(null));
    }

    @Test
    public void whenMultipartCreatedWithNullInvalidProductCodeThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> multiPartUtils.getProductSaveMultiPartFormBody(TestData.invalidNullCodeProductSaveRequest()));
    }

    @Test
    public void whenMultipartCreatedWithNonNumericInvalidProductCodeThrowsException(){
        assertThrows(OpenFoodFactsException.class,()-> multiPartUtils.getProductSaveMultiPartFormBody(TestData.invalidNonNumericCodeProductSaveRequest()));
    }

    @Test
    public void whenMultipartCreatedProductRequestAllKeysShouldWillAreProductRequestFields(){

        ProductSaveRequest validRequest = TestData.validProductSaveRequest();
        List<String> requestFields = new ArrayList<>();
        MultiValueMap<String, HttpEntity<?>> multiValueMap = multiPartUtils.getProductSaveMultiPartFormBody(validRequest);

        List<RecordComponent> recordComponents = List.of(validRequest.getClass().getRecordComponents());

        for (RecordComponent component : recordComponents) {
            try {
                JsonProperty jsonProperty = component.getAccessor().getAnnotation(JsonProperty.class);
                requestFields.add(jsonProperty.value());

            } catch (Exception e) {
                throw new OpenFoodFactsException("ProductSaveRequest are not able to parsed");
            }
        }

        List<String> mapKeys = multiValueMap.entrySet().stream()
                .map(en->en.getKey())
                .collect(Collectors.toList());

        assertTrue(requestFields.containsAll(mapKeys));
     }

    @Test
    public void whenMultipartImageUploadCreatedValueMapKeysHaveCodeImageFieldAndImage(){
        ProductImageUploadRequest imgRequest = TestData.validImageRequest();
        MultiValueMap<String,HttpEntity<?>> multiValueMap = multiPartUtils.getImageUploadMultiPartFormBody(imgRequest);

        List<String> keys = multiValueMap.entrySet().stream()
                .map(en->en.getKey())
                .collect(Collectors.toList());

        String imgKey = "imgupload_" + imgRequest.facet() +"_" + imgRequest.language().code();
        assertTrue(keys.contains("code"));
        assertTrue(keys.contains("imagefield"));
        assertTrue(keys.contains(imgKey));
    }

    @Test
    public void whenImageFormatConvertedInvalidStringEnumThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()-> ImageFormat.from("test"));
    }

    @Test
    public void whenImageFacetInvalidStringEnumThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()-> ImageFacet.from("test"));
    }

    @Test
    public void whenImageFacetValidStringEnumWillValid(){
        assertEquals(ImageFacet.INGREDIENTS,ImageFacet.from("ingredients"));
    }

    @Test
    public void whenImageFormatValidStringEnumWillValid(){
        assertEquals(ImageFormat.PNG,ImageFormat.from("png"));
    }
}