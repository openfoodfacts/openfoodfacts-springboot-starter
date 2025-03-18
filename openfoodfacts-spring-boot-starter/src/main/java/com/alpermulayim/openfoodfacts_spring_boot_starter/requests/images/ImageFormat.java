package com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images;

public enum ImageFormat {
    GIF("gif"),
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    HEIC("heic");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }

    public ImageFormat get(){
        return this;
    }
    public String extension() {
        return extension;
    }
    public static ImageFormat from(String val) {
        for (ImageFormat format : ImageFormat.values()) {
            if (format.extension().equalsIgnoreCase(val)) {
                return format;
            }
        }
        throw new IllegalArgumentException("Invalid Image Format: " + val + "only available [ gif|jpeg|jpg|png|heic ] formats");
    }
}
