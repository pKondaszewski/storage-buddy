package pl.przemek.storage_buddy.adapter.out.inmemorydb;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
class FileInfoInMemoryEntity {
    private UUID id;
    private String name;
    private Integer size;
    private String objectKey;
    private String contentType;
    private FileStatus status;
    private Instant createdAt;
    private Instant modifiedAt;

    enum FileStatus {
        PENDING,
        UPLOADED
    }
}