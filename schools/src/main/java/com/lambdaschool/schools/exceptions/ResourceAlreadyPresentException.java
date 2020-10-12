package com.lambdaschool.schools.exceptions;

public class ResourceAlreadyPresentException extends RuntimeException{
    public ResourceAlreadyPresentException(String message) {
        super("Exception in School: " + message);
    }
}
