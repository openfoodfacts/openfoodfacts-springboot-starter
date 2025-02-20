package com.alpermulayim.openfoodfacts_spring_boot_starter;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class FakeController {
    @Autowired
    private ApiService service;

    @GetMapping
    OpenFoodFactsResponse getProduct(){
        System.out.println("get products");
        return service.request();
    }
}
