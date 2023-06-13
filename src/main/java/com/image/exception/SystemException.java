package com.image.exception;

public class SystemException extends RuntimeException{

    public SystemException() {
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
