package com.lambdaschool.schools.models;

public class ValidationError {
    private String key;
    private String message;

    public ValidationError(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public ValidationError() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return key + ": " + message;
    }
}
