package dev.gabiqassis.serasa.client.exception;

public class OrderServiceException extends RuntimeException {

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceException(String message) {
        super(message);
    }
}
