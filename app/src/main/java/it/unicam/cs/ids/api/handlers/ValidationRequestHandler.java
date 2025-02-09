package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ValidationRequestHandler {

    private ValidationRequestRepository validationRequestRepository;

    public ValidationRequestHandler(ValidationRequestRepository validationRequestRepository) {
        this.validationRequestRepository = validationRequestRepository;
    }

    public ValidationRequest saveValidationRequest(ValidationRequest validationRequest) {
        if (validationRequest == null)
            throw new NullPointerException("Cannot save a null validation request");
        return this.validationRequestRepository.save(validationRequest);
    }

    public ValidationRequest findValidationRequestById(Integer id) {
        return this.validationRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find validation request with id: " + id));
    }

    public List<ValidationRequest> findAllValidationRequests() {
        return this.validationRequestRepository.findAll();
    }

}
