package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OpenPricesProduct(
        @JsonProperty("id") int id,
        @JsonProperty("code") String code,
        @JsonProperty("source") String source,
        @JsonProperty("source_last_synced") LocalDateTime sourceLastSynced,
        @JsonProperty("product_name") String productName,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("product_quantity") String productQuantity,
        @JsonProperty("product_quantity_unit") String productQuantityUnit,
        @JsonProperty("categories_tags") List<String> categoriesTags,
        @JsonProperty("brands") String brands,
        @JsonProperty("brands_tags") List<String> brandsTags,
        @JsonProperty("labels_tags") List<String> labelsTags,
        @JsonProperty("nutriscore_grade") String nutriscoreGrade,
        @JsonProperty("ecoscore_grade") String ecoscoreGrade,
        @JsonProperty("nova_group") String novaGroup,
        @JsonProperty("unique_scans_n") int uniqueScansN,
        @JsonProperty("price_count") int priceCount,
        @JsonProperty("price_currency_count") int priceCurrencyCount,
        @JsonProperty("location_count") int locationCount,
        @JsonProperty("location_type_osm_country_count") int locationTypeOsmCountryCount,
        @JsonProperty("user_count") int userCount,
        @JsonProperty("proof_count") int proofCount,
        @JsonProperty("created") LocalDateTime created,
        @JsonProperty("updated") LocalDateTime updated
) {}