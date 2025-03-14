package com.alpermulayim.openfoodfacts_starter_demo;

import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.ProductSearchRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices.PriceRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsPageResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.OpenFoodFactsResponse;
import com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices.OpenPriceFactsResponse;
import com.alpermulayim.openfoodfacts_starter_demo.dtos.DemoPrice;
import com.alpermulayim.openfoodfacts_starter_demo.dtos.DemoProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    @GetMapping("/searchCustom")
    OpenFoodFactsPageResponse getProductCustom() throws InvocationTargetException, IllegalAccessException {
        return service.searchCustom();
    }

    @GetMapping("/converted")
    List<DemoProduct> getCustomConvertedProducts() throws InvocationTargetException, IllegalAccessException {
       return service.searchDemoProducts();
    }

    @GetMapping("/price")
    OpenPriceFactsResponse getPriceForProduct(@RequestParam("code") String code){
        return service.getProductPrice(code);
    }

    @GetMapping("/price/search")
    OpenPriceFactsResponse pricesSearch(@RequestBody PriceRequest request){
        return service.searchPrice(request);
    }

    @GetMapping("/price/custom")
    OpenPriceFactsResponse pricesSearch(){
        return service.searchPriceCustom();
    }

    @GetMapping("/price/mySearch")
    List<DemoPrice> pricesSearchMy(){
        return service.myDemoSearchMyProductPrices();
    }

    @PostMapping("image")
    String uploadProductImage( @RequestParam("code") String code,@RequestParam("lang") String lang, @RequestParam("facet")  String facet, @RequestParam("file") MultipartFile file) throws IOException {
        return service.uploadProductImage(code,lang,facet,file);
    }
}
