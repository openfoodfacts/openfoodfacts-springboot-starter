package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record Packaging(
     Integer numberOfUnits,
     Shape shape,
     Material material,
     Recycling recycling,
     String quantityPerUnit,
     Double quantityPerUnitValue,
     String quantityPerUnitUnit,
     Double weightSpecified,
     Double weightMeasured,
     Double weightEstimated,
     Double weight,
     String weightSourceId
) { }
