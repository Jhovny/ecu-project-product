package ecu.project.product.infraestructure.exception;

import ecu.project.product.web.controller.MovementController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // specific exception
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException
            (ResourceNotFoundException exception, WebRequest request){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
    log.error(request.getContextPath(),exception);
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // specific general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException
    (Exception exception, WebRequest request){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        log.error(request.getContextPath(),exception);
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // specific api
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleApiException
    (APIException exception, WebRequest request){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        log.error(request.getContextPath(),exception);
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
}
