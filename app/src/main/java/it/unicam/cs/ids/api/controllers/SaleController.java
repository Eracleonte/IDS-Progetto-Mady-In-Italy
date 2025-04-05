package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSaleDTO;
import it.unicam.cs.ids.api.dto.output.OutputSaleDTO;
import it.unicam.cs.ids.api.handlers.SaleHandler;

import java.util.List;

public class SaleController {

    private SaleHandler saleHandler;

    public SaleController(SaleHandler saleHandler) {
        this.saleHandler = saleHandler;
    }

    /**

    // CREATOR

    public int addNewProductOnSale(InputSaleDTO inputSaleDTO) {
        return this.saleHandler.saveProductOnSale(inputSaleDTO);
    }

    //READ

    public OutputSaleDTO getProductOnSaleById(int id){
        return this.saleHandler.findProductOnSaleById(id);
    }

    public List<OutputSaleDTO> getProductsOnSale(){
        return this.saleHandler.findAllProductsOnSale();
    }

    public String approveSale(int id, boolean approvalChoice) {
        return this.saleHandler.approveSale(id, approvalChoice);
    }
     */

}
