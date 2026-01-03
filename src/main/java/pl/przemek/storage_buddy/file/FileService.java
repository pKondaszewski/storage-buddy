package pl.przemek.storage_buddy.file;

import static pl.przemek.storage_buddy.common.LogMessages.SAVED_FILE_INFO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.przemek.storage_buddy.common.helper.uuid.UuidHelper;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.file.dto.request.PresignedPostFormDataRequest;
import pl.przemek.storage_buddy.file.dto.response.PresignedPostFormDataResponse;
import pl.przemek.storage_buddy.file.exception.FileInfoAlreadyExistsException;
import pl.przemek.storage_buddy.storage.StorageFacade;

@Service
@Slf4j
@RequiredArgsConstructor
class FileService {
    private final FileInfoRepository fileInfoRepository;
    private final FileMapper fileMapper;
    private final StorageFacade storageFacade;
    private final UuidHelper uuidHelper;

    @Transactional
    PresignedPostFormDataResponse createPresignedPostFormData(PresignedPostFormDataRequest request) {
        ensureFileDoesNotExist(request);
        FileInfo toBeSaved = fileMapper.toEntity(request, generateObjectKey(request.fileExtenstion()));
        FileInfo saved = fileInfoRepository.save(toBeSaved);
        log.info(SAVED_FILE_INFO, saved.getName());
        CreatedFileInfoDto dto = fileMapper.toDto(saved);
        return storageFacade.generatePresignedPostFormData(dto);
    }

    private void ensureFileDoesNotExist(PresignedPostFormDataRequest request) {
        if (fileInfoRepository.existsByName(request.name())) {
            throw new FileInfoAlreadyExistsException(request.name());
        }
    }

    private String generateObjectKey(String fileExtension) {
        return "%s.%s".formatted(uuidHelper.randomAsString(), fileExtension);
    }
}
