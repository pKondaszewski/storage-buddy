package pl.przemek.storage_buddy.file.port.in;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PresignedPostFormDataResponse(
        String key, @JsonProperty("content-type") String contentType) {}
