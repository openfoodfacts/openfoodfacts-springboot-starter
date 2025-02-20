package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

import java.util.List;

public record OriginsOfIngredients(
    List<AggregatedOrigins> aggregatedOrigins
) { }
