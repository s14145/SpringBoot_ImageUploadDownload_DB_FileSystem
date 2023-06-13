package com.image.exception;

import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends RuntimeException{

    private HttpStatus httpStatus;

    public ImageNotFoundException(String message) {
       super(message);
    }

    public ImageNotFoundException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);
    }

    public ImageNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ImageNotFoundException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
