package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices.OpenPricesProductLocation;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceLocationRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.ProductSaveResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceLocationsPagedResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * OpenFoodFactsApi implemented designed to simplify the integration of OpenFoodFacts REST APIs into Spring Boot applications.
 * Developers can easily connect to the OpenFoodFacts API without implement any complex RESTFul calls.
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */
public interface OpenFoodFactsApi {

    /**
     * Retrieve Product with setted fields.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @param fields the retrieved fields list  on response object.{@link ProductField}.
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    OpenFoodFactsResponse getProduct(String productCode, List<ProductField> fields);

    /**
     * Retrieves product details.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     */
    OpenFoodFactsResponse getProduct(String productCode);

    /**
     * Retrieves product details using a {@link ProductRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The request object containing productCode and fields.
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     * @throws OpenFoodFactsException if request or required fields are null.
     */
    OpenFoodFactsResponse getProduct(ProductRequest request);

    /**
     * Searches for products based on a {@link ProductSearchRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The search request containing query parameters.
     * @return The paginated response wrapped in an {@link OpenFoodFactsPageResponse}.
     * @throws OpenFoodFactsException if request is null.
     */
    OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException , OpenFoodFactsException;

    /**
     * Searches for product prices based on a {@link PriceRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param priceRequest product code
     * @return The price details wrapped in an {@link OpenPriceFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    OpenPriceFactsResponse findPrice(PriceRequest priceRequest);

    /**
     * Finds price details for a product.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @return The price details wrapped in an {@link OpenPriceFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    OpenPriceFactsResponse findPrice(String productCode);

    /**
     * Uploads a product image asynchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload {@link ProductImageUploadRequest}. request containing image data.
     * @return A {@link Mono} with the server response.
     */
    Mono<ResponseEntity<String>> uploadProductImage(ProductImageUploadRequest request);

    /**
     * Uploads a product image synchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload request containing image data. {@link ProductImageUploadRequest}.
     * @return server response.
     */
    String uploadProductImageBlocked(ProductImageUploadRequest request) throws OpenFoodFactsException;

    /**
     * Uploads a product image asynchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload {@link ProductImageUploadRequest}.  request containing image data.
     * @return A {@link Mono} with the server response.
     */
    Mono<ResponseEntity<String>> uploadProductImageUnblocked(ProductImageUploadRequest request) throws OpenFoodFactsException;

    /**
     * Saves new product or updates product information.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The product save new product or update product request. {@link ProductSaveRequest}.
     * @return The saved product response wrapped in a {@link ProductSaveResponse}.
     * @throws OpenFoodFactsException if request is null or missing required fields.
     */
    ProductSaveResponse saveProduct(ProductSaveRequest request);

    /**
     * Find the Price Location
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param locationRequest
     * @return {@link OpenPriceLocationsPagedResponse}.
     */
    OpenPriceLocationsPagedResponse findPriceLocation(PriceLocationRequest locationRequest);

}
