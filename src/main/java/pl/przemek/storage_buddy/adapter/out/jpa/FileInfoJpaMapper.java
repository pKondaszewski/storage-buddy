package pl.przemek.storage_buddy.adapter.out.jpa;

import org.mapstruct.Mapper;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;

@Mapper
interface FileInfoJpaMapper {
    FileInfoJpaEntity toEntity(FileInfoRepositoryDto dto);
    FileInfoRepositoryDto toDto(FileInfoJpaEntity entity);
}
