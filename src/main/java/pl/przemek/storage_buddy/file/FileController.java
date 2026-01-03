package pl.przemek.storage_buddy.file;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.przemek.storage_buddy.file.dto.request.PresignedPostFormDataRequest;
import pl.przemek.storage_buddy.file.dto.response.PresignedPostFormDataResponse;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
class FileController {
    private final FileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PresignedPostFormDataResponse createPresignedPostFormData(
            // TODO: Jakarta Bean Validation
            @RequestBody PresignedPostFormDataRequest request) {
        return fileService.createPresignedPostFormData(request);
    }
}
