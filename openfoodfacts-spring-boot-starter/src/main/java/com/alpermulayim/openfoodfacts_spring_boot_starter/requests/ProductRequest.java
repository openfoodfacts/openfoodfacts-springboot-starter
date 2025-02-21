package com.alpermulayim.openfoodfacts_spring_boot_starter.requests;

import java.util.List;

public record ProductRequest (
        String productCode,
        List<ProductField> productFields
) {}
