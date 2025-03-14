package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images;

import com.alpermulayim.openfoodfacts_spring_boot_starter.dtos.Product;
import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public record ProductImageUploadRequest(
        String productCode,
        @JsonIgnore
        MultipartFile file,
        String facet,
        Language language
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MultipartFile file;
        private String facet;
        private Language language;
        private String productCode;


        public Builder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder file(MultipartFile file) {
            this.file = file;
            return this;
        }

        public Builder facet(String facet) {
            this.facet = facet;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public ProductImageUploadRequest build() {
            return new ProductImageUploadRequest(productCode,file, facet, language);
        }
    }
}
