package com.aeviles.helpdesk.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3260603479817898134L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
