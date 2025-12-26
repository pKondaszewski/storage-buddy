package pl.przemek.storage_buddy.file;

import lombok.RequiredArgsConstructor;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

@RequiredArgsConstructor
class FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileResponse createFile(CreateFileRequest request) {
        File saved = fileRepository.save(new File(null, request.name()));
        return new FileResponse(saved.getId());
    }
}
