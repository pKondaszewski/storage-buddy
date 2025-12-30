package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    FileInfo toEntity(CreateFileInfoDto request);

    CreatedFileInfoDto toResponse(FileInfo entity);
}
