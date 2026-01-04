package pl.przemek.storage_buddy.file.port.out;

public interface FileInfoRepositoryPort {
    FileInfoRepositoryDto save(FileInfoRepositoryDto dto);

    boolean existsByName(String name);
}
