package com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record OpenPriceProof(
        @JsonProperty("id") int id,
        @JsonProperty("location_id") int locationId,
        @JsonProperty("file_path") String filePath,
        @JsonProperty("mimetype") String mimetype,
        @JsonProperty("type") String type,
        @JsonProperty("image_thumb_path") String imageThumbPath,
        @JsonProperty("location_osm_id") long locationOsmId,
        @JsonProperty("location_osm_type") String locationOsmType,
        @JsonProperty("date") LocalDate date,
        @JsonProperty("currency") String currency,
        @JsonProperty("receipt_price_count") Integer receiptPriceCount,
        @JsonProperty("receipt_price_total") Double receiptPriceTotal,
        @JsonProperty("receipt_online_delivery_costs") Double receiptOnlineDeliveryCosts,
        @JsonProperty("ready_for_price_tag_validation") boolean readyForPriceTagValidation,
        @JsonProperty("price_count") int priceCount,
        @JsonProperty("prediction_count") int predictionCount,
        @JsonProperty("owner") String owner,
        @JsonProperty("source") String source,
        @JsonProperty("created") LocalDateTime created,
        @JsonProperty("updated") LocalDateTime updated
) {}