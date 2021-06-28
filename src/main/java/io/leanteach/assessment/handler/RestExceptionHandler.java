package io.leanteach.assessment.handler;

import io.leanteach.assessment.dto.HttpResponseDto;
import io.leanteach.assessment.exception.AlreadyExistsException;
import io.leanteach.assessment.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);


    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(CONFLICT);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleAlreadyExistsException(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(new HttpResponseDto(false, apiError), apiError.getStatus());
    }

}
