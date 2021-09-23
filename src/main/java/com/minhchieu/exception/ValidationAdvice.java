package com.minhchieu.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Order((Ordered.HIGHEST_PRECEDENCE))
@ControllerAdvice
public class ValidationAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println("RETURNING ADVICE ERROR");
        BindingResult result = ex.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        String errorMessage = fieldErrors.get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(Map.of("message", errorMessage));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        return new ResponseEntity("You gave an incorrect value for ...." , HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Your object is not found"));
    }

}
