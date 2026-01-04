package pl.przemek.storage_buddy.adapter.out.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "file_info")
class FileInfoMongoDocument {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    private Integer size;
    private String objectKey;
    private String contentType;

    private FileStatus status;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;

    enum FileStatus {
        PENDING,
        UPLOADED
    }
}
