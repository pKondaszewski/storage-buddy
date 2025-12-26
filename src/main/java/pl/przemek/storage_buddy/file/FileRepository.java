package pl.przemek.storage_buddy.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface FileRepository extends JpaRepository<File, UUID> {
}
