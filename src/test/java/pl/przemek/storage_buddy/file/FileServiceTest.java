package pl.przemek.storage_buddy.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileServiceTest {

    private final FileRepository fileRepository = new InMemoryFileRepository();
    private final FileMapper fileMapper = new FileMapperImpl();
    private final FileService fileService = new FileService(fileRepository, fileMapper);

    private static final String FILENAME = "name.txt";
    private static final CreateFileRequest CREATE_FILE_REQUEST = new CreateFileRequest(FILENAME);

    @AfterEach
    void clearDb() {
        fileRepository.deleteAllInBatch();
    }

    @Test
    void shouldCreateFile() {
        // when
        FileResponse result = fileService.createFile(CREATE_FILE_REQUEST);

        // then
        assertNotNull(result.id());
    }

    @Test
    void shouldCreateAndPersistFile() {
        // when
        FileResponse result = fileService.createFile(CREATE_FILE_REQUEST);

        // then
        assertTrue(fileRepository.existsById(result.id()));
    }

    @Test
    void shouldCreateFileWithCorrectName() {
        // when
        FileResponse result = fileService.createFile(CREATE_FILE_REQUEST);

        // then
        File savedFile = fileRepository.findById(result.id()).get();
        assertEquals(FILENAME, savedFile.getName());
    }
}
