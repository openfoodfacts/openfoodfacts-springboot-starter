package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.OpenFoodFactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class DemoOpenFoodFactsController {
    @Autowired
    private DemoOpenFoodFactsService service;

    @GetMapping
    OpenFoodFactsResponse getProduct(){
        return service.request();
    }
}
