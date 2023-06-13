package com.image.exception;

import org.springframework.http.HttpStatus;

public class ImageAlreadyExists extends RuntimeException{

    private HttpStatus httpStatus;

    public ImageAlreadyExists() {
    }

    public ImageAlreadyExists(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ImageAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
