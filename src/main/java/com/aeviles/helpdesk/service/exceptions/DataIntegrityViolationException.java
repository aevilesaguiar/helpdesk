package com.aeviles.helpdesk.service.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

    private static final long serialVersionUID = -3260603479817898134L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
