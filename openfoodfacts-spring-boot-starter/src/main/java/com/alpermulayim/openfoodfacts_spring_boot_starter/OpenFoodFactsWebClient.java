package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */

@Service("openFoodFactsWebClient")
public class OpenFoodFactsWebClient {
    private RestClient restClient;
    private RestClient pricesRestClient;
    private UriUtils uriUtils;
    private OpenFoodFactsWebClientProperties clientProperties;
    private WebClient webClient;

    @Autowired
    public OpenFoodFactsWebClient(OpenFoodFactsWebClientProperties clientProperties) {
        this.clientProperties = clientProperties;
        this.uriUtils = new UriUtils(clientProperties);
        restClient = RestClient.create(clientProperties.baseUrl());
        pricesRestClient = RestClient.create(clientProperties.pricesBaseUrl());
        webClient = WebClient.create(clientProperties.baseUrl());

        //TODO: add init properties print for caller developers
        System.out.println("OpenFoodFactsWebClient initialized with "+ clientProperties);
    }

    public OpenFoodFactsResponse getProduct(String productCode,List<ProductField> fields){
        if(productCode == null || productCode.isEmpty() ){
            throw new OpenFoodFactsException("Product  Number cannot be null for product request");
        }

      return restClient.get()
                .uri(uriUtils.productsUri(productCode, fields))
                .retrieve()
                .body(OpenFoodFactsResponse.class);
    }

    public OpenFoodFactsResponse getProduct(String productCode){
        return restClient.get()
                .uri(uriUtils.productsUri(productCode))
                .retrieve()
                .body(OpenFoodFactsResponse.class);
    }

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

    public OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException ,OpenFoodFactsException{

        if(request == null){
            throw new OpenFoodFactsException("ProductSearchRequest could not be null");
        }
       return restClient.get()
                .uri(uriUtils.searchUri(request))
                .retrieve()
                .body(OpenFoodFactsPageResponse.class);
    }

    public OpenPriceFactsResponse findPrice(String productCode)  throws  OpenFoodFactsException{
        if(productCode == null ||productCode.isEmpty() ){
            throw new OpenFoodFactsException("Product code is null or empty");
        }
        return pricesRestClient.get()
                .uri(uriUtils.findPriceUri(productCode))
                .retrieve()
                .body(OpenPriceFactsResponse.class);
    }

    public OpenPriceFactsResponse findPrice(PriceRequest priceRequest) throws OpenFoodFactsException{
        if(priceRequest == null ){
            throw new OpenFoodFactsException("PriceRequest is null");
        }
        return pricesRestClient.get()
                .uri(uriUtils.findPriceUri(priceRequest))
                .retrieve()
                .body(OpenPriceFactsResponse.class);
    }


    public String uploadProductImage(ProductImageUploadRequest request) throws OpenFoodFactsException{

        if(clientProperties.username() == null && clientProperties.password() == null){
            throw  new OpenFoodFactsException("Client Username or Password is Invalid, cannot run image upload");
        }

        String username = clientProperties.username().orElseThrow(() ->
                new OpenFoodFactsException("Client Username or Password is Invalid, cannot run image upload"));

        String password = clientProperties.password().orElseThrow(()->
                new OpenFoodFactsException("Client Username or Password is Invalid cannot run image upload"));

        String auth = username + ":" + password;
        String authorization = java.util.Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + authorization);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String imageField = request.facet() + "_" + request.language();
        String imageUploadFieldKey ="imgupload_"+ request.facet() +"_"+ request.language();

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("code", request.productCode());
        multipartBodyBuilder.part("imagefield", imageField);
        multipartBodyBuilder.part(imageUploadFieldKey, request.file().getResource());

        return webClient.post()
                .uri(clientProperties.productImagePath())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(multipartBodyBuilder.build())
                .retrieve()
                .toEntity(String.class).block().getBody();
    }


}
