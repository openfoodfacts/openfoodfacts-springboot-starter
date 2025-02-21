package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
/**
 * @author Alper Mulayim  https://github.com/AlperMulayim
 */

@RestController
@RequestMapping("api/v1/products")
public class DemoOpenFoodFactsController {
    @Autowired
    private DemoOpenFoodFactsService service;

    @GetMapping
    OpenFoodFactsResponse getProduct(){
        return service.request();
    }

    @GetMapping("/{code}")
    OpenFoodFactsResponse getProduct(@PathVariable String code){
        return service.request(code);
    }

    @GetMapping("/search")
    OpenFoodFactsPageResponse getProduct(@RequestBody ProductSearchRequest request) throws InvocationTargetException, IllegalAccessException {
        return service.search(request);
    }
}
