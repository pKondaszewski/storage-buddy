package pl.przemek.storage_buddy.adapter.out.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryPort;

@Component
@Profile({"postgres", "mysql", "mssql"})
@RequiredArgsConstructor
class FileInfoJpaRepositoryAdapter implements FileInfoRepositoryPort {
    private final FileInfoJpaRepository repository;
    private final FileInfoJpaMapper mapper;

    @Override
    public FileInfoRepositoryDto save(FileInfoRepositoryDto query) {
        FileInfoJpaEntity toBeSaved = mapper.toEntity(query);
        FileInfoJpaEntity saved = repository.save(toBeSaved);
        return mapper.toDto(saved);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}