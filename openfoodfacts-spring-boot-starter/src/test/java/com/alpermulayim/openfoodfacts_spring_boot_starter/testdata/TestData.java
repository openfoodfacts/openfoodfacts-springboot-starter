package com.alpermulayim.openfoodfacts_spring_boot_starter.testdata;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.ProductSaveResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestData {

    public static final  String sampleProductCode = "5449000000996";
    public static final String fakeProductCode = "484848484";
    public static final String emptyProductCode = "";
    public static final ProductRequest nullProductRequest = null;
    public static  final Language langEnglish = Language.ENGLISH;
    public static  final Language langTurkish = Language.TURKISH;
    public static  final Language langFrench = Language.FRENCH;

    public static String sampleProductCode(){
        return sampleProductCode;
    }
    public static String fakeProductCode(){ return  fakeProductCode; }

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
        return new OpenFoodFactsWebClientProperties(
                "https://world.openfoodfacts.org",
                "https://prices.openfoodfacts.org",
                "/api/v2/search",
                "/api/v2/product","/api/v3/prices",
                "/cgi/product_image_upload.pl",
                "cgi/product_jqm2.pl",
                Optional.of("abc"),Optional.of("abc"));
    }
    public static OpenFoodFactsWebClientProperties nullWebClientProperties(){
        return new OpenFoodFactsWebClientProperties(null,null,
                null,null,null,null,
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


    public static ProductImageUploadRequest validImageRequest(){
        byte[] fakeImageBytes = new byte[] {
                (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47, (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0D, (byte) 0x49, (byte) 0x48, (byte) 0x44, (byte) 0x52,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x64,
                (byte) 0x08, (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x80, (byte) 0x02,
                (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE6, (byte) 0x49, (byte) 0x44, (byte) 0x41,
                (byte) 0x54, (byte) 0x78, (byte) 0x9C, (byte) 0xED, (byte) 0xD0, (byte) 0x41, (byte) 0x09, (byte) 0x00,
                (byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE6, (byte) 0x49, (byte) 0x44, (byte) 0x41
        };

        // Create a mock MultipartFile for the fake image
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "image",               // Field name
                "fake_image.png",      // Original file name
                "image/png",           // Content type
                fakeImageBytes         // Image content as byte array
        );

        return ProductImageUploadRequest.builder()
                .file(mockMultipartFile)
                .language(Language.TURKISH)
                .facet("back")
                .productCode(sampleProductCode)
                .build();
    }

    public static ProductSaveRequest validProductSaveRequest(){
        return ProductSaveRequest.builder()
                .code(fakeProductCode)
                .brands("fakebrand")
                .productName("fakeproduct")
                .ingredientsText("fake ingredients")
                .comment("fake comment")
                .build();
    }

    public static ProductSaveRequest invalidNullCodeProductSaveRequest(){
        return ProductSaveRequest.builder()
                .brands("fakebrand")
                .productName("fakeproduct")
                .ingredientsText("fake ingredients")
                .comment("fake comment")
                .build();
    }

    public static ProductSaveRequest invalidNonNumericCodeProductSaveRequest(){
        return ProductSaveRequest.builder()
                .code("ABCDEFG")
                .brands("fakebrand")
                .productName("fakeproduct")
                .ingredientsText("fake ingredients")
                .comment("fake comment")
                .build();
    }

}
