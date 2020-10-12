package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes {
    @Autowired
    HelperFunctions helperFunctions;

    // title: name of exception
    // status: HTTP header
    // detail: description of what specifically went wrong (human-readable)
    // timestamp
    // developerMessage: debug info, e.g., class name
    // errors: validation errors

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace)
    {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String, Object> result = new LinkedHashMap<>();

        result.put("title", errorAttributes.get("error"));
        result.put("status", errorAttributes.get("status"));
        result.put("detail", errorAttributes.get("message"));
        result.put("timestamp", errorAttributes.get("timestamp"));
        result.put("developerMessage", "path: " + errorAttributes.get("path"));
        result.put("errors", helperFunctions.getConstraintViolations(this.getError(webRequest)));

        return result;
    }
}
