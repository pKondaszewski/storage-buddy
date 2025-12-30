package pl.przemek.storage_buddy.storage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageBucketProperties.class)
class StorageConfig {}
