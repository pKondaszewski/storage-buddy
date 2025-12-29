package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoDto;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoResponse;

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
        CreatedFileInfoResponse result = mapper.toResponse(null);

        // then
        assertNull(result);
    }

    @Test
    void shouldCorrectlyMapIdDuringMappingToResponse() {
        // given
        UUID expected = UUID.randomUUID();
        FILE_INFO.setId(expected);

        // when
        CreatedFileInfoResponse result = mapper.toResponse(FILE_INFO);

        // then
        assertEquals(expected, result.id());
    }
}
