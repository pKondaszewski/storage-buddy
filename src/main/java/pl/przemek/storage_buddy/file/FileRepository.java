package pl.przemek.storage_buddy.file;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface FileRepository extends JpaRepository<File, UUID> {
    boolean existsByName(@NotEmpty String name);
}
