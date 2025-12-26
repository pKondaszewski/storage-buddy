package pl.przemek.storage_buddy.file.exception;

public class FileAlreadyExistsException extends RuntimeException {

    public static final String ERROR_MESSAGE = "File with filename: %s already exists";

    public FileAlreadyExistsException(String filename) {
        super(ERROR_MESSAGE.formatted(filename));
    }
}
