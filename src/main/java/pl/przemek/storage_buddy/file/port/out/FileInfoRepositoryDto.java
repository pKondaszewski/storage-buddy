package pl.przemek.storage_buddy.file.port.out;

import java.time.Instant;
import java.util.UUID;

public record FileInfoRepositoryDto(UUID id, String name, Integer size, String objectKey, String contentType, FileStatusDto status, Instant createdAt, Instant modifiedAt) {
    public enum FileStatusDto {
        PENDING,
        UPLOADED
    }
}
