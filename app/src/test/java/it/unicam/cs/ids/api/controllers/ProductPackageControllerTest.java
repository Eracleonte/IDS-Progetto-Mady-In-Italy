package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.dto.input.InputProductPackageElementDTO;
import it.unicam.cs.ids.api.handlers.ProductPackageHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.repos.ProductPackageRepository;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProductPackageControllerTest {

    private ProductPackageController productPackageController;

    private ValidationRequestController validationRequestController;

    private InputProductPackageDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up ProductPackageController

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();
        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        ProductPackageRepository productPackageRepository = new ProductPackageRepository();
        ProductPackageHandler productPackageHandler = new ProductPackageHandler(productPackageRepository,
                validationRequestHandler);

        productPackageController = new ProductPackageController(productPackageHandler);

        // Setting up ValidationRequestController

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        // InputProductPackageElementDTO setup

        List<InputProductPackageElementDTO> inputProductPackageElementDTOS = new ArrayList<>();
        inputProductPackageElementDTOS.add
                (new InputProductPackageElementDTO(1, ContentType.RAW_PRODUCT.getValue()));
        inputProductPackageElementDTOS.add
                (new InputProductPackageElementDTO(1, ContentType.TRANSFORMED_PRODUCT.getValue()));

        // DTO setup

        dto = new InputProductPackageDTO(
                1,
                "test_name",
                "test_description",
                "test_author",
                inputProductPackageElementDTOS
        );

    }

    @Test
    void addNewProductPackage() {
        // Initial check of product packages status in the system and validation requests
        Assertions.assertEquals(0, productPackageController.getProductPackages().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> productPackageController.addNewProductPackage(dto));
        // Checking if system has been updated with a new product package
        Assertions.assertEquals(1, productPackageController.getProductPackages().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
        // Printing get results
        System.out.println(productPackageController.getProductPackageById(1).toString());
        System.out.println(validationRequestController.getValidationRequestById(1).toString());
    }

}