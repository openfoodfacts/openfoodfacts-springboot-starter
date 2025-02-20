package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record EcoscoreData(
    Adjustments adjustments,
    Packaging packaging,
    ProductionSystem productionSystem,
    ThreatenedSpecies threatenedSpecies
) { }
