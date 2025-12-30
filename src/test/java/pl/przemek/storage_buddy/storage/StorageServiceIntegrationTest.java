package pl.przemek.storage_buddy.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.przemek.storage_buddy.common.TestcontainersConfig;

@SpringBootTest
class StorageServiceIntegrationTest extends TestcontainersConfig {

    @Autowired
    private StorageService storageService;

    @Test
    void test() {
        //        minioService.generatePresignedPostPolicy();
    }
}
