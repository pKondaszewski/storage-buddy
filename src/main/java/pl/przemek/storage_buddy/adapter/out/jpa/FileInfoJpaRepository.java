package pl.przemek.storage_buddy.adapter.out.jpa;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface FileInfoJpaRepository extends JpaRepository<FileInfoJpaEntity, UUID> {
    boolean existsByName(@NotEmpty String name);
}
