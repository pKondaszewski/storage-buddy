package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoResponse;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    FileInfo toEntity(CreateFileInfoDto request);

    CreatedFileInfoResponse toResponse(FileInfo entity);
}
