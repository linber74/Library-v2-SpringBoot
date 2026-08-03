package se.linda.library.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ItemNotFoundException ex){
        return buildResponse(ex.getMessage(), 404);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex){
        return buildResponse(ex.getMessage(), 400);
    }

    private ResponseEntity<ErrorResponse> buildResponse (String message, int status){
        ErrorResponse error = new ErrorResponse(message, status);
        return ResponseEntity.status(status).body(error);
    }


}
