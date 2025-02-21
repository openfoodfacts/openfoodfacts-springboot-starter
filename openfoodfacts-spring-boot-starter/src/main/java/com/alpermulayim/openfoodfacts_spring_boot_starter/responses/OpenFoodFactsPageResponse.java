package com.alpermulayim.openfoodfacts_spring_boot_starter.responses;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenFoodFactsPageResponse(
        @JsonProperty("page")
        Long page,
        @JsonProperty("page_size")
        Long pageSize,
        @JsonProperty("page_count")
        Long pageCount,
        @JsonProperty("count")
        Long count,
        @JsonProperty("skip")
        Long skip,
        @JsonProperty("products")
        List<Product> products
) { }
