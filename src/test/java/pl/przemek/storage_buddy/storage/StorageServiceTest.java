package pl.przemek.storage_buddy.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.minio.MinioClient;
import io.minio.errors.InternalException;
import java.time.Instant;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import pl.przemek.storage_buddy.common.helper.time.TimeHelper;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.storage.exception.StorageClientException;

class StorageServiceTest {
    private final StorageMapper storageMapper = spy(new StorageMapperImpl());
    private final MinioClient minioClient = mock(MinioClient.class);
    private final TimeHelper timeHelper = mock(TimeHelper.class);
    private final StorageBucketProperties bucket = new StorageBucketProperties("cloud-storage-bucket", 5);
    private final StorageService storageService = new StorageService(storageMapper, minioClient, timeHelper, bucket);

    @Test
    void shouldGenerateCorrectPresignedPostFormData() throws Exception {
        // given
        CreatedFileInfoDto dto = new CreatedFileInfoDto("objectKey.txt", "plain/text", 100);
        when(timeHelper.now()).thenReturn(Instant.now());
        when(minioClient.getPresignedPostFormData(any())).thenReturn(new HashMap<>());

        // when
        var result = storageService.generatePresignedPostFormData(dto);

        // then
        assertNotNull(result);
        verify(minioClient, times(1)).getPresignedPostFormData(any());
        verify(storageMapper, times(1)).toResponse(any());
        assertEquals(dto.objectKey(), result.key());
        assertEquals(dto.contentType(), result.contentType());
    }

    @Test
    void shouldThrowCorrectExceptionWhenStorageClientThrowsException() throws Exception {
        // given
        CreatedFileInfoDto dto = new CreatedFileInfoDto("objectKey.txt", "plain/text", 100);
        when(timeHelper.now()).thenReturn(Instant.now());
        when(minioClient.getPresignedPostFormData(any())).thenThrow(InternalException.class);

        // when then
        assertThrows(StorageClientException.class, () -> storageService.generatePresignedPostFormData(dto));
    }
}
