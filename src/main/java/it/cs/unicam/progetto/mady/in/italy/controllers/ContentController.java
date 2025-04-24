package it.cs.unicam.progetto.mady.in.italy.controllers;

import it.cs.unicam.progetto.mady.in.italy.dto.input.*;
import it.cs.unicam.progetto.mady.in.italy.handlers.ContentFacade;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller used to treat any call that is destined to Create/Read/Update/Delete
 * contents in the system.
 */
@RestController
@RequestMapping("content")
public class ContentController {

    /**
     * Content Facade bearing all system components necessary for Content management.
     */
    private final ContentFacade facade;

    @Autowired
    public ContentController(ContentFacade facade) {
        if (facade == null)
            throw new NullPointerException("Cannot create a Content Controller due to Facade being null");
        this.facade = facade;
    }

    // Creation

    /**
     * Adds a new Raw Product from the given parameters.
     * The new Raw Product will result as authored by the User and will count as not approved.
     *
     * @param dto the dto bearing necessary information required for the creation of the new Raw Product.
     * @param userId the ID of the user that is creating the new Raw Product.
     * @return a message in String format that will communicate the operation's result.
     */
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

    /**
     * Adds a new Transformed Product from the given parameters.
     * The new Transformed Product will result as authored by the User and will count as not approved.
     *
     * @param dto the dto bearing necessary information required for the creation of the new Transformed Product.
     * @param userId the ID of the user that is creating the new Transformed Product.
     * @return a message in String format that will communicate the operation's result.
     */
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

    /**
     * Adds a new Product Package from the given parameters.
     * The new Product Package will result as authored by the User and will count as not approved.
     *
     * @param dto the dto bearing necessary information required for the creation of the new Product Package.
     * @param userId the ID of the User that is creating the new Product Package.
     * @return a message in String format that will communicate the operation's result.
     */
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

    /**
     * Adds a new Sale from the given parameters.
     * The new Sale will result as authored by the User and will count as not approved.
     *
     * @param dto the dto bearing necessary information required for the creation of the new Sale.
     * @param userId the ID of the User that is creating the new Sale.
     * @return a message in String format that will communicate the operation's result.
     */
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

    /**
     * Adds a new Transformation Process from the given parameters.
     * The new Transformation Process will result as authored by the User and will count as not approved.
     *
     * @param dto the dto bearing necessary information required for the creation of the new Transformation Process.
     * @param userId the ID of the User that is creating the new Transformation Process.
     * @return a message in String format that will communicate the operation's result.
     */
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

    /**
     * Retrieves a Content by its ID and ContentType.
     *
     * @param id the ID of the Content wished to be retrieved.
     * @param contentType the ContentType of the Content wished to be retrieved.
     * @return the desired Content if existent.
     */
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
     * Retrieves all contents of a certain ContentType based on the approval status.
     *
     * @param contentType the type of contents researched.
     * @param approved is set to true if only approved contents are subject of the retrieval operation,
     *                 is set to false if only contents yet to be approved are subject of the retrieval operation.
     * @return all contents of a certain ContentType based on the approval status.
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

    /**
     * Retrieves all contents that match the given ContentSearchFilter.
     *
     * @param contentSearchFilterDTO the ContentSearchFilter to apply for this retrieval operation.
     * @return all contents that match the ContentSearchFilter if present.
     */
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

    /**
     * Approves or Rejects a Content.
     *
     * @param id the ID of the Content to approve or reject.
     * @param contentType the ContentType of the Content to approve or reject.
     * @param approvalChoice true if the Content needs to be approved,
     *                       false if the Content needs to be rejected.
     * @param curatorId the ID of the Curator that is performing this operation.
     * @return a message in String format that will communicate the operation's result.
     */
    @PatchMapping("/approval/{contentId}/{contentType}/by-curator/{curatorId}")
    public ResponseEntity<String> approveContent(@PathVariable("contentId") int id,
                                                 @PathVariable("contentType") ContentType contentType,
                                                 @RequestParam boolean approvalChoice,
                                                 @PathVariable("curatorId") int curatorId) {
        try {
            return new ResponseEntity<>(facade.approveContent(id,contentType,approvalChoice,curatorId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Buys from an approved Sale.
     *
     * @param saleId the ID of the Sale where to buy from.
     * @param quantity the quantity of desired items to buy.
     * @param buyerId the ID of the Buyer.
     * @return a message in String format that will communicate the operation's result.
     */
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
     * Updates the quantity of the Sale with the specified ID.
     * The update of the Sale quantity should be interpreted as a resupply of the Sale with the specified ID.
     *
     * @param saleId the ID of the Sale to resupply
     * @param quantity the quantity used to resupply the specified Sale
     * @param sellerId the ID of the seller.
     * @return a message in String format that will communicate the operation's result.
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
