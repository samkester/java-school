package com.lambdaschool.schools.exceptions;

public class UnknownEndpointException extends RuntimeException {
    public UnknownEndpointException(String message) {
        super("Exception in School: " + message);
    }
}
