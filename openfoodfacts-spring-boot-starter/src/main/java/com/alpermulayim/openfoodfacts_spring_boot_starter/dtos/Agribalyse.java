package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record Agribalyse(
     String agribalyseProxyFoodCode,
     String agribalyseFoodCode,
     Double co2Agriculture,
     Integer co2Consumption,
     Double co2Distribution,
     Double co2Packaging,
     Double co2Processing,
     Double co2Total,
     Double co2Transportation
) { }
