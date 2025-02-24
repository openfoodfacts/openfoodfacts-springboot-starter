package com.alpermulayim.openfoodfacts_spring_boot_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author Alper Mulayim  <a href="https://github.com/AlperMulayim">...</a>
 * "https://world.openfoodfacts.org/api/v2/product/5449000000996.json TODO: is not good for REST standards find out correct endpoint.
 */
@ConfigurationProperties(prefix = "openfoodfacts")
public record OpenFoodFactsWebClientProperties (
        @DefaultValue("https://world.openfoodfacts.org")
        String baseUrl,
        @DefaultValue("/api/v2/search")
        String searchPath,
        @DefaultValue("/api/v2/product")
        String productPath,
        @DefaultValue("/")   // TODO: /api/v2/product/5449000000996.json  is not good for REST standards find out correct endpoint.
        String productsPathJsonDelimeter, // OpenFoodFacts API request build < /api/v2/product/5449000000996.json > I think need to updated.
        @DefaultValue(".json")
        String productsPathJsonPrefix // OpenFoodFacts API request < /api/v2/product/5449000000996.json > I think need to updated.
){}

