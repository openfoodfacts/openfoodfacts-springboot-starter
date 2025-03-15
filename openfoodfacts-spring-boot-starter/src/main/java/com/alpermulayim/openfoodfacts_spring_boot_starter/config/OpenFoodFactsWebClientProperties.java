package com.alpermulayim.openfoodfacts_spring_boot_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Optional;

/**
 * @author Alper Mulayim  <a href="https://github.com/AlperMulayim">...</a>
 */
@ConfigurationProperties(prefix = "openfoodfacts")
public record OpenFoodFactsWebClientProperties (
        @DefaultValue("https://world.openfoodfacts.org")
        String baseUrl,
        @DefaultValue("https://prices.openfoodfacts.org")
        String pricesBaseUrl,
        @DefaultValue("/api/v2/search")
        String searchPath,
        @DefaultValue("/api/v2/product")
        String productPath,
        @DefaultValue("/api/v1/prices")
        String pricePath,
        @DefaultValue("/cgi/product_image_upload.pl")
        String productImagePath,
        @DefaultValue("/cgi/product_jqm2.pl")
        String productPathSave,
        Optional<String> username,
        Optional<String> password
){}

