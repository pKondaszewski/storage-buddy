package pl.przemek.storage_buddy.common.helper.time;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class TimeHelperTest {

    private final TimeHelper timeHelper = new TimeHelperImpl();

    @Test
    void shouldReturnNonNullInstant() {
        // when
        Instant result = timeHelper.now();

        // then
        assertNotNull(result);
    }

    @Test
    void shouldReturnInstantCloseToCurrentTime() {
        // given
        Instant before = Instant.now();

        // when
        Instant result = timeHelper.now();

        // then
        Instant after = Instant.now();
        assertFalse(result.isBefore(before.minusMillis(10)));
        assertFalse(result.isAfter(after.plusMillis(10)));
    }
}
