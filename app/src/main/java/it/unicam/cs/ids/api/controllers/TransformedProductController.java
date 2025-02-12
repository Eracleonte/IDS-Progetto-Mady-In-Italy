package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.TransformedProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputValidationRequestDTO;
import it.unicam.cs.ids.api.handlers.TransformedProductHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.builder.ValidationRequestBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.TransformedProductBuilder;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;


import java.util.List;

public class TransformedProductController {

    // TODO refactor

    /**

    private TransformedProductHandler transformedProductHandler;

    private ValidationRequestHandler validationRequestHandler;

    private TransformedProductBuilder transformedProductBuilder;

    private ValidationRequestBuilder validationRequestBuilder;


    public TransformedProductController(TransformedProductHandler transformedProductHandler,
                                           ValidationRequestHandler validationRequestHandler) {
        this.transformedProductHandler = transformedProductHandler;
        this.validationRequestHandler = validationRequestHandler;
        this.transformedProductBuilder = new TransformedProductBuilder();
        this.validationRequestBuilder = new ValidationRequestBuilder();
    }

    // CREATION

    public TransformedProduct addNewTransformedProduct(TransformedProductDTO transformedProductDTO) {
        TransformedProduct transformedProduct = this.transformedProductHandler.saveTransformedProduct(
                this.transformedProductBuilder.buildTransformedProductFromDTO(transformedProductDTO));
        this.validationRequestHandler.saveValidationRequest(this.generateValidationRequestFrom(transformedProductDTO));
        return transformedProduct;
    }

    // READ

    public TransformedProduct getTransformedProductById(int id) {
        return this.transformedProductHandler.findTransformedProductById(id);
    }

    public List<TransformedProduct> getTransformedProducts() {
        return this.transformedProductHandler.findAllTransformedProduct();
    }

    // UTILITIES

    private ValidationRequest generateValidationRequestFrom(TransformedProductDTO transformedProductDTO) {
        OutputValidationRequestDTO validationRequestDTO = new OutputValidationRequestDTO();
        validationRequestDTO.setSupplyChainPointId(transformedProductDTO.getSupplyChainPointId());
        validationRequestDTO.setContentId(transformedProductDTO.getId());
        validationRequestDTO.setContentType(transformedProductDTO.getContentType());
        return this.validationRequestBuilder.buildValidationRequestFromDTO(validationRequestDTO);
    }

     */

}
