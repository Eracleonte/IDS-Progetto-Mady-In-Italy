package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputTransformedProductDTO;
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

    private InputTransformedProductDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up TransformationProcessController

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();

        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        TransformedProductRepository transformedProductRepository = TransformedProductRepository.getInstance();

        TransformedProductHandler transformedProductHandler = new TransformedProductHandler(transformedProductRepository,validationRequestHandler);

        transformedProductController = new TransformedProductController(transformedProductHandler);

        // DTO Setup
        dto = new InputTransformedProductDTO(1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_variety",
                1
        );

    }

    @Test
    void addTransformationProcess() {
        // Initial check of trasformation process status in the system and validation requests
        Assertions.assertEquals(0, transformedProductController.getTransformedProducts().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() ->  transformedProductController.addNewTransformedProduct(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1,  transformedProductController.getTransformedProducts().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
        // Printing get results
        System.out.println(transformedProductController.getTransformedProductById(1).toString());
        System.out.println(validationRequestController.getValidationRequestById(1).toString());
    }

}