package com.alpermulayim.openfoodfacts_spring_boot_starter.requests;

public enum ProductSearchField {
    additivesTags("additives_tags"),
    allergensTags("allergens_tags"),
    brandsTags("brands_tags"),
    categoriesTags("categories_tags"),
    countriesTagsEn("countries_tags_en"),
    embCodesTags("emb_codes_tags"),
    labelsTags("labels_tags"),
    manufacturingPlacesTags("manufacturing_places_tags"),
    nutritionGradesTags("nutrition_grades_tags"),
    originsTags("origins_tags"),
    packagingTagsDe("packaging_tags_de"),
    purchasePlacesTags("purchase_places_tags"),
    statesTags("states_tags"),
    storesTags("stores_tags"),
    tracesTags("traces_tags"),
    fields("fields"),
    sortBy("sort_by"),
    page("page"),
    pageSize("page_size");

    private final String fieldName;

    ProductSearchField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String get() {
        return fieldName;
    }

    public static ProductSearchField fromFieldName(String fieldName) {
        for (ProductSearchField field : values()) {
            if (field.name().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }
}