package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos;

public record Missing(
    Integer labels,
    Integer origins,
    Integer packagings
) { }
