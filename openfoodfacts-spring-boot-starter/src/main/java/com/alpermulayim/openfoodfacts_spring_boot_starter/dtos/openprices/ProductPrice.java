package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProductPrice(
        @JsonProperty("id") int id,
        @JsonProperty("product_id") int productId,
        @JsonProperty("location_id") int locationId,
        @JsonProperty("proof_id") int proofId,
        @JsonProperty("product") OpenPricesProduct product,
        @JsonProperty("location") OpenPricesProductLocation location,
        @JsonProperty("proof") OpenPriceProof proof,
        @JsonProperty("type") String type,
        @JsonProperty("product_code") String productCode,
        @JsonProperty("product_name") String productName,
        @JsonProperty("category_tag") String categoryTag,
        @JsonProperty("labels_tags") List<String> labelsTags,
        @JsonProperty("origins_tags") List<String> originsTags,
        @JsonProperty("price") double price,
        @JsonProperty("price_is_discounted") boolean priceIsDiscounted,
        @JsonProperty("price_without_discount") Double priceWithoutDiscount,
        @JsonProperty("discount_type") String discountType,
        @JsonProperty("price_per") Double pricePer,
        @JsonProperty("currency") String currency,
        @JsonProperty("location_osm_id") long locationOsmId,
        @JsonProperty("location_osm_type") String locationOsmType,
        @JsonProperty("date") LocalDate date,
        @JsonProperty("receipt_quantity") Integer receiptQuantity,
        @JsonProperty("owner") String owner,
        @JsonProperty("source") String source,
        @JsonProperty("created") LocalDateTime created,
        @JsonProperty("updated") LocalDateTime updated
) {}
