package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.ProductSaveResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.AuthUtils;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.MultiPartUtils;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * OpenFoodFactsWebClient designed to simplify the integration of OpenFoodFacts REST APIs into Spring Boot applications.
 * Developers can easily connect to the OpenFoodFacts API without implement any complex RESTFul calls.
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */
@Service("openFoodFactsWebClient")
public class OpenFoodFactsWebClient implements OpenFoodFactsApi{
    private RestClient restClient;
    private RestClient pricesRestClient;
    private UriUtils uriUtils;
    private OpenFoodFactsWebClientProperties clientProperties;
    private WebClient webClient;
    private AuthUtils authUtils;
    private MultiPartUtils multiPartFormUtils;

    /**
     * Constructor for OpenFoodFactsWebClient.{@link OpenFoodFactsWebClientProperties}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param clientProperties Configuration properties for the API.
     */
    @Autowired
    public OpenFoodFactsWebClient(OpenFoodFactsWebClientProperties clientProperties) {
        this.clientProperties = clientProperties;
        this.uriUtils = new UriUtils(clientProperties);
        restClient = RestClient.create(clientProperties.baseUrl());
        pricesRestClient = RestClient.create(clientProperties.pricesBaseUrl());
        webClient = WebClient.create(clientProperties.baseUrl());
        authUtils = new AuthUtils(clientProperties);
        multiPartFormUtils = new MultiPartUtils();

        //TODO: add init properties print for caller developers
        System.out.println("OpenFoodFactsWebClient initialized with "+ clientProperties);
    }

    /**
     * Retrieve Product with setted fields.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @param fields the retrieved fields list  on response object.{@link ProductField}.
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    public OpenFoodFactsResponse getProduct(String productCode,List<ProductField> fields){
        if(productCode == null || productCode.isEmpty() ){
            throw new OpenFoodFactsException("Product  Number cannot be null for product request");
        }

      return restClient.get()
                .uri(uriUtils.productsUri(productCode, fields))
                .retrieve()
                .body(OpenFoodFactsResponse.class);
    }


    /**
     * Retrieves product details.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     */
    public OpenFoodFactsResponse getProduct(String productCode){
        return restClient.get()
                .uri(uriUtils.productsUri(productCode))
                .retrieve()
                .body(OpenFoodFactsResponse.class);
    }

    /**
     * Retrieves product details using a {@link ProductRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The request object containing productCode and fields.
     * @return The product details wrapped in an {@link OpenFoodFactsResponse}.
     * @throws OpenFoodFactsException if request or required fields are null.
     */
    public OpenFoodFactsResponse getProduct(ProductRequest request){
        if(request == null){
            throw new OpenFoodFactsException(" ProductRequest could not be null");
        }
        if(request.productFields() == null || request.productCode() == null || request.productCode().isEmpty()){
            throw new OpenFoodFactsException("ProductRequest fields or productCode could not be null ");
        }

        return restClient.get()
                .uri(uriUtils.productsUri(request.productCode(),request.productFields()))
                .retrieve()
                .body(OpenFoodFactsResponse.class);
    }

    /**
     * Searches for products based on a {@link ProductSearchRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The search request containing query parameters.
     * @return The paginated response wrapped in an {@link OpenFoodFactsPageResponse}.
     * @throws OpenFoodFactsException if request is null.
     */
    public OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException ,OpenFoodFactsException{

        if(request == null){
            throw new OpenFoodFactsException("ProductSearchRequest could not be null");
        }
       return restClient.get()
                .uri(uriUtils.searchUri(request))
                .retrieve()
                .body(OpenFoodFactsPageResponse.class);
    }

    /**
     * Finds price details for a product.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param productCode product code
     * @return The price details wrapped in an {@link OpenPriceFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    public OpenPriceFactsResponse findPrice(String productCode)  throws  OpenFoodFactsException{
        if(productCode == null ||productCode.isEmpty() ){
            throw new OpenFoodFactsException("Product code is null or empty");
        }
        return pricesRestClient.get()
                .uri(uriUtils.findPriceUri(productCode))
                .retrieve()
                .body(OpenPriceFactsResponse.class);
    }

    /**
     * Searches for product prices based on a {@link PriceRequest}.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param priceRequest product code
     * @return The price details wrapped in an {@link OpenPriceFactsResponse}.
     * @throws OpenFoodFactsException if the product code is null or empty.
     */
    public OpenPriceFactsResponse findPrice(PriceRequest priceRequest) throws OpenFoodFactsException{
        if(priceRequest == null ){
            throw new OpenFoodFactsException("PriceRequest is null");
        }
        return pricesRestClient.get()
                .uri(uriUtils.findPriceUri(priceRequest))
                .retrieve()
                .body(OpenPriceFactsResponse.class);
    }

    /**
     * Uploads a product image asynchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload {@link ProductImageUploadRequest}. request containing image data.
     * @return A {@link Mono} with the server response.
     */
    public Mono<ResponseEntity<String>> uploadProductImage(ProductImageUploadRequest request){
        return  uploadProductImageUnblocked(request);
    }

    /**
     * Uploads a product image asynchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload {@link ProductImageUploadRequest}.  request containing image data.
     * @return A {@link Mono} with the server response.
     */
    public Mono<ResponseEntity<String>> uploadProductImageUnblocked(ProductImageUploadRequest request) throws OpenFoodFactsException{

        return webClient.post()
                .uri(clientProperties.productImagePath())
                .headers(httpHeaders -> httpHeaders.addAll(authUtils.getAuthHeaders()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(multiPartFormUtils.getImageUploadMultiPartFormBody(request))
                .retrieve()
                .toEntity(String.class);
    }

    /**
     * Uploads a product image synchronously.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The image upload request containing image data. {@link ProductImageUploadRequest}.
     * @return server response.
     */
    public String uploadProductImageBlocked(ProductImageUploadRequest request) throws OpenFoodFactsException{

        return webClient.post()
                .uri(clientProperties.productImagePath())
                .headers(httpHeaders -> httpHeaders.addAll(authUtils.getAuthHeaders()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(multiPartFormUtils.getImageUploadMultiPartFormBody(request))
                .retrieve()
                .toEntity(String.class).block().getBody();
    }

    /**
     * Saves new product or updates product information.
     * @author Alper Mulayim  https://github.com/AlperMulayim
     * @param request The product save new product or update product request. {@link ProductSaveRequest}.
     * @return The saved product response wrapped in a {@link ProductSaveResponse}.
     * @throws OpenFoodFactsException if request is null or missing required fields.
     */
    public ProductSaveResponse saveProduct(ProductSaveRequest request) throws OpenFoodFactsException{
        if(request == null){
            throw new OpenFoodFactsException("Product Save Request cannot be null");
        }
        if(request.code() == null){
            throw new OpenFoodFactsException("ProductCode Save Request cannot be null");
        }

        return  restClient.post()
                .uri(clientProperties.productPathSave())
                .headers(httpHeaders -> httpHeaders.addAll(authUtils.getAuthHeaders()))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multiPartFormUtils.getProductSaveMultiPartFormBody(request))
                .retrieve().body(ProductSaveResponse.class);
    }

}
