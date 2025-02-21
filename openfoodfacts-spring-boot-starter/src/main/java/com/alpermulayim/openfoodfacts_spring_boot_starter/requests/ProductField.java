package com.alpermulayim.openfoodfacts_spring_boot_starter.requests;

public enum ProductField {
    ABBREVIATED_PRODUCT_NAME("abbreviated_product_name"),
    CODE("code"),
    CODES_TAGS("codes_tags"),
    GENERIC_NAME("generic_name"),
    ID("id"),
    LC("lc"),
    LANG("lang"),
    NOVA_GROUP("nova_group"),
    NOVA_GROUPS("nova_groups"),
    OBSOLETE("obsolete"),
    OBSOLETE_SINCE_DATE("obsolete_since_date"),
    PRODUCT_NAME("product_name"),
    PRODUCT_NAME_EN("product_name_en"),
    PRODUCT_QUANTITY("product_quantity"),
    PRODUCT_QUANTITY_UNIT("product_quantity_unit"),
    QUANTITY("quantity"),
    ADDITIVES_N("additives_n"),
    CHECKED("checked"),
    COMPLETE("complete"),
    COMPLETENESS("completeness"),
    ECOSCORE_GRADE("ecoscore_grade"),
    ECOSCORE_SCORE("ecoscore_score"),
    FOOD_GROUPS("food_groups"),
    FOOD_GROUPS_TAGS("food_groups_tags"),
    NUTRIENT_LEVELS("nutrient_levels"),
    PACKAGING_TEXT("packaging_text"),
    PACKAGINGS("packagings"),
    PACKAGINGS_COMPLETE("packagings_complete"),
    PNNS_GROUP_1("pnns_groups_1"),
    PNNS_GROUP_1_TAGS("pnns_groups_1_tags"),
    PNNS_GROUP_2("pnns_groups_2"),
    PNNS_GROUP_2_TAGS("pnns_groups_2_tags"),
    POPULARITY_KEY("popularity_key"),
    POPULARITY_TAGS("popularity_tags"),
    SCANS_N("scans_n"),
    UNIQUE_SCANS_N("unique_scans_n"),
    SERVING_QUANTITY("serving_quantity"),
    SERVING_QUANTITY_UNIT("serving_quantity_unit"),
    SERVING_SIZE("serving_size"),
    BRANDS("brands"),
    BRANDS_TAGS("brands_tags"),
    CATEGORIES("categories"),
    CATEGORIES_HIERARCHY("categories_hierarchy"),
    CATEGORIES_LC("categories_lc"),
    CATEGORIES_TAGS("categories_tags"),
    CHECKERS_TAGS("checkers_tags"),
    CITIES("cities"),
    CITIES_TAGS("cities_tags"),
    CORRECTORS_TAGS("correctors_tags"),
    COUNTRIES("countries"),
    COUNTRIES_HIERARCHY("countries_hierarchy"),
    COUNTRIES_LC("countries_lc"),
    COUNTRIES_TAGS("countries_tags"),
    ECOSCORE_TAGS("ecoscore_tags"),
    EMB_CODES("emb_codes"),
    EMB_CODES_ORIG("emb_codes_orig"),
    EMB_CODES_TAGS("emb_codes_tags"),
    LABELS("labels"),
    LABELS_HIERARCHY("labels_hierarchy"),
    LABELS_LC("labels_lc"),
    LABELS_TAGS("labels_tags"),
    ENTRY_DATES_TAGS("entry_dates_tags"),
    MANUFACTURING_PLACES("manufacturing_places"),
    MANUFACTURING_PLACES_TAGS("manufacturing_places_tags"),
    NOVA_GROUPS_TAGS("nova_groups_tags"),
    NUTRIENT_LEVELS_TAGS("nutrient_levels_tags"),
    IMAGES("images"),
    LAST_IMAGE_DATES_TAGS("last_image_dates_tags"),
    LAST_IMAGE_T("last_image_t"),
    SELECTED_IMAGES("selected_images"),
    IMAGE_SMALL_URL("image_small_url"),
    IMAGE_THUMB_URL("image_thumb_url"),
    IMAGE_URL("image_url"),
    ECOSCORE_DATA("ecoscore_data"),
    AGRIBALYSE("agribalyse"),
    GRADE("grade"),
    GRADES("grades"),
    MISSING("missing"),
    MISSING_DATA_WARNING("missing_data_warning"),
    PREVIOUS_DATA("previous_data"),
    SCORE("score"),
    SCORES("scores"),
    STATUS("status"),
    ECOSCORE_EXTENDED_DATA_VERSION("ecoscore_extended_data_version"),
    ENVIRONMENT_IMPACT_LEVEL("environment_impact_level"),
    ENVIRONMENT_IMPACT_LEVEL_TAGS("environment_impact_level_tags"),
    ADDITIVES_TAGS("additives_tags"),
    ALLERGENS("allergens"),
    ALLERGENS_LC("allergens_lc"),
    ALLERGENS_HIERARCHY("allergens_hierarchy"),
    ALLERGENS_TAGS("allergens_tags"),
    INGREDIENTS("ingredients"),
    INGREDIENTS_ANALYSIS("ingredients_analysis"),
    INGREDIENTS_TAGS("ingredients_tags"),
    INGREDIENTS_LC("ingredients_lc"),
    INGREDIENTS_TEXT("ingredients_text"),
    INGREDIENTS_TEXT_WITH_ALLERGENS("ingredients_text_with_allergens"),
    INGREDIENTS_FROM_PALM_OIL_N("ingredients_from_palm_oil_n"),
    INGREDIENTS_FROM_PALM_OIL_TAGS("ingredients_from_palm_oil_tags"),
    ORIGINS("origins"),
    ORIGINS_LC("origins_lc"),
    ORIGINS_TAGS("origins_tags"),
    TRACES("traces"),
    TRACES_LC("traces_lc"),
    UNKNOWN_INGREDIENTS_N("unknown_ingredients_n"),
    NO_NUTRITION_DATA("no_nutrition_data"),
    NUTRIMENTS("nutriments"),
    NUTRITION_GRADE_FR("nutrition_grade_fr"),
    NUTRITION_GRADES("nutrition_grades"),
    NUTRITION_GRADES_TAGS("nutrition_grades_tags"),
    NUTRITION_SCORE_BEVERAGE("nutrition_score_beverage"),
    NUTRITION_SCORE_WARNING_FRUITS_VEGETABLES_NUTS_ESTIMATE_FROM_INGREDIENTS("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients"),
    NUTRITION_SCORE_WARNING_FRUITS_VEGETABLES_NUTS_ESTIMATE_FROM_INGREDIENTS_VALUE("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value"),
    NUTRITION_SCORE_WARNING_NO_FIBER("nutrition_score_warning_no_fiber"),
    OTHER_NUTRITIONAL_SUBSTANCES_TAGS("other_nutritional_substances_tags"),
    UNKNOWN_NUTRIENTS_TAGS("unknown_nutrients_tags"),
    VITAMINS_TAGS("vitamins_tags"),
    NUTRISCORE_2021("nutriscore_2021"),
    NUTRISCORE_2023("nutriscore_2023"),
    NUTRISCORE_DATA("nutriscore_data"),
    NUTRISCORE_GRADE("nutriscore_grade"),
    NUTRISCORE_SCORE("nutriscore_score"),
    NUTRISCORE_SCORE_OPPOSITE("nutriscore_score_opposite"),
    NUTRISCORE_TAGS("nutriscore_tags"),
    NUTRISCORE_VERSION("nutriscore_version"),
    ADDITIVES_ORIGINAL_TAGS("additives_original_tags"),
    ADDITIVES_PREV_ORIGINAL_TAGS("additives_prev_original_tags"),
    ADDED_COUNTRIES_TAGS("added_countries_tags"),
    ALLERGENS_FROM_INGREDIENTS("allergens_from_ingredients"),
    ALLERGENS_FROM_USER("allergens_from_user"),
    AMINO_ACIDS_PREV_TAGS("amino_acids_prev_tags"),
    AMINO_ACIDS_TAGS("amino_acids_tags"),
    CARBON_FOOTPRINT_PERCENT_OF_KNOWN_INGREDIENTS("carbon_footprint_percent_of_known_ingredients"),
    CATEGORIES_PROPERTIES("categories_properties"),
    CATEGORIES_PROPERTIES_TAGS("categories_properties_tags"),
    COMPARED_TO_CATEGORY("compared_to_category"),
    CONSERVATION_CONDITIONS("conservation_conditions"),
    CUSTOMER_SERVICE("customer_service"),
    EXPIRATION_DATE("expiration_date"),
    LINK("link"),
    MAIN_COUNTRIES_TAGS("main_countries_tags"),
    MINERALS_PREV_TAGS("minerals_prev_tags"),
    MINERALS_TAGS("minerals_tags"),
    OWNER_FIELDS("owner_fields"),
    NOVA_GROUPS_MARKERS("nova_groups_markers"),
    ORIGIN("origin"),
    PURCHASE_PLACES("purchase_places"),
    PURCHASE_PLACES_TAGS("purchase_places_tags"),
    STORES("stores"),
    STORES_TAGS("stores_tags"),
    TRACES_FROM_INGREDIENTS("traces_from_ingredients"),
    TRACES_FROM_USER("traces_from_user"),
    CREATED_T("created_t"),
    CREATOR("creator"),
    EDITORS_TAGS("editors_tags"),
    INFORMERS_TAGS("informers_tags"),
    INTERFACE_VERSION_CREATED("interface_version_created"),
    INTERFACE_VERSION_MODIFIED("interface_version_modified"),
    LANGUAGES("languages"),
    LANGUAGES_CODES("languages_codes"),
    LANGUAGES_HIERARCHY("languages_hierarchy"),
    LANGUAGES_TAGS("languages_tags"),
    LAST_EDIT_DATES_TAGS("last_edit_dates_tags"),
    LAST_EDITOR("last_editor"),
    LAST_MODIFIED_BY("last_modified_by"),
    LAST_MODIFIED_T("last_modified_t"),
    OWNER("owner"),
    OWNERS_TAGS("owners_tags"),
    PHOTOGRAPHERS_TAGS("photographers_tags"),
    REV("rev"),
    SOURCES("sources"),
    SOURCES_FIELDS("sources_fields"),
    TEAMS("teams"),
    TEAMS_TAGS("teams_tags"),
    UPDATE_KEY("update_key"),
    KNOWLEDGE_PANELS("knowledge_panels");

    private final String fieldName;

    ProductField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String get(){
        return fieldName;
    }

}
