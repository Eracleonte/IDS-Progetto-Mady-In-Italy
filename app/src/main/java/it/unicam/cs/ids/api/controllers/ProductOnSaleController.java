package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputProductOnSaleDTO;
import it.unicam.cs.ids.api.dto.output.OutputProductOnSaleDTO;
import it.unicam.cs.ids.api.handlers.ProductOnSaleHandler;

import java.util.List;

public class ProductOnSaleController {
    private ProductOnSaleHandler productOnSaleHandler;

    public ProductOnSaleController(ProductOnSaleHandler productOnSaleHandler) {
        this.productOnSaleHandler = productOnSaleHandler;
    }

    // CREATOR

    public int addNewProductOnSale(InputProductOnSaleDTO inputProductOnSaleDTO) {
        return this.productOnSaleHandler.saveProductOnSale(inputProductOnSaleDTO);
    }

    //READ

    public OutputProductOnSaleDTO getProductOnSaleById(int id){
        return this.productOnSaleHandler.findProductOnSaleById(id);
    }

    public List<OutputProductOnSaleDTO> getProductsOnSale(){
        return this.productOnSaleHandler.findAllProductsOnSale();
    }

}
