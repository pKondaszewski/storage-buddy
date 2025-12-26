package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.hibernate.AssertionFailure;
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

    private FileResponse createFile() {
        return fileService.createFile(CREATE_FILE_REQUEST);
    }

    @AfterEach
    void clearDb() {
        fileRepository.deleteAllInBatch();
    }

    @Test
    void shouldUseRepositoryDuringPersistingFile() {
        // when
        createFile();

        // then
        verify(fileRepository, times(1)).save(any());
    }

    @Test
    void shouldUseMapperDuringPersistingFile() {
        // when
        createFile();

        // then
        verify(fileMapper, times(1)).toEntity(CREATE_FILE_REQUEST);
    }

    @Test
    void shouldLogInfoAboutSuccessfulPersistence(CapturedOutput output) {
        // given
        String logMessage = "Successfully created file: %s".formatted(FILENAME);

        // when
        createFile();

        // then
        assertTrue(output.getOut().contains(logMessage));
    }

    @Test
    void shouldCreateAndPersistFile() {
        // when
        FileResponse result = createFile();

        // then
        assertTrue(fileRepository.existsById(result.id()));
    }

    @Test
    void shouldCreateFileWithFieldsFilled() {
        // when
        FileResponse result = createFile();

        // then
        File savedFile =
                fileRepository.findById(result.id()).orElseThrow(() -> new AssertionFailure("Expected existing file"));
        assertNotNull(result.id());
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
