package pl.przemek.storage_buddy.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.przemek.storage_buddy.common.helper.uuid.UuidHelper;
import pl.przemek.storage_buddy.file.dto.SavedFileInfoDto;
import pl.przemek.storage_buddy.file.exception.FileInfoAlreadyExistsException;
import pl.przemek.storage_buddy.file.port.in.FileInputPort;
import pl.przemek.storage_buddy.file.port.in.PresignedPostFormDataRequest;
import pl.przemek.storage_buddy.file.port.in.PresignedPostFormDataResponse;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryDto;
import pl.przemek.storage_buddy.file.port.out.FileInfoRepositoryPort;
import pl.przemek.storage_buddy.storage.StorageFacade;

import static pl.przemek.storage_buddy.common.LogMessages.SAVED_FILE_INFO;

@Service
@Slf4j
@RequiredArgsConstructor
class FileService implements FileInputPort {
    private final FileInfoRepositoryPort repository;
    private final FileMapper mapper;
    private final StorageFacade storageFacade;
    private final UuidHelper uuidHelper;

    @Transactional
    public PresignedPostFormDataResponse createPresignedPostFormData(PresignedPostFormDataRequest request) {
        ensureFileDoesNotExist(request);
        SavedFileInfoDto dto = saveFileInfoInPendingMode(request);
        return storageFacade.generatePresignedPostFormData(dto);
    }

    private void ensureFileDoesNotExist(PresignedPostFormDataRequest request) {
        if (repository.existsByName(request.name())) {
            throw new FileInfoAlreadyExistsException(request.name());
        }
    }

    private SavedFileInfoDto saveFileInfoInPendingMode(PresignedPostFormDataRequest request) {
        FileInfoRepositoryDto query = mapper.toRepositoryDto(request, generateObjectKey(request.fileExtenstion()));
        FileInfoRepositoryDto result = repository.save(query);
        log.info(SAVED_FILE_INFO, result.name());
        return mapper.toDto(result);
    }

    private String generateObjectKey(String fileExtension) {
        return "%s.%s".formatted(uuidHelper.randomAsString(), fileExtension);
    }
}
