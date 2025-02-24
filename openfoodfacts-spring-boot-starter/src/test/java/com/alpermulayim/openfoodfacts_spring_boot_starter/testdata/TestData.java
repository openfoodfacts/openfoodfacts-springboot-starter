package com.alpermulayim.openfoodfacts_spring_boot_starter.testdata;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static final  String sampleProductCode = "5449000000996";
    public static final String emptyProductCode = "";
    public static final ProductRequest nullProductRequest = null;

    public static String sampleProductCode(){
        return sampleProductCode;
    }

    public static List<ProductField> sampleFields(){
        List<ProductField> sampleFields = new ArrayList<>();
        sampleFields.add(ProductField.PRODUCT_NAME);
        sampleFields.add(ProductField.CODE);
        sampleFields.add(ProductField.NUTRISCORE_SCORE);
        sampleFields.add(ProductField.IMAGE_URL);
        sampleFields.add(ProductField.NUTRISCORE_DATA);
        sampleFields.add(ProductField.NUTRITION_GRADES);
        sampleFields.add(ProductField.BRANDS);
        sampleFields.add(ProductField.INGREDIENTS_TEXT);
        return sampleFields;
    }

    public static ProductRequest emptyProductRequest(){
        return new ProductRequest("",null);
    }

    public static ProductRequest productRequestProductCodeNull(){
        return new ProductRequest(null,sampleFields());
    }

    public static ProductRequest productRequestFieldsNull(){
        return new ProductRequest(sampleProductCode(),null);
    }
    public static OpenFoodFactsWebClientProperties defaultWebClientProperties(){
        return new OpenFoodFactsWebClientProperties("https://world.openfoodfacts.org","/api/v2/search","/api/v2/product","/","/.json");
    }
    public static OpenFoodFactsWebClientProperties nullWebClientProperties(){
        return new OpenFoodFactsWebClientProperties(null,null,null,null,null);
    }
}
