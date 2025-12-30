package pl.przemek.storage_buddy.storage.config;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.InsufficientDataException;
import org.junit.jupiter.api.Test;

class MinioMigrationTest {

    private static final String BUCKET_NAME = "test-bucket";
    private static final BucketExistsArgs EXISTS_BUCKET_ARGS =
            BucketExistsArgs.builder().bucket(BUCKET_NAME).build();
    private static final MakeBucketArgs MAKE_BUCKET_ARGS =
            MakeBucketArgs.builder().bucket(BUCKET_NAME).build();

    private final MinioClient minioClient = mock(MinioClient.class);
    private final MinioProperties minioProperties = new MinioProperties(
            "endpoint", "accessKey", "secretKey", new MinioProperties.BucketProperties(BUCKET_NAME));
    private final MinioMigration minioMigration = new MinioMigration(minioClient, minioProperties);

    @Test
    void shouldCreateNonExistingBucket() throws Exception {
        // given
        when(minioClient.bucketExists(EXISTS_BUCKET_ARGS)).thenReturn(false);

        // when
        minioMigration.migrate();

        // then
        verify(minioClient, times(1)).makeBucket(MAKE_BUCKET_ARGS);
    }

    @Test
    void shouldNotCreateAlreadyExistingBucket() throws Exception {
        // given
        when(minioClient.bucketExists(EXISTS_BUCKET_ARGS)).thenReturn(true);

        // when
        minioMigration.migrate();

        // then
        verify(minioClient, times(0)).makeBucket(MAKE_BUCKET_ARGS);
    }

    @Test
    void throwExceptionWhenMinioClientReturnsException() throws Exception {
        // given
        when(minioClient.bucketExists(EXISTS_BUCKET_ARGS)).thenThrow(new InsufficientDataException(null));

        // when then
        assertThrows(InsufficientDataException.class, minioMigration::migrate);
    }
}
