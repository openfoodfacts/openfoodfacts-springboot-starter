package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record OpenPricesProductLocation(
        @JsonProperty("id") int id,
        @JsonProperty("type") String type,
        @JsonProperty("osm_id") long osmId,
        @JsonProperty("osm_type") String osmType,
        @JsonProperty("osm_name") String osmName,
        @JsonProperty("osm_display_name") String osmDisplayName,
        @JsonProperty("osm_tag_key") String osmTagKey,
        @JsonProperty("osm_tag_value") String osmTagValue,
        @JsonProperty("osm_brand") String osmBrand,
        @JsonProperty("osm_address_postcode") String osmAddressPostcode,
        @JsonProperty("osm_address_city") String osmAddressCity,
        @JsonProperty("osm_address_country") String osmAddressCountry,
        @JsonProperty("osm_address_country_code") String osmAddressCountryCode,
        @JsonProperty("osm_lat") double osmLat,
        @JsonProperty("osm_lon") double osmLon,
        @JsonProperty("osm_version") int osmVersion,
        @JsonProperty("website_url") String websiteUrl,
        @JsonProperty("price_count") int priceCount,
        @JsonProperty("user_count") int userCount,
        @JsonProperty("product_count") int productCount,
        @JsonProperty("proof_count") int proofCount,
        @JsonProperty("source") String source,
        @JsonProperty("created") LocalDateTime created,
        @JsonProperty("updated") LocalDateTime updated
) {}