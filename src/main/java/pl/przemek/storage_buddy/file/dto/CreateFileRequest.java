package pl.przemek.storage_buddy.file.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateFileRequest(@NotEmpty String name) {}
