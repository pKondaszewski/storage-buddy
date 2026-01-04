package pl.przemek.storage_buddy.adapter.out.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryPort;

@Component
@Profile("mongo")
@RequiredArgsConstructor
class FileInfoMongoRepositoryAdapter implements FileInfoRepositoryPort {
    private final FileInfoMongoRepository repository;
    private final FileInfoMongoMapper mapper;

    @Override
    public FileInfoRepositoryDto save(FileInfoRepositoryDto query) {
        FileInfoMongoDocument toBeSaved = mapper.toDocument(query);
        FileInfoMongoDocument saved = repository.save(toBeSaved);
        return mapper.toDto(saved);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}