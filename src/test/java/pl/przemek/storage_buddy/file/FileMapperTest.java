package pl.przemek.storage_buddy.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.file.dto.CreateFileRequest;

class FileMapperTest {

    private final FileMapper mapper = new FileMapperImpl();

    private static final String FILENAME = "name.txt";
    private static final CreateFileRequest CREATE_FILE_REQUEST = new CreateFileRequest(FILENAME);

    @Test
    void shouldReturnNotNullResultDuringMappingToEntity() {
        // when
        File result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertNotNull(result);
    }

    @Test
    void shouldIdBeNullDuringMappingToEntity() {
        // when
        File result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertNull(result.getId());
    }

    @Test
    void shouldCorrectlyMapFilenameDuringMappingToEntity() {
        // when
        File result = mapper.toEntity(CREATE_FILE_REQUEST);

        // then
        assertEquals(FILENAME, result.getName());
    }
}
