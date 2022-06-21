package com.abhi.bloggerbook.Exceptions;

import com.abhi.bloggerbook.Payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException e){
        ApiResponse respone= new ApiResponse(e.getMessage(),false);
        return new ResponseEntity<>(respone, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e)
    {
        Map<String,String> errors= new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName= ((FieldError)error).getField();
            errors.put(fieldName,error.getDefaultMessage());
    });

        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
