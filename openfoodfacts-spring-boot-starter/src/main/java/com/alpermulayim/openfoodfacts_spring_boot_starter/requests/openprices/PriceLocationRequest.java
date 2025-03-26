package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceLocationRequest(
        @JsonProperty("order_by")
        String orderBy,

        @JsonProperty("osm_address_city_like")
        String osmAddressCityLike,

        @JsonProperty("osm_address_country_like")
        String osmAddressCountryLike,

        @JsonProperty("osm_name_like")
        String osmNameLike,

        @JsonProperty("page")
        Integer page,

        @JsonProperty("price_count")
        Integer priceCount,

        @JsonProperty("price_count_gte")
        Integer priceCountGte,

        @JsonProperty("price_count_lte")
        Integer priceCountLte,

        @JsonProperty("size")
        Integer size,

        @JsonProperty("type")
        OsmType type
) {
    public static Builder builder() {
        return new Builder();
    }

    // Builder class for LocationRequest
    public static class Builder {
        private String orderBy;
        private String osmAddressCityLike;
        private String osmAddressCountryLike;
        private String osmNameLike;
        private Integer page;
        private Integer priceCount;
        private Integer priceCountGte;
        private Integer priceCountLte;
        private Integer size;
        private OsmType type;


        public Builder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder osmAddressCityLike(String osmAddressCityLike) {
            this.osmAddressCityLike = osmAddressCityLike;
            return this;
        }

        public Builder osmAddressCountryLike(String osmAddressCountryLike) {
            this.osmAddressCountryLike = osmAddressCountryLike;
            return this;
        }

        public Builder osmNameLike(String osmNameLike) {
            this.osmNameLike = osmNameLike;
            return this;
        }

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder priceCount(Integer priceCount) {
            this.priceCount = priceCount;
            return this;
        }

        public Builder priceCountGte(Integer priceCountGte) {
            this.priceCountGte = priceCountGte;
            return this;
        }

        public Builder priceCountLte(Integer priceCountLte) {
            this.priceCountLte = priceCountLte;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder type(String type) {
            this.type = OsmType.valueOf(type.toUpperCase());
            return this;
        }

        public Builder type(OsmType type) {
            this.type = type;
            return this;
        }

        public PriceLocationRequest build() {
            return new PriceLocationRequest(
                    orderBy, osmAddressCityLike, osmAddressCountryLike, osmNameLike,
                    page, priceCount, priceCountGte, priceCountLte, size, type
            );
        }
    }
}