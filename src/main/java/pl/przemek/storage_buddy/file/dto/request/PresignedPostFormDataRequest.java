package pl.przemek.storage_buddy.file.dto.request;

public record PresignedPostFormDataRequest(
        String name, String contentType, String fileExtenstion, Integer size) {}
