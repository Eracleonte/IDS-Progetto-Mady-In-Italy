package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.model.contents.ValidationRequest;

import java.util.*;

public class ValidationRequestRepository implements Repository<ValidationRequest,Integer> {

    private Map<Integer, ValidationRequest> validationRequestMap;

    private int nextValidationRequestId;

    public ValidationRequestRepository() {
        this.validationRequestMap = new HashMap<>();
        this.nextValidationRequestId = 1;
    }

    @Override
    public ValidationRequest save(ValidationRequest element) {
        element.setId(this.nextValidationRequestId);
        this.validationRequestMap.put(element.getId(), element);
        this.nextValidationRequestId++;
        return element;
    }

    @Override
    public Optional<ValidationRequest> findById(Integer id) {
        return Optional.ofNullable(validationRequestMap.get(id));
    }

    @Override
    public List<ValidationRequest> findAll() {
        return new ArrayList<>(validationRequestMap.values());
    }

}
