package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.ProductSaveResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OpenFoodFactsApi {
    OpenFoodFactsResponse getProduct(String productCode, List<ProductField> fields);
    OpenFoodFactsResponse getProduct(String productCode);
    OpenFoodFactsResponse getProduct(ProductRequest request);
    OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException , OpenFoodFactsException;
    OpenPriceFactsResponse findPrice(PriceRequest priceRequest);
    OpenPriceFactsResponse findPrice(String productCode);
    ProductSaveResponse saveProduct(ProductSaveRequest request);
    Mono<ResponseEntity<String>> uploadProductImage(ProductImageUploadRequest request);
    String uploadProductImageBlocked(ProductImageUploadRequest request) throws OpenFoodFactsException;
    Mono<ResponseEntity<String>> uploadProductImageUnblocked(ProductImageUploadRequest request) throws OpenFoodFactsException;
}
