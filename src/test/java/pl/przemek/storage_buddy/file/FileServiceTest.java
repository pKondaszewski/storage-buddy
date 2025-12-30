package pl.przemek.storage_buddy.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.file.exception.FileInfoAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.przemek.storage_buddy.common.LogMessages.CREATED_FILE_INFO;

@ExtendWith(OutputCaptureExtension.class)
class FileServiceTest {

    private static final String FILENAME = "name.txt";
    private static final CreateFileInfoDto CREATE_FILE_REQUEST =
            new CreateFileInfoDto(FILENAME, "objectKey.txt", "plain/text");

    private final FileInfoRepository fileInfoRepository = spy(new InMemoryFileInfoRepository());
    private final FileMapper fileMapper = spy(new FileMapperImpl());
    private final FileService fileService = new FileService(fileInfoRepository, fileMapper);

    private CreatedFileInfoDto createFile() {
        return fileService.createFile(CREATE_FILE_REQUEST);
    }

    @AfterEach
    void clearDb() {
        fileInfoRepository.deleteAllInBatch();
    }

    @Test
    void shouldUseRepositoryDuringPersistingFile() {
        // when
        createFile();

        // then
        verify(fileInfoRepository, times(1)).save(any());
    }

    @Test
    void shouldUseMapperDuringPersistingFile() {
        // when
        createFile();

        // then
        verify(fileMapper, times(1)).toEntity(CREATE_FILE_REQUEST);
        verify(fileMapper, times(1)).toResponse(any());
    }

    @Test
    void shouldLogInfoAboutSuccessfulPersistence(CapturedOutput output) {
        // given
        String expectedMessage =
                MessageFormatter.format(CREATED_FILE_INFO, FILENAME).getMessage();

        // when
        createFile();

        // then
        assertTrue(output.getOut().contains(expectedMessage));
    }

    @Test
    void shouldCreateAndPersistFile() {
        // when
        createFile();

        // then
        assertEquals(1, fileInfoRepository.count());
    }

    @Test
    void shouldCreateFileWithFieldsFilled() {
        // when
        createFile();

        // then
        FileInfo savedFileInfo = fileInfoRepository.findAll().getFirst();
        assertNotNull(savedFileInfo.getId());
        assertEquals(FILENAME, savedFileInfo.getName());
    }

    @Test
    void shouldThrowFileAlreadyExistsExceptionWhenFileWithGivenFilenameAlreadyExists() {
        // given
        FileInfo toBeSaved = fileMapper.toEntity(CREATE_FILE_REQUEST);
        fileInfoRepository.save(toBeSaved);

        // when
        FileInfoAlreadyExistsException thrown =
                assertThrows(FileInfoAlreadyExistsException.class, () -> fileService.createFile(CREATE_FILE_REQUEST));

        // then
        String expected = FileInfoAlreadyExistsException.ERROR_MESSAGE.formatted(FILENAME);
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void shouldNotSaveFileWhenFileWithGivenFilenameAlreadyExists() {
        // given
        FileInfo toBeSaved = fileMapper.toEntity(CREATE_FILE_REQUEST);
        fileInfoRepository.save(toBeSaved);

        // when
        assertThrows(FileInfoAlreadyExistsException.class, () -> fileService.createFile(CREATE_FILE_REQUEST));

        // then
        verify(fileInfoRepository, times(1)).save(toBeSaved);
    }
}
