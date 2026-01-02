package pl.przemek.storage_buddy.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PresignedPostFormDataResponse(
        String key, @JsonProperty("content-type") String contentType) {}
