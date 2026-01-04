package pl.przemek.storage_buddy.adapter.out.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

interface FileInfoMongoRepository extends MongoRepository<FileInfoMongoDocument, UUID> {
    boolean existsByName(String name);
}
