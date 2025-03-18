package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsApi;
import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices.ProductPrice;
import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.ProductSaveResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import com.alpermulayim.openfoodfacts_starter_demo.dtos.DemoPrice;
import com.alpermulayim.openfoodfacts_starter_demo.dtos.DemoProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */

@Service
public class DemoOpenFoodFactsService {
    @Autowired
    OpenFoodFactsApi webClient;

    public OpenFoodFactsResponse request(){

       List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.NUTRISCORE_SCORE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.NUTRISCORE_DATA);
        fields.add(ProductField.NUTRITION_GRADES);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);
       return webClient.getProduct("5449000000996",fields);
    }

    public OpenFoodFactsResponse request(String productCode){
        return webClient.getProduct(productCode);
    }

    public OpenFoodFactsPageResponse search(ProductSearchRequest request)
            throws InvocationTargetException, IllegalAccessException {
        return webClient.searchProduct(request);
    }

    public OpenFoodFactsPageResponse searchCustom() throws InvocationTargetException, IllegalAccessException {
        List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);

        ProductSearchRequest request = ProductSearchRequest.builder()
                .brandsTags("nutella")
                .pageSize(3)
                .fields(fields)
                .build();
        return webClient.searchProduct(request);
    }

    public List<DemoProduct> searchDemoProducts() throws InvocationTargetException, IllegalAccessException {
        ProductSearchRequest request = searchRequest("eti",5);
        OpenFoodFactsPageResponse response = webClient.searchProduct(request);

        return response.products().stream().map((product -> new DemoProduct(
                product.code(), product.product_name(),product.imageUrl())))
                .collect(Collectors.toList());

    }

    public ProductSearchRequest searchRequest(String brand, Integer pageSize){
        List<ProductField> fields = new ArrayList<>();
        fields.add(ProductField.PRODUCT_NAME);
        fields.add(ProductField.CODE);
        fields.add(ProductField.IMAGE_URL);
        fields.add(ProductField.BRANDS);
        fields.add(ProductField.INGREDIENTS_TEXT);

        return ProductSearchRequest.builder()
                .brandsTags(brand)
                .pageSize(pageSize)
                .fields(fields)
                .build();
    }

    public OpenPriceFactsResponse getProductPrice(String productCode){
        return webClient.findPrice(productCode);
    }

    public OpenPriceFactsResponse searchPrice(PriceRequest request){
        return webClient.findPrice(request);
    }

    public OpenPriceFactsResponse searchPriceCustom(){
        PriceRequest priceRequest = PriceRequest.builder()
                .priceGt(2.0)
                .priceLt(5.0)
                .size(3)
                .build();
        return webClient.findPrice(priceRequest);
    }

    public List<DemoPrice> myDemoSearchMyProductPrices(){
        PriceRequest priceRequest = PriceRequest.builder()
                .priceGt(2.0)
                .priceLt(5.0)
                .build();

        OpenPriceFactsResponse priceResponse = webClient.findPrice(priceRequest);

        return priceResponse.prices().stream()
                .map(productPrice -> createDemoPrice(productPrice))
                .collect(Collectors.toList());
    }
    private DemoPrice createDemoPrice(ProductPrice productPrice){
        String productName = productPrice.productName();
        String productCode = productPrice.productCode();
        String brand = productPrice.product().brands();
        String country = productPrice.location().osmAddressCountryCode();
        Double price = productPrice.price();
        String currency = productPrice.currency();
        String store = productPrice.location().osmBrand();
        String imageUrl = productPrice.product().imageUrl();

        return new DemoPrice(productCode,productName,brand,store,country,currency,price,imageUrl);
    }

    public String uploadProductImage(String code, String lang, String facet, MultipartFile file) throws IOException {

        ProductImageUploadRequest request = ProductImageUploadRequest.builder()
                .productCode(code)
                .language(Language.fromCode(lang))
                .file(file)
                .facet(facet)
                .build();

        return  webClient.uploadProductImageBlocked(request);
    }

    public ProductSaveResponse saveProduct(ProductSaveRequest request){
        return webClient.saveProduct(request);
    }

    public ProductSaveResponse saveProductCustom(){
         ProductSaveRequest request = ProductSaveRequest.builder()
                    .code("484848484")
                    .brands("fakebrand-test")
                    .productName("fakeproduct-test")
                    .ingredientsText("fake,ingredients,test1,test2,test3")
                    .comment("fake,comment")
                    .build();
         return webClient.saveProduct(request);
    }

}

