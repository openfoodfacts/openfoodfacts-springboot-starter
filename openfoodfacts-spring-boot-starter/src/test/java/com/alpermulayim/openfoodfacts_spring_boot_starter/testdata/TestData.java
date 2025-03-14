package com.alpermulayim.openfoodfacts_spring_boot_starter.testdata;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static final  String sampleProductCode = "5449000000996";
    public static final String emptyProductCode = "";
    public static final ProductRequest nullProductRequest = null;
    public static  final Language langEnglish = Language.ENGLISH;
    public static  final Language langTurkish = Language.TURKISH;
    public static  final Language langFrench = Language.FRENCH;

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
        return new OpenFoodFactsWebClientProperties("https://world.openfoodfacts.org","https://prices.openfoodfacts.org"
                ,"/api/v2/search",
                "/api/v2/product","/api/v3/prices");
    }
    public static OpenFoodFactsWebClientProperties nullWebClientProperties(){
        return new OpenFoodFactsWebClientProperties(null,null,
                null,null,null);
    }


    public static PriceRequest validPriceRequest(){
        return  PriceRequest.builder()
                .productCode(sampleProductCode())
                .locationId(2)
                .priceLt(100.0)
                .priceGt(200.0)
                .currency("EUR")
                .build();
    }

}
