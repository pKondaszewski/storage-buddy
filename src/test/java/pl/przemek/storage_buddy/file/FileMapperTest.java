package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoRequest;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoResponse;

class FileMapperTest {

    private static final String FILENAME = "name.txt";
    private static final CreateFileInfoRequest CREATE_FILE_REQUEST = new CreateFileInfoRequest(FILENAME);
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
    void shouldCorrectlyMapFilenameDuringMappingToEntity() {
        // when
        FileInfo result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertEquals(FILENAME, result.getName());
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
