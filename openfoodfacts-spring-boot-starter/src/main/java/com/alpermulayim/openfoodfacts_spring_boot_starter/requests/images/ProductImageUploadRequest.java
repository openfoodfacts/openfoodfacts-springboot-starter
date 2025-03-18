package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images;

import com.alpermulayim.openfoodfacts_spring_boot_starter.lang.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public record ProductImageUploadRequest(
        String productCode,
        @JsonIgnore
        MultipartFile file,
        ImageFormat imageFormat,
        ImageFacet facet,
        Language language
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MultipartFile file;
        private ImageFacet facet;
        private Language language;
        private String productCode;
        private ImageFormat imageFormat;


        public Builder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder file(MultipartFile file) {
            this.file = file;
            return this;
        }

        public Builder facet(ImageFacet facet) {
            this.facet = facet;
            return this;
        }

        public Builder format(ImageFormat format) {
            this.imageFormat = format;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public ProductImageUploadRequest build() {
            return new ProductImageUploadRequest(productCode,file, imageFormat,facet, language);
        }
    }
}
