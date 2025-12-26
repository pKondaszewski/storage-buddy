package pl.przemek.storage_buddy.file;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface FileInfoRepository extends JpaRepository<FileInfo, UUID> {
    boolean existsByName(@NotEmpty String name);
}
