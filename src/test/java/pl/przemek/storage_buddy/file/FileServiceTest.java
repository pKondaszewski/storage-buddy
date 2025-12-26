package pl.przemek.storage_buddy.file;

import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.FileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileServiceTest {

    private final FileRepository fileRepository = new InMemoryFileRepository();
    private final FileService fileService = new FileService(fileRepository);

    private static final String FILENAME = "filename.txt";

    @Test
    void shouldCreateFile() {
        // given
        FileRequest request = new FileRequest(FILENAME);

        // when
        FileResponse response = fileService.createFile(request);

        // then
        assertNotNull(response.id());
    }

    @Test
    void shouldCreateAndPersistFile() {
        // given
        FileRequest request = new FileRequest(FILENAME);

        // when
        FileResponse response = fileService.createFile(request);

        // then
        assertTrue(fileRepository.existsById(response.id()));
    }

    @Test
    void shouldCreateFileWithCorrectName() {
        // given
        FileRequest request = new FileRequest(FILENAME);

        // when
        FileResponse response = fileService.createFile(request);

        // then
        File savedFile = fileRepository.findById(response.id()).get();
        assertEquals(FILENAME, savedFile.getName());
    }
}
