package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.RawProductDTO;
import it.unicam.cs.ids.api.dto.ValidationRequestDTO;
import it.unicam.cs.ids.api.handlers.RawProductHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.builder.ValidationRequestBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.RawProductBuilder;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.List;

public class RawProductController {

    private RawProductHandler rawProductHandler;

    private ValidationRequestHandler validationRequestHandler;

    private RawProductBuilder rawProductBuilder;

    private ValidationRequestBuilder validationRequestBuilder;

    public RawProductController(RawProductHandler rawProductHandler,
                                ValidationRequestHandler validationRequestHandler) {
        this.rawProductHandler = rawProductHandler;
        this.validationRequestHandler = validationRequestHandler;
        this.rawProductBuilder = new RawProductBuilder();
        this.validationRequestBuilder = new ValidationRequestBuilder();
    }

    // CREATION

    public RawProduct addNewRawProduct(RawProductDTO rawProductDTO) {
        RawProduct rawProduct = this.rawProductHandler.saveRawProduct(
                this.rawProductBuilder.buildRawProductFromDTO(rawProductDTO));
        this.validationRequestHandler.saveValidationRequest(this.generateValidationRequestFrom(rawProductDTO));
        return rawProduct;
    }

    // READ

    public RawProduct getRawProductById(int id) {
        return this.rawProductHandler.findRawProductById(id);
    }

    public List<RawProduct> getRawProducts() {
        return this.rawProductHandler.findAllRawProducts();
    }

    // UTILITIES

    private ValidationRequest generateValidationRequestFrom(RawProductDTO rawProductDTO) {
        ValidationRequestDTO validationRequestDTO = new ValidationRequestDTO();
        validationRequestDTO.setSupplyChainPointId(rawProductDTO.getSupplyChainPointId());
        validationRequestDTO.setContentId(rawProductDTO.getId());
        validationRequestDTO.setContentType(rawProductDTO.getContentType());
        return this.validationRequestBuilder.buildValidationRequestFromDTO(validationRequestDTO);
    }

}
