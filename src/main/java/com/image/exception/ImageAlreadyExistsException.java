package com.image.exception;

import org.springframework.http.HttpStatus;

public class ImageAlreadyExistsException extends RuntimeException{

    private HttpStatus httpStatus;

    public ImageAlreadyExistsException() {
    }

    public ImageAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ImageAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
