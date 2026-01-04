package pl.przemek.storage_buddy.file.port.in;

public record PresignedPostFormDataRequest(
        String name, String contentType, String fileExtenstion, Integer size) {}
