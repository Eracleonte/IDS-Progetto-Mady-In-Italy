package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.output.OutputValidationRequestDTO;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;

import java.util.List;

public class ValidationRequestController {

    private ValidationRequestHandler validationRequestHandler;

    public ValidationRequestController(ValidationRequestHandler validationRequestHandler) {
        this.validationRequestHandler = validationRequestHandler;
    }

    public OutputValidationRequestDTO findValidationRequestById(Integer id) {
        return this.validationRequestHandler.findValidationRequestById(id);
    }

    public List<OutputValidationRequestDTO> getValidationRequests() {
        return this.validationRequestHandler.findAllValidationRequests();
    }

}
