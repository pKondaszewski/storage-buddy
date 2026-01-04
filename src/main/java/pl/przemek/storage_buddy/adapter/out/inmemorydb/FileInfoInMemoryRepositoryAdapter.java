package pl.przemek.storage_buddy.adapter.out.inmemorydb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.przemek.storage_buddy.common.helper.uuid.UuidHelper;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryPort;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Profile("inmemorydb")
@RequiredArgsConstructor
class FileInfoInMemoryRepositoryAdapter implements FileInfoRepositoryPort {
    private static final ConcurrentHashMap<UUID, FileInfoInMemoryEntity> db = new ConcurrentHashMap<>();
    private final FileInfoInMemoryMapper mapper;
    private final UuidHelper uuidHelper;

    @Override
    public FileInfoRepositoryDto save(FileInfoRepositoryDto query) {
        FileInfoInMemoryEntity toBeSaved = mapper.toEntity(query);
        UUID id = uuidHelper.random();
        toBeSaved.setId(id);
        FileInfoInMemoryEntity saved = db.put(id, toBeSaved);
        return mapper.toDto(saved);
    }

    @Override
    public boolean existsByName(String name) {
        return db.values().stream().anyMatch(entity -> entity.getName().equals(name));
    }
}