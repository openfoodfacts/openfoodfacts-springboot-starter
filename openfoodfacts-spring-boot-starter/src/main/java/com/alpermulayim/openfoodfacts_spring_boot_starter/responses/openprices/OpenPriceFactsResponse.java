package com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices.OpenPricesProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenPriceFactsResponse(
        @JsonProperty("items") List<OpenPricesProduct> items,
        @JsonProperty("page") int page,
        @JsonProperty("pages") int pages,
        @JsonProperty("size") int size,
        @JsonProperty("total") int total
) {}