package com.alpermulayim.openfoodfacts_spring_boot_starter.testdata;

import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static final  String sampleProductCode = "5449000000996";
    public static List<ProductField> sampleFields = new ArrayList<>();

    private void initSampleFields() {
        sampleFields.add(ProductField.PRODUCT_NAME);
        sampleFields.add(ProductField.CODE);
        sampleFields.add(ProductField.NUTRISCORE_SCORE);
        sampleFields.add(ProductField.IMAGE_URL);
        sampleFields.add(ProductField.NUTRISCORE_DATA);
        sampleFields.add(ProductField.NUTRITION_GRADES);
        sampleFields.add(ProductField.BRANDS);
        sampleFields.add(ProductField.INGREDIENTS_TEXT);
    }

    public static String sampleProductCode(){
        return sampleProductCode;
    }

    public static List<ProductField> sampleFields(){
        return sampleFields;
    }
}
