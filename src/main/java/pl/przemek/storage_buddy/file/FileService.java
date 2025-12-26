package pl.przemek.storage_buddy.file;

import lombok.RequiredArgsConstructor;
import pl.przemek.storage_buddy.file.dto.FileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

@RequiredArgsConstructor
class FileService {
    private final FileRepository fileRepository;

    public FileResponse createFile(FileRequest request) {
        File saved = fileRepository.save(new File(null, request.filename()));
        return new FileResponse(saved.getId());
    }
}
