package com.alpermulayim.openfoodfacts_spring_boot_starter.utils;

import com.alpermulayim.openfoodfacts_spring_boot_starter.exceptions.OpenFoodFactsException;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.images.ProductImageUploadRequest;
import com.alpermulayim.openfoodfacts_spring_boot_starter.requests.saves.ProductSaveRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.RecordComponent;
import java.util.List;

public class MultiPartUtils {

    public MultiValueMap<String, HttpEntity<?>> getProductSaveMultiPartFormBody(ProductSaveRequest request) throws OpenFoodFactsException {

        if(!validateRequest(request)){
            throw new OpenFoodFactsException("ProductSaveRequest could not be null or non-numeric or empty  for request product save");
        }

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        List<RecordComponent> recordComponents = List.of(request.getClass().getRecordComponents());

        for (RecordComponent component : recordComponents) {
            try {
                Object value = component.getAccessor().invoke(request);
                JsonProperty jsonProperty = component.getAccessor().getAnnotation(JsonProperty.class);

                if (value != null) {
                    multipartBodyBuilder.part(jsonProperty.value(), value);
                }

            } catch (Exception e) {
                throw new OpenFoodFactsException("ProductSaveRequest are not able to parsed");
            }
        }
        return multipartBodyBuilder.build();
    }

    public MultiValueMap<String,HttpEntity<?>> getImageUploadMultiPartFormBody(ProductImageUploadRequest request){

        String imageField = request.facet() + "_" + request.language().code();
        String imageUploadFieldKey ="imgupload_"+ request.facet() +"_"+ request.language().code();

        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        multipartBodyBuilder.part("code", request.productCode());
        multipartBodyBuilder.part("imagefield", imageField);
        multipartBodyBuilder.part(imageUploadFieldKey, request.file().getResource());

        return multipartBodyBuilder.build();
    }

    private boolean validateRequest(ProductSaveRequest request){
        if(request == null || request.code() == null || request.code().isEmpty()){
            return false;
        }
        return request.code().matches("\\d+");
    }
}
