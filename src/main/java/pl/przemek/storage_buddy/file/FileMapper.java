package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.file.dto.request.PresignedPostFormDataRequest;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    FileInfo toEntity(PresignedPostFormDataRequest request, String objectKey);

    CreatedFileInfoDto toDto(FileInfo entity);
}
