package com.lambdaschool.schools.handlers;

import com.lambdaschool.schools.exceptions.ResourceAlreadyPresentException;
import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private HelperFunctions helperFunctions;

    public RestExceptionHandler()
    {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandler(ResourceNotFoundException exception)
    {
        ErrorDetail detail = new ErrorDetail();
        detail.setDetail(exception.getMessage());
        detail.setDeveloperMessage(exception.getClass().getName());
        detail.setErrors(helperFunctions.getConstraintViolations(exception));
        detail.setStatus(HttpStatus.NOT_FOUND.value());
        detail.setTimestamp(new Date());
        detail.setTitle("Resource Not Found");

        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyPresentException.class)
    public ResponseEntity<?> resourceAlreadyPresentHandler(ResourceAlreadyPresentException exception)
    {
        ErrorDetail detail = new ErrorDetail();
        detail.setDetail(exception.getMessage());
        detail.setDeveloperMessage(exception.getClass().getName());
        detail.setErrors(helperFunctions.getConstraintViolations(exception));
        detail.setStatus(HttpStatus.CONFLICT.value());
        detail.setTimestamp(new Date());
        detail.setTitle("Resource Already Present");

        return new ResponseEntity<>(detail, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail detail = new ErrorDetail();
        detail.setDetail("Exception in School: " + ex.getMessage());
        detail.setDeveloperMessage(ex.getClass().getName());
        detail.setErrors(helperFunctions.getConstraintViolations(ex));
        detail.setStatus(status.value());
        detail.setTimestamp(new Date());
        detail.setTitle("Server Error");

        return new ResponseEntity<>(detail, status);
    }
}
