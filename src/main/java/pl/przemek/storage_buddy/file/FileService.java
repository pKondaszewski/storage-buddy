package pl.przemek.storage_buddy.file;

import static pl.przemek.storage_buddy.common.LogMessages.CREATED_FILE_INFO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoResponse;
import pl.przemek.storage_buddy.file.exception.FileInfoAlreadyExistsException;

@Slf4j
@RequiredArgsConstructor
class FileService {
    private final FileInfoRepository fileInfoRepository;
    private final FileMapper fileMapper;

    CreatedFileInfoResponse createFile(CreateFileInfoDto request) {
        ensureFileDoesNotExist(request);
        FileInfo toBeSaved = fileMapper.toEntity(request);
        FileInfo saved = fileInfoRepository.save(toBeSaved);
        log.info(CREATED_FILE_INFO, saved.getName());
        return fileMapper.toResponse(saved);
    }

    private void ensureFileDoesNotExist(CreateFileInfoDto request) {
        if (fileInfoRepository.existsByName(request.name())) {
            throw new FileInfoAlreadyExistsException(request.name());
        }
    }
}
