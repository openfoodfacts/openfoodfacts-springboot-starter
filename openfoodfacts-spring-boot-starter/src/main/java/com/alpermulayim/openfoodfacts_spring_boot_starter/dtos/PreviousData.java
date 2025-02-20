package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record PreviousData(
    String lastUpdate,
    String previousCategory,
    Integer previousLabelStatus
) { }
