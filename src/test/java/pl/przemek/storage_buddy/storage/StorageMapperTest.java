package pl.przemek.storage_buddy.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.storage.dto.PresignedPostFormDataResponse;

class StorageMapperTest {

    private static final String KEY_VALUE = "value123abc";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final Map<String, String> MAP =
            Map.of("key", KEY_VALUE, "content-type", CONTENT_TYPE_VALUE, "unsupported-key", "unsupported-value");
    private final StorageMapper mapper = new StorageMapperImpl();

    @Test
    void shouldReturnNotNullResultDuringMappingToEntity() {
        // when
        PresignedPostFormDataResponse response = mapper.toResponse(MAP);

        // then
        assertNotNull(response);
    }

    @Test
    void shouldCorrectlyMapNullToResponse() {
        // when
        PresignedPostFormDataResponse response = mapper.toResponse(null);

        // then
        assertNull(response);
    }

    @Test
    void shouldCorrectlyMapFieldsDuringMappingToResponse() {
        // when
        PresignedPostFormDataResponse response = mapper.toResponse(MAP);

        // then
        assertEquals(KEY_VALUE, response.key());
        assertEquals(CONTENT_TYPE_VALUE, response.contentType());
    }
}
