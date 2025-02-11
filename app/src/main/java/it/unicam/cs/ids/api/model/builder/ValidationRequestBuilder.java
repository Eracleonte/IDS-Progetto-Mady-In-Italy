package it.unicam.cs.ids.api.model.builder;

import it.unicam.cs.ids.api.dto.ValidationRequestDTO;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;

public class ValidationRequestBuilder {

    private ValidationRequest request;

    public ValidationRequestBuilder() {
        this.request = new ValidationRequest();
    }

    public ValidationRequest buildValidationRequestFromDTO(ValidationRequestDTO validationRequestDTO) {
        this.reset();
        this.setSupplyChainPointId(validationRequestDTO.getSupplyChainPointId());
        this.setContentId(validationRequestDTO.getContentId());
        this.setContentType(validationRequestDTO.getContentType());
        return this.getResult();
    }

    public void setSupplyChainPointId(int id) {
        request.setSupplyChainPointId(id);
    }

    public void setContentId(int id) {
        request.setContentId(id);
    }

    public void setContentType(String type) {
        request.setContentType(type);
    }

    public ValidationRequest getResult() {
        return this.request;
    }

    public void reset() {
        this.request = new ValidationRequest();
    }

}
