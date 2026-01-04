package pl.przemek.storage_buddy.file;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.file.dto.SavedFileInfoDto;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;
import pl.przemek.storage_buddy.file.port.in.PresignedPostFormDataRequest;

@Mapper
interface FileMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    FileInfoRepositoryDto toRepositoryDto(PresignedPostFormDataRequest request, String objectKey);

    SavedFileInfoDto toDto(FileInfoRepositoryDto result);
}
