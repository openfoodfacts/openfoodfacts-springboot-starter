package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.openprices;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PriceRequest(
        @JsonProperty("category_tag") String categoryTag,
        @JsonProperty("created__gte") LocalDateTime createdGte,
        @JsonProperty("created__lte") LocalDateTime createdLte,
        @JsonProperty("currency") String currency,
        @JsonProperty("date") LocalDate date,
        @JsonProperty("date__gt") LocalDate dateGt,
        @JsonProperty("date__gte") LocalDate dateGte,
        @JsonProperty("date__lt") LocalDate dateLt,
        @JsonProperty("date__lte") LocalDate dateLte,
        @JsonProperty("date__month") Integer dateMonth,
        @JsonProperty("date__year") Integer dateYear,
        @JsonProperty("discount_type") String discountType,
        @JsonProperty("labels_tags__contains") String labelsTagsContains,
        @JsonProperty("location_id") Integer locationId,
        @JsonProperty("location_id__isnull") Boolean locationIdIsNull,
        @JsonProperty("location_osm_id") Long locationOsmId,
        @JsonProperty("location_osm_type") String locationOsmType,
        @JsonProperty("order_by") String orderBy,
        @JsonProperty("origins_tags__contains") String originsTagsContains,
        @JsonProperty("owner") String owner,
        @JsonProperty("page") Integer page,
        @JsonProperty("price") Double price,
        @JsonProperty("price__gt") Double priceGt,
        @JsonProperty("price__gte") Double priceGte,
        @JsonProperty("price__lt") Double priceLt,
        @JsonProperty("price__lte") Double priceLte,
        @JsonProperty("price_is_discounted") Boolean priceIsDiscounted,
        @JsonProperty("product__categories_tags__contains") String productCategoriesTagsContains,
        @JsonProperty("product_code") String productCode,
        @JsonProperty("product_id") Integer productId,
        @JsonProperty("product_id__isnull") Boolean productIdIsNull,
        @JsonProperty("proof_id") Integer proofId,
        @JsonProperty("proof_id__isnull") Boolean proofIdIsNull,
        @JsonProperty("size") Integer size,
        @JsonProperty("type") String type
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String categoryTag;
        private LocalDateTime createdGte;
        private LocalDateTime createdLte;
        private String currency;
        private LocalDate date;
        private LocalDate dateGt;
        private LocalDate dateGte;
        private LocalDate dateLt;
        private LocalDate dateLte;
        private Integer dateMonth;
        private Integer dateYear;
        private String discountType;
        private String labelsTagsContains;
        private Integer locationId;
        private Boolean locationIdIsNull;
        private Long locationOsmId;
        private String locationOsmType;
        private String orderBy;
        private String originsTagsContains;
        private String owner;
        private Integer page;
        private Double price;
        private Double priceGt;
        private Double priceGte;
        private Double priceLt;
        private Double priceLte;
        private Boolean priceIsDiscounted;
        private String productCategoriesTagsContains;
        private String productCode;
        private Integer productId;
        private Boolean productIdIsNull;
        private Integer proofId;
        private Boolean proofIdIsNull;
        private Integer size;
        private String type;

        public Builder categoryTag(String categoryTag) { this.categoryTag = categoryTag; return this; }
        public Builder createdGte(LocalDateTime createdGte) { this.createdGte = createdGte; return this; }
        public Builder createdLte(LocalDateTime createdLte) { this.createdLte = createdLte; return this; }
        public Builder currency(String currency) { this.currency = currency; return this; }
        public Builder date(LocalDate date) { this.date = date; return this; }
        public Builder dateGt(LocalDate dateGt) { this.dateGt = dateGt; return this; }
        public Builder dateGte(LocalDate dateGte) { this.dateGte = dateGte; return this; }
        public Builder dateLt(LocalDate dateLt) { this.dateLt = dateLt; return this; }
        public Builder dateLte(LocalDate dateLte) { this.dateLte = dateLte; return this; }
        public Builder dateMonth(Integer dateMonth) { this.dateMonth = dateMonth; return this; }
        public Builder dateYear(Integer dateYear) { this.dateYear = dateYear; return this; }
        public Builder discountType(String discountType) { this.discountType = discountType; return this; }
        public Builder labelsTagsContains(String labelsTagsContains) { this.labelsTagsContains = labelsTagsContains; return this; }
        public Builder locationId(Integer locationId) { this.locationId = locationId; return this; }
        public Builder locationIdIsNull(Boolean locationIdIsNull) { this.locationIdIsNull = locationIdIsNull; return this; }
        public Builder locationOsmId(Long locationOsmId) { this.locationOsmId = locationOsmId; return this; }
        public Builder locationOsmType(String locationOsmType) { this.locationOsmType = locationOsmType; return this; }
        public Builder orderBy(String orderBy) { this.orderBy = orderBy; return this; }
        public Builder originsTagsContains(String originsTagsContains) { this.originsTagsContains = originsTagsContains; return this; }
        public Builder owner(String owner) { this.owner = owner; return this; }
        public Builder page(Integer page) { this.page = page; return this; }
        public Builder price(Double price) { this.price = price; return this; }
        public Builder priceGt(Double priceGt) { this.priceGt = priceGt; return this; }
        public Builder priceGte(Double priceGte) { this.priceGte = priceGte; return this; }
        public Builder priceLt(Double priceLt) { this.priceLt = priceLt; return this; }
        public Builder priceLte(Double priceLte) { this.priceLte = priceLte; return this; }
        public Builder priceIsDiscounted(Boolean priceIsDiscounted) { this.priceIsDiscounted = priceIsDiscounted; return this; }
        public Builder productCategoriesTagsContains(String productCategoriesTagsContains) { this.productCategoriesTagsContains = productCategoriesTagsContains; return this; }
        public Builder productCode(String productCode) { this.productCode = productCode; return this; }
        public Builder productId(Integer productId) { this.productId = productId; return this; }
        public Builder productIdIsNull(Boolean productIdIsNull) { this.productIdIsNull = productIdIsNull; return this; }
        public Builder proofId(Integer proofId) { this.proofId = proofId; return this; }
        public Builder proofIdIsNull(Boolean proofIdIsNull) { this.proofIdIsNull = proofIdIsNull; return this; }
        public Builder size(Integer size) { this.size = size; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public PriceRequest build() { return new PriceRequest(categoryTag, createdGte, createdLte, currency, date, dateGt, dateGte, dateLt, dateLte, dateMonth, dateYear, discountType, labelsTagsContains, locationId, locationIdIsNull, locationOsmId, locationOsmType, orderBy, originsTagsContains, owner, page, price, priceGt, priceGte, priceLt, priceLte, priceIsDiscounted, productCategoriesTagsContains, productCode, productId, productIdIsNull, proofId, proofIdIsNull, size, type); }
    }
}
