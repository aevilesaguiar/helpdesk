package com.aeviles.helpdesk.controller.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable{

    private static final long serialVersionUID = -1813588164058069631L;

    private List<FieldMessage> errors=new ArrayList<>();

    public ValidationError() {

    }


    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName,message));
    }
}
