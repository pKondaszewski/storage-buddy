package pl.przemek.storage_buddy.adapter.out.mongo;

import org.mapstruct.Mapper;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;

@Mapper
interface FileInfoMongoMapper {
    FileInfoMongoDocument toDocument(FileInfoRepositoryDto dto);
    FileInfoRepositoryDto toDto(FileInfoMongoDocument document);
}
