package pl.przemek.storage_buddy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfig.class)
class StorageBuddyApplicationTests {

    @Test
    void contextLoads() {}
}
