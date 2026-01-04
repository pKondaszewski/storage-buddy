package pl.przemek.storage_buddy.storage;

import pl.przemek.storage_buddy.file.dto.SavedFileInfoDto;
import pl.przemek.storage_buddy.file.port.in.PresignedPostFormDataResponse;

public interface StorageFacade {
    PresignedPostFormDataResponse generatePresignedPostFormData(SavedFileInfoDto fileInfo);
}
