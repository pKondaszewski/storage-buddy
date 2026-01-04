package pl.przemek.storage_buddy.file.port.in;

public interface FileInputPort {
    PresignedPostFormDataResponse createPresignedPostFormData(PresignedPostFormDataRequest request);
}
