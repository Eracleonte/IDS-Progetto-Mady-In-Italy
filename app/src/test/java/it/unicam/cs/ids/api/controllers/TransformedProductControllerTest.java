package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.TransformedProductDTO;
import it.unicam.cs.ids.api.handlers.TransformedProductHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;
import it.unicam.cs.ids.api.repos.content.TransformedProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransformedProductControllerTest {

    private TransformedProductController transformedProductController;

    private ValidationRequestController validationRequestController;

    private TransformedProductDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up RawProductController

        TransformedProductRepository transformedProductRepository = new TransformedProductRepository();
        TransformedProductHandler transformedProductHandler = new TransformedProductHandler(transformedProductRepository);

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();
        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        transformedProductController = new TransformedProductController(transformedProductHandler, validationRequestHandler);

        // Setting up ValidationRequestController

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        // DTO Setup
        dto = new TransformedProductDTO();
        dto.setId(1);
        dto.setSupplyChainPointId(1);
        dto.setName("test_name");
        dto.setDescription("test_description");
        dto.setAuthor("test_author");
        dto.setCertification("test_certification");
        dto.setVariety("test_variety");
        dto.setTransformationProcessID(1);

    }

    @Test
    void addNewTransformedProduct() {
        // Initial check of raw products status in the system and validation requests
        Assertions.assertEquals(0, transformedProductController.getTransformedProducts().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> transformedProductController.addNewTransformedProduct(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, transformedProductController.getTransformedProducts().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
    }

}