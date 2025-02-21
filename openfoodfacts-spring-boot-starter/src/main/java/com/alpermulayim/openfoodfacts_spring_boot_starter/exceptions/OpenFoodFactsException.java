package com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions;

public class OpenFoodFactsException  extends RuntimeException{
    public OpenFoodFactsException(String message) {
        super( "[OpenFoodFactsException] "+ message);
    }
}
