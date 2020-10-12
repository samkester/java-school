package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{
    @Override
    public List<ValidationError> getConstraintViolations(Throwable cause) {
        List<ValidationError> result = new ArrayList<>();

        while(cause != null && !(cause instanceof ConstraintViolationException || cause instanceof MethodArgumentNotValidException))
        {
            cause = cause.getCause();
        }

        if (cause != null)
        {
            if(cause instanceof ConstraintViolationException)
            {
                ConstraintViolationException ex = (ConstraintViolationException)cause;
                result.add(new ValidationError(ex.getMessage(), ex.getConstraintName()));
            }
            else
            {
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException)cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for (FieldError fe: fieldErrors)
                {
                    result.add(new ValidationError(fe.getField(), fe.getDefaultMessage()));
                }
            }
        }

        return result;
    }
}
