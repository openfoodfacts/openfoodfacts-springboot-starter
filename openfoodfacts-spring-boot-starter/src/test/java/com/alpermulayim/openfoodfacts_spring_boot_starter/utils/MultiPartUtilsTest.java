package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.testdata.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void whenMultipartCreatedWithNullInvalidProductCode(){
        assertThrows(OpenFoodFactsException.class,()-> multiPartUtils.getProductSaveMultiPartFormBody(TestData.invalidNullCodeProductSaveRequest()));
    }

    @Test
    public void whenMultipartCreatedWithNonNumericInvalidProductCode(){
        assertThrows(OpenFoodFactsException.class,()-> multiPartUtils.getProductSaveMultiPartFormBody(TestData.invalidNonNumericCodeProductSaveRequest()));
    }

}