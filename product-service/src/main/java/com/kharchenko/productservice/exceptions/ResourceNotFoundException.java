package com.kharchenko.productservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String errorMessage;

    public ResourceNotFoundException(String message) {
        super(message);
        this.setErrorMessage(message);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
