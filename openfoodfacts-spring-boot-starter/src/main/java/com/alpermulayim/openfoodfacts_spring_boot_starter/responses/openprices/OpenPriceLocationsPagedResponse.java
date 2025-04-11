package com.alpermulayim.openfoodfacts_spring_boot_starter.responses.openprices;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices.OpenPricesProductLocation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenPriceLocationsPagedResponse (
            @JsonProperty("items") List<OpenPricesProductLocation> prices,
            @JsonProperty("page") int page,
            @JsonProperty("pages") int pages,
            @JsonProperty("size") int size,
            @JsonProperty("total") int total
    ) {}

