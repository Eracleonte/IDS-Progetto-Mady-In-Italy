package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.model.contents.ValidationRequest;

import java.util.*;

public class ValidationRequestRepository implements Repository<ValidationRequest,Integer> {

    private Map<Integer, ValidationRequest> validationRequestMap;

    public ValidationRequestRepository() {
        this.validationRequestMap = new HashMap<>();
    }

    @Override
    public ValidationRequest save(ValidationRequest element) {
        this.validationRequestMap.put(element.getId(), element);
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
