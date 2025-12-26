package pl.przemek.storage_buddy.file.exception;

public class FileInfoAlreadyExistsException extends RuntimeException {

    public static final String ERROR_MESSAGE = "File info with filename: %s already exists";

    public FileInfoAlreadyExistsException(String filename) {
        super(ERROR_MESSAGE.formatted(filename));
    }
}
