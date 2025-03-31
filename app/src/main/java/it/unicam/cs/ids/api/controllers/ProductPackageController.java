package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.dto.output.OutputProductPackageDTO;
import it.unicam.cs.ids.api.handlers.ProductPackageHandler;

import java.util.List;

public class ProductPackageController {

    private ProductPackageHandler productPackageHandler;

    public ProductPackageController(ProductPackageHandler productPackageHandler) {
        this.productPackageHandler = productPackageHandler;
    }

    // CREATION

    public int addNewProductPackage(InputProductPackageDTO inputProductPackageDTO) {
        return this.productPackageHandler.saveProductPackage(inputProductPackageDTO);
    }

    // READ

    public OutputProductPackageDTO getProductPackageById(int id) {
        return this.productPackageHandler.findProductPackageById(id);
    }

    public List<OutputProductPackageDTO> getProductPackages() {
        return this.productPackageHandler.findAllProductPackages();
    }

    public String approveProductPackage(int id, boolean approvalChoice) {
        return this.productPackageHandler.approveProductPackage(id, approvalChoice);
    }

}
