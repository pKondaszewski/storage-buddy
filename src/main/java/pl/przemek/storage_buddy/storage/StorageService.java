package pl.przemek.storage_buddy.storage;

import io.minio.MinioClient;
import io.minio.PostPolicy;
import java.time.ZoneId;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przemek.storage_buddy.common.helper.time.TimeHelper;
import pl.przemek.storage_buddy.file.dto.CreatedFileInfoDto;
import pl.przemek.storage_buddy.file.dto.response.PresignedPostFormDataResponse;
import pl.przemek.storage_buddy.storage.exception.StorageClientException;

@Service
@RequiredArgsConstructor
class StorageService implements StorageFacade {

    private static final String KEY = "key";
    private static final String CONTENT_TYPE = "content-type";

    private final StorageMapper mapper;
    private final MinioClient minioClient;
    private final TimeHelper timeHelper;
    private final StorageBucketProperties bucket;

    @Override
    public PresignedPostFormDataResponse generatePresignedPostFormData(CreatedFileInfoDto dto) {
        PostPolicy postPolicy = generatePostPolicy(dto);
        return getPresignedPostFormData(postPolicy, dto);
    }

    private PostPolicy generatePostPolicy(CreatedFileInfoDto fileInfo) {
        PostPolicy policy = new PostPolicy(bucket.name(), timeHelper.now().atZone(ZoneId.systemDefault()));
        policy.addEqualsCondition(KEY, fileInfo.objectKey());
        policy.addEqualsCondition(CONTENT_TYPE, fileInfo.contentType());
        policy.addContentLengthRangeCondition(fileInfo.size(), fileInfo.size());
        return policy;
    }

    private PresignedPostFormDataResponse getPresignedPostFormData(PostPolicy policy, CreatedFileInfoDto fileInfo) {
        Map<String, String> presignedPostFormData = fetchPresignedPostFormDataFromStorageClient(policy);
        presignedPostFormData.put(KEY, fileInfo.objectKey());
        presignedPostFormData.put(CONTENT_TYPE, fileInfo.contentType());
        return mapper.toResponse(presignedPostFormData);
    }

    private Map<String, String> fetchPresignedPostFormDataFromStorageClient(PostPolicy policy) {
        try {
            return minioClient.getPresignedPostFormData(policy);
        } catch (Exception exception) {
            throw new StorageClientException(exception);
        }
    }
}
