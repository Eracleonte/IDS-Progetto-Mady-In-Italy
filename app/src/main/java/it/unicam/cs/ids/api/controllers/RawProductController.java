package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputRawProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputRawProductDTO;
import it.unicam.cs.ids.api.handlers.RawProductHandler;

import java.util.List;

public class RawProductController {

    private RawProductHandler rawProductHandler;

    public RawProductController(RawProductHandler rawProductHandler) {
        this.rawProductHandler = rawProductHandler;
    }

    // CREATION

    public int addNewRawProduct(InputRawProductDTO inputRawProductDTO) {
        return this.rawProductHandler.saveRawProduct(inputRawProductDTO);
    }

    // READ

    public OutputRawProductDTO getRawProductById(int id) {
        return this.rawProductHandler.findRawProductById(id);
    }

    public List<OutputRawProductDTO> getRawProducts() {
        return this.rawProductHandler.findAllRawProducts();
    }

    public String approveRawProduct(int id, boolean approvalChoice) {
        return this.rawProductHandler.approveRawProduct(id, approvalChoice);
    }

}
