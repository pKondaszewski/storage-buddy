package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    File toEntity(CreateFileRequest request);

    FileResponse toResponse(File entity);
}
