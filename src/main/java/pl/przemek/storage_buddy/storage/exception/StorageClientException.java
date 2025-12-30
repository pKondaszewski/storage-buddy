package pl.przemek.storage_buddy.storage.exception;

public class StorageClientException extends RuntimeException {

    public static final String ERROR_MESSAGE = "There are some unexpected problems with storage client";

    public StorageClientException(Throwable throwable) {
        super(ERROR_MESSAGE, throwable);
    }
}
