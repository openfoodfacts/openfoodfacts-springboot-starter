package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenFoodFactsResponse(
    @JsonProperty("code")
    String code,
    @JsonProperty("product")
    Product product,
    @JsonProperty("status")
    Long status,
    @JsonProperty("status_verbose")
    String statusVerbose
){}
