package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getListErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){ errors.add(new FieldMessage(fieldName, message)); }

}
