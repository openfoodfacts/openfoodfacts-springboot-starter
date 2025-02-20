package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record AggregatedOrigins(
     String epiScore,
     String origin,
     Integer percent,
     Integer transportationScore
) { }
