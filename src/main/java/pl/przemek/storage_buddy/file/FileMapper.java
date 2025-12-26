package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoRequest;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoResponse;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    FileInfo toEntity(CreateFileInfoRequest request);

    CreatedFileInfoResponse toResponse(FileInfo entity);
}
