package com.alpermulayim.openfoodfacts_spring_boot_starter.requests;

import java.util.List;
import java.util.stream.Collectors;

public record ProductSearchRequest(
        String additivesTags,
        String allergensTags,
        String brandsTags,
        String categoriesTags,
        String countriesTagsEn,
        String embCodesTags,
        String labelsTags,
        String manufacturingPlacesTags,
        String nutritionGradesTags,
        String originsTags,
        String packagingTagsDe,
        String purchasePlacesTags,
        String statesTags,
        String storesTags,
        String tracesTags,
        String fields,
        String sortBy,
        Integer page,
        Integer pageSize
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String additivesTags;
        private String allergensTags;
        private String brandsTags;
        private String categoriesTags;
        private String countriesTagsEn;
        private String embCodesTags;
        private String labelsTags;
        private String manufacturingPlacesTags;
        private String nutritionGradesTags;
        private String originsTags;
        private String packagingTagsDe;
        private String purchasePlacesTags;
        private String statesTags;
        private String storesTags;
        private String tracesTags;
        private String fields;
        private String sortBy;
        private Integer page;
        private Integer pageSize;

        public Builder additivesTags(String additivesTags) { this.additivesTags = additivesTags; return this; }
        public Builder allergensTags(String allergensTags) { this.allergensTags = allergensTags; return this; }
        public Builder brandsTags(String brandsTags) { this.brandsTags = brandsTags; return this; }
        public Builder categoriesTags(String categoriesTags) { this.categoriesTags = categoriesTags; return this; }
        public Builder countriesTagsEn(String countriesTagsEn) { this.countriesTagsEn = countriesTagsEn; return this; }
        public Builder embCodesTags(String embCodesTags) { this.embCodesTags = embCodesTags; return this; }
        public Builder labelsTags(String labelsTags) { this.labelsTags = labelsTags; return this; }
        public Builder manufacturingPlacesTags(String manufacturingPlacesTags) { this.manufacturingPlacesTags = manufacturingPlacesTags; return this; }
        public Builder nutritionGradesTags(String nutritionGradesTags) { this.nutritionGradesTags = nutritionGradesTags; return this; }
        public Builder originsTags(String originsTags) { this.originsTags = originsTags; return this; }
        public Builder packagingTagsDe(String packagingTagsDe) { this.packagingTagsDe = packagingTagsDe; return this; }
        public Builder purchasePlacesTags(String purchasePlacesTags) { this.purchasePlacesTags = purchasePlacesTags; return this; }
        public Builder statesTags(String statesTags) { this.statesTags = statesTags; return this; }
        public Builder storesTags(String storesTags) { this.storesTags = storesTags; return this; }
        public Builder tracesTags(String tracesTags) { this.tracesTags = tracesTags; return this; }
        public Builder fields(String fields) { this.fields = fields; return this; }
        public Builder sortBy(String sortBy) { this.sortBy = sortBy; return this; }
        public Builder page(Integer page) { this.page = page; return this; }
        public Builder pageSize(Integer pageSize) { this.pageSize = pageSize; return this; }

        public Builder fields(List<ProductField> fields) {
            String fieldString =  fields.stream()
                               .map(field->field.get())
                    .collect(Collectors.joining(","));
            this.fields = fieldString;
            return this;
        }

        public ProductSearchRequest build() {
            return new ProductSearchRequest(
                    additivesTags, allergensTags, brandsTags, categoriesTags, countriesTagsEn,
                    embCodesTags, labelsTags, manufacturingPlacesTags, nutritionGradesTags, originsTags,
                    packagingTagsDe, purchasePlacesTags, statesTags, storesTags, tracesTags,
                    fields, sortBy, page, pageSize
            );
        }
    }
}