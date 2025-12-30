package pl.przemek.storage_buddy.storage;

import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.przemek.storage_buddy.storage.dto.PresignedPostFormDataResponse;

@Mapper
interface StorageMapper {
    @Mapping(target = "key", expression = "java(input.get(\"key\"))")
    @Mapping(target = "contentType", expression = "java(input.get(\"content-type\"))")
    PresignedPostFormDataResponse toResponse(Map<String, String> input);
}
