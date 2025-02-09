package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;

import java.util.List;

public class ValidationRequestController {

    private ValidationRequestHandler validationRequestHandler;

    public ValidationRequestController(ValidationRequestHandler validationRequestHandler) {
        this.validationRequestHandler = validationRequestHandler;
    }

    public List<ValidationRequest> getValidationRequests() {
        return this.validationRequestHandler.findAllValidationRequests();
    }

}
