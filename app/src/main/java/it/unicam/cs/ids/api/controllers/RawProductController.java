package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.RawProductDTO;
import it.unicam.cs.ids.api.handlers.RawProductHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.RawProductBuilder;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;

import java.util.List;

public class RawProductController {

    private RawProductHandler rawProductHandler;

    private ValidationRequestHandler validationRequestHandler;

    private RawProductBuilder rawProductBuilder;

    private int nextValidationRequestId;

    public RawProductController(RawProductHandler rawProductHandler,
                                ValidationRequestHandler validationRequestHandler) {
        this.rawProductHandler = rawProductHandler;
        this.validationRequestHandler = validationRequestHandler;
        this.rawProductBuilder = new RawProductBuilder();
        this.nextValidationRequestId = 1;
    }

    // CREATION

    public RawProduct addNewRawProduct(RawProductDTO rawProductDTO) {
        RawProduct rawProduct = this.rawProductHandler.saveRawProduct(
                this.rawProductBuilder.buildRawProductFromDTO(rawProductDTO));
        ValidationRequest validationRequest = this.validationRequestHandler.saveValidationRequest(
                generateValidationRequestFrom(rawProductDTO));
        this.nextValidationRequestId = validationRequest.getId() + 1;
        return rawProduct;
    }

    // READ

    public List<RawProduct> getRawProducts() {
        return this.rawProductHandler.findAllRawProducts();
    }

    // UTILITIES

    private ValidationRequest generateValidationRequestFrom(RawProductDTO rawProductDTO) {
        return new ValidationRequest(this.nextValidationRequestId,
                rawProductDTO.getSupplyChainPointId(),
                rawProductDTO.getId(),
                rawProductDTO.getContentType());
    }

}
