package com.image.handler;

import com.image.dto.ErrorMessage;
import com.image.exception.ImageNotFoundException;
import com.image.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleImageNotFoundExcepton(ImageNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorMessage> handleSystemException(SystemException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            errorMap.put(fieldName, message);
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}