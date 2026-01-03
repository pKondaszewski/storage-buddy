package pl.przemek.storage_buddy.storage;

import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.file.dto.response.PresignedPostFormDataResponse;

public interface StorageFacade {
    PresignedPostFormDataResponse generatePresignedPostFormData(CreatedFileInfoDto fileInfo);
}
