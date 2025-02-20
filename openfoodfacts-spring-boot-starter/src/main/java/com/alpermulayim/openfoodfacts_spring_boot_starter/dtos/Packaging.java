package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record Packaging(
     Integer numberOfUnits,
     String shape,
     String material,
     String recycling,
     String quantityPerUnit,
     Double quantityPerUnitValue,
     String quantityPerUnitUnit,
     Double weightSpecified,
     Double weightMeasured,
     Double weightEstimated,
     Double weight,
     String weightSourceId
) { }
