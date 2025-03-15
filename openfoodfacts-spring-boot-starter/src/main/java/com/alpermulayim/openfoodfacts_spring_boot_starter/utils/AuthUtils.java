package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component("openFoodFactsAuthUtils")
public class AuthUtils {
    private OpenFoodFactsWebClientProperties clientProperties;

    @Autowired
    public AuthUtils(OpenFoodFactsWebClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    public HttpHeaders getAuthHeaders(){
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

        return headers;
    }
}
