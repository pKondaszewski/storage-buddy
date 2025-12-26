package pl.przemek.storage_buddy.file;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface FileRepository extends JpaRepository<File, UUID> {}
