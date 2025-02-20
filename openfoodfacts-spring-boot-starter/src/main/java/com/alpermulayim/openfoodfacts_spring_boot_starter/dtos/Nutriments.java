package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record Nutriments(
    Double fat,
    Double carbohydrates,
    Double energy,
    Double salt,
    Double saturatedFat,
    Double sugars,
    Double proteins
) { }
