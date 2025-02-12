package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.output.OutputValidationRequestDTO;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class ValidationRequestHandler {

    private ValidationRequestRepository validationRequestRepository;

    public ValidationRequestHandler(ValidationRequestRepository validationRequestRepository) {
        this.validationRequestRepository = validationRequestRepository;
    }

    public int saveValidationRequest(ValidationRequest validationRequest) {
        if (validationRequest == null)
            throw new NullPointerException("Cannot save a null validation request");
        return this.validationRequestRepository.save(validationRequest).getId();
    }

    public OutputValidationRequestDTO findValidationRequestById(Integer id) {
        ValidationRequest validationRequest = this.validationRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find validation request with id: " + id));
        return validationRequest.getOutputValidationRequestDTO();
    }

    public List<OutputValidationRequestDTO> findAllValidationRequests() {
        return this.validationRequestRepository.findAll()
                .stream()
                .map(ValidationRequest::getOutputValidationRequestDTO)
                .toList();
    }

}
