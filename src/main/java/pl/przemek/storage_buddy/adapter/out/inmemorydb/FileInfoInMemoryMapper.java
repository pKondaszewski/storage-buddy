package pl.przemek.storage_buddy.adapter.out.inmemorydb;

import org.mapstruct.Mapper;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;

@Mapper
interface FileInfoInMemoryMapper {
    FileInfoInMemoryEntity toEntity(FileInfoRepositoryDto dto);
    FileInfoRepositoryDto toDto(FileInfoInMemoryEntity entity);
}
