package com.alpermulayim.openfoodfacts_starter_demo.dtos;


public record DemoPrice (
        String productCode,
        String productName,
        String brand,
        String store,
        String country,
        String currency,
        Double price,
        String imageUrl
){  }
