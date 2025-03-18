package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images;


public enum ImageFacet {
    FRONT("front"),
    INGREDIENTS("ingredients"),
    NUTRITION("nutrition"),
    PACKAGING("packaging"),
    OTHER("other");

    private final String value;

    ImageFacet(String value) {
        this.value = value;
    }

    public String type() {
        return value;
    }

    public static ImageFacet from(String val) {
        for (ImageFacet facet : ImageFacet.values()) {
            if (facet.type().equalsIgnoreCase(val)) {
                return facet;
            }
        }
        throw new IllegalArgumentException("Invalid facet: " + val + " only available facets [front|ingredients|nutrition|packaging|other]");

    }
}