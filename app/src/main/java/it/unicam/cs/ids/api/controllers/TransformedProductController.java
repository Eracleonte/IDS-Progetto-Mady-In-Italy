package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputTransformedProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;
import it.unicam.cs.ids.api.handlers.TransformedProductHandler;

import java.util.List;

public class TransformedProductController {


    private TransformedProductHandler transformedProductHandler;

    public TransformedProductController(TransformedProductHandler transformedProductHandler) {
        this.transformedProductHandler = transformedProductHandler;
    }

    /**

    // CREATION

    public int addNewTransformedProduct(InputTransformedProductDTO inputTransformedProductDTO) {
        return this.transformedProductHandler.saveTransformedProduct(inputTransformedProductDTO);
    }

    // READ

    public OutputTransformedProductDTO getTransformedProductById(int id) {
        return this.transformedProductHandler.findTransformedProductById(id);
    }

    public List<OutputTransformedProductDTO> getTransformedProducts() {
        return this.transformedProductHandler.findAllTransformedProducts();
    }

    public String approveTransformedProduct(int id, boolean approvalChoice) {
        return this.transformedProductHandler.approveTransformedProduct(id, approvalChoice);
    }

     */

}
