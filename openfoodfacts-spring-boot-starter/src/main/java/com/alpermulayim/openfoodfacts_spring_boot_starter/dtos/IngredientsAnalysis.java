package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

import java.util.List;

public record IngredientsAnalysis(
        List<String> palmOil,
        List<String> veganStatusUnknown,
        List<String> vegetarianStatusUnknown
) {}
