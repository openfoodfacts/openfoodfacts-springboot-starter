package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductSaveRequest(
        @JsonProperty("code")
        String code,
        @JsonProperty("brands")
        String brands,
        @JsonProperty("labels")
        String labels,
        @JsonProperty("categories")
        String categories,
        @JsonProperty("packaging")
        String packaging,
        @JsonProperty("comment")
        String comment,
        @JsonProperty("ingredients_text")
        String ingredientsText,
        @JsonProperty("product_name")
        String productName

) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private String brands;
        private String labels;
        private String categories;
        private String packaging;
        private String comment;
        private String ingredientsText;
        private String productName;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder brands(String brands) {
            this.brands = brands;
            return this;
        }

        public Builder labels(String labels) {
            this.labels = labels;
            return this;
        }

        public Builder categories(String categories) {
            this.categories = categories;
            return this;
        }

        public Builder packaging(String packaging) {
            this.packaging = packaging;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder ingredientsText(String ingredientsText) {
            this.ingredientsText = ingredientsText;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductSaveRequest build() {
            return new ProductSaveRequest(code, brands, labels, categories, packaging, comment, ingredientsText, productName);
        }
    }
}

