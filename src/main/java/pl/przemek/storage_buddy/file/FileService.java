package pl.przemek.storage_buddy.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

@Slf4j
@RequiredArgsConstructor
class FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileResponse createFile(CreateFileRequest request) {
        File toBeSaved = fileMapper.toEntity(request);
        File saved = fileRepository.save(toBeSaved);
        log.info("Successfully created file: {}", saved.getName());
        return new FileResponse(saved.getId());
    }
}
