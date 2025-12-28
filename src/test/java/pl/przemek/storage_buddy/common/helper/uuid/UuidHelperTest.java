package pl.przemek.storage_buddy.common.helper.uuid;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UuidHelperTest {

    private final UuidHelper uuidHelper = new UuidHelperImpl();

    @Test
    void shouldGenerateValidUuidAsString() {
        // when
        String result = uuidHelper.randomAsString();

        // then
        assertNotNull(result);
        assertDoesNotThrow(() -> UUID.fromString(result));
    }

    @Test
    void shouldGenerateUniqueUuidValues() {
        // given
        int count = 10;
        Set<String> uuids = new HashSet<>();

        // when
        for (int i = 0; i < count; i++) {
            uuids.add(uuidHelper.randomAsString());
        }

        // then
        assertEquals(count, uuids.size());
    }
}
