package it.cs.unicam.progetto.mady.in.italy.controllers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.*;
import it.cs.unicam.progetto.mady.in.italy.handlers.ContentFacade;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content")
public class ContentController {

    private final ContentFacade facade;

    @Autowired
    public ContentController(ContentFacade facade) {
        if (facade == null)
            throw new NullPointerException("Cannot create a Content Controller due to Facade being null");
        this.facade = facade;
    }

    // Creation

    @PostMapping("/new-raw-product/by/{userId}")
    public ResponseEntity<String> addNewRawProduct(@RequestBody InputRawProductDTO dto,
                                                   @PathVariable("userId") int userId) {
        try {
            int id = facade.saveRawProduct(dto,userId);
            return new ResponseEntity<>("Raw Product saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-transformed-product/by/{userId}")
    public ResponseEntity<String> addNewTransformedProduct(@RequestBody InputTransformedProductDTO dto,
                                                           @PathVariable("userId") int userId) {
        try {
            int id = facade.saveTransformedProduct(dto,userId);
            return new ResponseEntity<>("Transformed Product saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-product-package/by/{userId}")
    public ResponseEntity<String> addNewProductPackage(@RequestBody InputProductPackageDTO dto,
                                                       @PathVariable("userId") int userId) {
        try {
            int id = facade.saveProductPackage(dto,userId);
            return new ResponseEntity<>("Product Package saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-sale/by/{userId}")
    public ResponseEntity<String> addNewSale(@RequestBody InputSaleDTO dto,
                                             @PathVariable("userId") int userId) {
        try {
            int id = facade.saveSale(dto,userId);
            return new ResponseEntity<>("Sale saved with id: " + id, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-transformation-process/by/{userId}")
    public ResponseEntity<String> addNewTransformationProcess(@RequestBody InputTransformationProcessDTO dto,
                                                              @PathVariable("userId") int userId) {
        try {
            int id = facade.saveTransformationProcess(dto,userId);
            return new ResponseEntity<>("Transformation Process saved with id: " + id, HttpStatus.CREATED);
        }  catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read

    @GetMapping("/search/{contentId}/{contentType}")
    public ResponseEntity<Object> getContentByIdAndContentType(@PathVariable("contentId") int id,
                                                               @PathVariable("contentType") ContentType contentType) {
        try {
            return new ResponseEntity<>(facade.findContentByIdAndContentType(id, contentType), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets all contents of a certain type based on the approval status.
     *
     * @param contentType the type of contents researched.
     * @param approved is set to true if only approved contents are subject of the retrieval operation,
     *                 is set to false if only contents yet to be approved are subject of the retrieval operation.
     */
    @GetMapping("/search/all/{contentType}")
    public ResponseEntity<Object> getAllContentsOfContentType(@PathVariable("contentType") ContentType contentType,
                                                              @RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(facade.findAllContentsOfContentType(contentType, approved), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search/filtered")
    public ResponseEntity<Object> getAllContentsOfContentTypeFiltered(@RequestBody ContentSearchFilterDTO contentSearchFilterDTO) {
        try {
            return new ResponseEntity<>(facade.findAllContentsFiltered(contentSearchFilterDTO), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update

    @PatchMapping("/approval/{contentId}/{contentType}/by-curator/{curatorId}")
    public ResponseEntity<String> approveContent(@PathVariable("contentId") int id,
                                                 @PathVariable("contentType") ContentType contentType,
                                                 @RequestParam boolean approvalChoice,
                                                 @PathVariable("curatorId") int curatorId) {
        try {
            return new ResponseEntity<>(facade.approveContent(id, contentType, approvalChoice,curatorId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/buy/{saleId}/by-buyer/{buyerId}")
    public ResponseEntity<String> buyFromSale(@PathVariable("saleId") int saleId,
                                              @RequestParam int quantity,
                                              @PathVariable("buyerId") int buyerId) {
        try {
            return new ResponseEntity<>(facade.buyFromSale(saleId,quantity,buyerId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * Updates the quantity of the sale with the specified id.
     * The update of the sale quantity should be interpreted as a resupply of the sale with the specified id.
     *
     * @param saleId the id of the sale to resupply
     * @param quantity the quantity used to resupply the specified sale
     */
    @PatchMapping("/sale/resupply/{saleId}/of-seller/{sellerId}")
    public ResponseEntity<String> updateSaleQuantity(@PathVariable("saleId") int saleId,
                                                     @RequestParam int quantity,
                                                     @PathVariable("sellerId") int sellerId) {
        try {
            return new ResponseEntity<>(facade.updateSaleQuantity(saleId, quantity,sellerId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
