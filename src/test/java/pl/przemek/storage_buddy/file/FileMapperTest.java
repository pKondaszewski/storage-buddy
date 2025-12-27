package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileInfoRequest;

class FileMapperTest {

    private static final String FILENAME = "name.txt";
    private static final CreateFileInfoRequest CREATE_FILE_REQUEST = new CreateFileInfoRequest(FILENAME);

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
}
