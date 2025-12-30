package pl.przemek.storage_buddy.common.helper.time;

import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
class TimeHelperImpl implements TimeHelper {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
