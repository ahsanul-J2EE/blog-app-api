package com.ahsanul.blogappapi.exceptions;


import com.ahsanul.blogappapi.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String messages = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(messages,false);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }


}
