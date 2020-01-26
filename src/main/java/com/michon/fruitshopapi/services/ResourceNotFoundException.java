package com.michon.fruitshopapi.services;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String messgae, Throwable cause, boolean enableSupression, boolean writeStackTrace) {
        super(messgae, cause, enableSupression, writeStackTrace);
    }

}
