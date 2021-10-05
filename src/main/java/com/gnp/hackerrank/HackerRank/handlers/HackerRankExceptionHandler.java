package com.gnp.hackerrank.HackerRank.handlers;

import com.gnp.hackerrank.HackerRank.exceptions.HackerRankException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.sql.SQLException;

@ControllerAdvice
public class HackerRankExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RequestError requestError = new RequestError();
        requestError.field = exception.getBindingResult().getFieldErrors().get(0).getField();
        requestError.message = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        requestError.value = exception.getBindingResult().getFieldErrors().get(0).getRejectedValue();
        return new ResponseEntity<>(requestError, status);
    }

    @ExceptionHandler({
            JpaSystemException.class,
            SQLException.class,
            TransactionSystemException.class
    })
    public ResponseEntity<JpaError> handleJpaSystemException(Exception ex) {
        JpaError error = new JpaError();
        error.message = ex.getMessage();
        error.clazz = ex.getClass();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<HackerRankError> handleHackerRankException(HackerRankException ex) {
        HackerRankError error = new HackerRankError();
        error.message = ex.getMessage();
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<HackerRankError> handleException(Exception ex) {
        HackerRankError error = new HackerRankError();
        error.message = ex.getMessage();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

class JpaError {
    public String message;
    public Object clazz;
}

class RequestError {
    public String field;
    public Object value;
    public String message;
}

class HackerRankError {
    public String message;
}