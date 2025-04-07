package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.input.*;
import it.unicam.cs.ids.api.handlers.ContentFacade;
import it.unicam.cs.ids.api.model.contents.ContentType;

import java.util.List;

public class ContentController {

    private final ContentFacade facade;

    public ContentController(ContentFacade facade) {
        if (facade == null)
            throw new NullPointerException("Cannot create a Content Controller due to Facade being null");
        this.facade = facade;
    }

    // Creation

    public int addNewRawProduct(InputRawProductDTO dto) {
        if (dto == null)
            throw new NullPointerException("Cannot add a raw product due to DTO being null");
        return this.facade.saveRawProduct(dto);
    }

    public int addNewTransformedProduct(InputTransformedProductDTO dto) {
        if (dto == null)
            throw new NullPointerException("Cannot add a transformed product due to DTO being null");
        return this.facade.saveTransformedProduct(dto);
    }

    public int addNewProductPackage(InputProductPackageDTO dto) {
        if (dto == null)
            throw new NullPointerException("Cannot add a product package due to DTO being null");
        return this.facade.saveProductPackage(dto);
    }

    public int addNewSale(InputSaleDTO dto) {
        if (dto == null)
            throw new NullPointerException("Cannot add a sale due to DTO being null");
        return this.facade.saveSale(dto);
    }

    public int addNewTransformationProcess(InputTransformationProcessDTO dto) {
        if (dto == null)
            throw new NullPointerException("Cannot add a transformation process due to DTO being null");
        return this.facade.saveTransformationProcess(dto);
    }

    // Read

    public Identifiable getContentsByIdAndContentType(int id, ContentType contentType) {
        if (contentType == null)
            throw new NullPointerException("Cannot get contents by id due to contentType being null");
        return this.facade.findContentByIdAndType(id,contentType);
    }

    public List<Identifiable> getAllContentsOfContentType(ContentType contentType) {
        if (contentType == null)
            throw new NullPointerException("Cannot get contents by id due to contentType being null");
        return this.facade.findAllContentsOfContentType(contentType);
    }

    public List<Identifiable> getAllContentsOfContentTypeFiltered(ContentSearchFilterDTO contentSearchFilterDTO) {
        if (contentSearchFilterDTO == null)
            throw new NullPointerException("Cannot get contents by id due to contentSearchFilterDTO being null");
        return this.facade.findAllContentsFiltered(contentSearchFilterDTO);
    }

    // Update

    public String approveContent(int id, ContentType contentType, boolean approvalChoice) {
        if (contentType == null)
            throw new NullPointerException("Cannot approve content due to contentType being null");
        return this.facade.approveContent(id, contentType, approvalChoice);
    }

    public int buyFromSale(int saleId, int quantity) {
        return this.facade.buyFromSale(saleId, quantity);
    }

    public int updateSaleQuantity(int saleId, int quantity) {
        return this.facade.updateSaleQuantity(saleId, quantity);
    }

}
