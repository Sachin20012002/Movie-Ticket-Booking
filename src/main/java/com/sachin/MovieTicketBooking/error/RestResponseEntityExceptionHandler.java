package com.sachin.MovieTicketBooking.error;


import com.sachin.MovieTicketBooking.entity.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> Exception(Exception exception, WebRequest request) {
        ErrorMessage errorMessage;
        if (exception instanceof NotFoundException){
            errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        }
        else {
             errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);

    }
}

