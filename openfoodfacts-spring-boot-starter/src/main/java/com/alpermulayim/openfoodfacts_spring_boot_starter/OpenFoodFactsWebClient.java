package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.Product;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductField;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.utils.UriUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


@Service("OpenFoodFactsWebClient")
public class OpenFoodFactsWebClient {
    private RestClient restClient;
    private UriUtils uriUtils;

    @Autowired
    public OpenFoodFactsWebClient() {
        this.uriUtils = new UriUtils();
        restClient = RestClient.create("https://world.openfoodfacts.org");
    }

    public OpenFoodFactsResponse getProduct(String productCode,List<ProductField> fields){
        if(productCode == null || productCode.isEmpty() ){
            throw new OpenFoodFactsException("Product  Number cannot be null for product request");
        }

       OpenFoodFactsResponse product = restClient.get()
                .uri(uriUtils.productsUri(productCode, fields))
                .retrieve()
                .body(OpenFoodFactsResponse.class);

        return product;
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

    //TODO: response will be PAGE
    public OpenFoodFactsPageResponse searchProduct(ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException {

       return restClient.get()
                .uri(uriUtils.searchUri(request))
                .retrieve()
                .body(OpenFoodFactsPageResponse.class);
    }

}
