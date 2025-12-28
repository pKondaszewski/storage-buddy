package pl.przemek.storage_buddy.common.helper.uuid;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class UuidHelperImpl implements UuidHelper {
    @Override
    public String randomAsString() {
        return UUID.randomUUID().toString();
    }
}
