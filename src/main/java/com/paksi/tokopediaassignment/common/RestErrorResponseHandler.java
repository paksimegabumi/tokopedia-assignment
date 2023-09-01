package com.paksi.tokopediaassignment.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorResponseHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errorField = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toList());
  
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors, errorField), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getErrorsMap(List<String> errors, List<String> errorFields) {
        Map<String, String> errorResponse = new HashMap<>();
        int index = 0;
        for (String field : errorFields) {
            errorResponse.put(field, errors.get(index));
            index++;
        }
        return errorResponse;
    }
}
