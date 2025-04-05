package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.input.*;
import it.unicam.cs.ids.api.handlers.ContentFacade;
import it.unicam.cs.ids.api.model.contents.ContentType;

import java.util.List;

public class ContentController {

    private ContentFacade facade;

    public ContentController(ContentFacade facade) {
        if (facade == null)
            throw new NullPointerException("Cannot create a Content Controller due to Facade being null");
        this.facade = facade;
    }

    // Creation

    public int addNewRawProduct(InputRawProductDTO inputRawProductDTO) {
        return this.facade.saveRawProduct(inputRawProductDTO);
    }

    public int addNewTransformedProduct(InputTransformedProductDTO inputTransformedProductDTO) {
        return this.facade.saveTransformedProduct(inputTransformedProductDTO);
    }

    public int addNewProductPackage(InputProductPackageDTO inputProductPackageDTO) {
        return this.facade.saveProductPackage(inputProductPackageDTO);
    }

    public int addNewSale(InputSaleDTO inputSaleDTO) {
        return this.facade.saveSale(inputSaleDTO);
    }

    public int addNewTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO) {
        return this.facade.saveTransformationProcess(inputTransformationProcessDTO);
    }

    // Read

    public Identifiable getContentsByIdAndContentType(int id, ContentType contentType) {
        return this.facade.findContentByIdAndType(id,contentType);
    }

    public List<Identifiable> getAllContentsOfContentType(ContentType contentType) {
        return this.facade.findAllContentsOfContentType(contentType);
    }

    // Update

    public String approveContent(int id, ContentType contentType, boolean approvalChoice) {
        return this.facade.approveContent(id, contentType, approvalChoice);
    }

    public int buyFromSale(int saleId, int quantity) {
        return this.facade.buyFromSale(saleId, quantity);
    }

    public int updateSaleQuantity(int saleId, int quantity) {
        return this.facade.updateSaleQuantity(saleId, quantity);
    }

}
