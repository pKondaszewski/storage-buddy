package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;

class FileMapperTest {

    private static final String FILENAME = "name.txt";
    private static final String OBJECT_KEY = "imagename.png";
    private static final String CONTENT_TYPE = "image/png";
    private static final CreateFileInfoDto CREATE_FILE_REQUEST =
            new CreateFileInfoDto(FILENAME, OBJECT_KEY, CONTENT_TYPE);
    private static final FileInfo FILE_INFO = new FileInfo();

    private final FileMapper mapper = new FileMapperImpl();

    @Test
    void shouldReturnNotNullResultDuringMappingToEntity() {
        // when
        FileInfo result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertNotNull(result);
    }

    @Test
    void shouldIdBeNullDuringMappingToEntity() {
        // when
        FileInfo result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertNull(result.getId());
    }

    @Test
    void shouldCorrectlyMapFieldsDuringMappingToEntity() {
        // when
        FileInfo result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertEquals(FILENAME, result.getName());
        assertEquals(OBJECT_KEY, result.getObjectKey());
        assertEquals(CONTENT_TYPE, result.getContentType());
        assertEquals(FileStatus.PENDING, result.getStatus());
    }

    @Test
    void shouldCorrectlyMapNullToEntity() {
        // when
        FileInfo result = mapper.toEntity(null);

        // then
        assertNull(result);
    }

    @Test
    void shouldCorrectlyMapNullToResponse() {
        // when
        CreatedFileInfoDto result = mapper.toResponse(null);

        // then
        assertNull(result);
    }

    @Test
    void shouldCorrectlyMapIdDuringMappingToResponse() {
        // given
        String objectKey = UUID.randomUUID().toString();
        String contentType = UUID.randomUUID().toString();
        Integer size = 1;
        FILE_INFO.setObjectKey(objectKey);
        FILE_INFO.setContentType(contentType);
        FILE_INFO.setSize(size);

        // when
        CreatedFileInfoDto result = mapper.toResponse(FILE_INFO);

        // then
        assertEquals(objectKey, result.objectKey());
        assertEquals(contentType, result.contentType());
        assertEquals(size, result.size());
    }
}
