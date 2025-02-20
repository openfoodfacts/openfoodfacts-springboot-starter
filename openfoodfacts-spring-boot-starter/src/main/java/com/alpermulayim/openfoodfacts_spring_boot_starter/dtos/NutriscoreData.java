package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record NutriscoreData (
    double saturatedFatRatio,
    int saturatedFatRatioPoints,
    double saturatedFatRatioValue
){}