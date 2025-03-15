package com.alpermulayim.openfoodfacts_spring_boot_starter.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductSaveResponse(
        @JsonProperty("status_verbose")
        String message,
        @JsonProperty("status")
        Integer status
) { }
