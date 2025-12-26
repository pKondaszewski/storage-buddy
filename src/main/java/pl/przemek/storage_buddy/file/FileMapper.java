package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

@Mapper
interface FileMapper {
    File toEntity(CreateFileRequest request);

    FileResponse toResponse(File entity);
}
