package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;
import pl.przemek.storage_buddy.file.dto.FileResponse;
import pl.przemek.storage_buddy.file.exception.FileAlreadyExistsException;

@ExtendWith(OutputCaptureExtension.class)
class FileServiceTest {

    private final FileRepository fileRepository = spy(new InMemoryFileRepository());
    private final FileMapper fileMapper = spy(new FileMapperImpl());
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
    void shouldUseRepositoryDuringPersistingFile() {
        // when
        fileService.createFile(CREATE_FILE_REQUEST);

        // then
        verify(fileRepository, times(1)).save(any());
    }

    @Test
    void shouldUseMapperDuringPersistingFile() {
        // when
        fileService.createFile(CREATE_FILE_REQUEST);

        // then
        verify(fileMapper, times(1)).toEntity(CREATE_FILE_REQUEST);
    }

    @Test
    void shouldLogInfoAboutSuccessfulPersistence(CapturedOutput output) {
        // given
        String logMessage =
                "INFO pl.przemek.storage_buddy.file.FileService -- Successfully created file: %s".formatted(FILENAME);

        // when
        fileService.createFile(CREATE_FILE_REQUEST);

        // then
        assertTrue(output.getOut().contains(logMessage));
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

    @Test
    void shouldThrowFileAlreadyExistsExceptionWhenFileWithGivenFilenameAlreadyExists() {
        // given
        File toBeSaved = fileMapper.toEntity(CREATE_FILE_REQUEST);
        fileRepository.save(toBeSaved);

        // when
        FileAlreadyExistsException thrown =
                assertThrows(FileAlreadyExistsException.class, () -> fileService.createFile(CREATE_FILE_REQUEST));

        // then
        String expected = FileAlreadyExistsException.ERROR_MESSAGE.formatted(FILENAME);
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void shouldNotSaveFileWhenFileWithGivenFilenameAlreadyExists() {
        // given
        File toBeSaved = fileMapper.toEntity(CREATE_FILE_REQUEST);
        fileRepository.save(toBeSaved);

        // when
        assertThrows(FileAlreadyExistsException.class, () -> fileService.createFile(CREATE_FILE_REQUEST));

        // then
        verify(fileRepository, times(1)).save(toBeSaved);
    }
}
