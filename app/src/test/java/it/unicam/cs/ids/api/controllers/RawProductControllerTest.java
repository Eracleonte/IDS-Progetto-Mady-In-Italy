package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.RawProductDTO;
import it.unicam.cs.ids.api.handlers.RawProductHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;
import it.unicam.cs.ids.api.repos.content.RawProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RawProductControllerTest {

    private RawProductController rawProductController;

    private ValidationRequestController validationRequestController;

    private RawProductDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up RawProductController

        RawProductRepository rawProductRepository = new RawProductRepository();
        RawProductHandler rawProductHandler = new RawProductHandler(rawProductRepository);

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();
        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        rawProductController = new RawProductController(rawProductHandler, validationRequestHandler);

        // Setting up ValidationRequestController

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        // DTO Setup
        dto = new RawProductDTO();
        dto.setId(1);
        dto.setSupplyChainPointId(1);
        dto.setName("test_name");
        dto.setDescription("test_description");
        dto.setAuthor("test_author");
        dto.setCertification("test_certification");
        dto.setVariety("test_variety");
        dto.setProductionMethod("test_production_method");

    }

    @Test
    void testAddNewRawProduct() {
        // Initial check of raw products status in the system and validation requests
        Assertions.assertEquals(0, rawProductController.getRawProducts().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> rawProductController.addNewRawProduct(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, rawProductController.getRawProducts().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
    }

}