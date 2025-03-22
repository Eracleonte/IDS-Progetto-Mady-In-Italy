package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputProductOnSaleDTO;
import it.unicam.cs.ids.api.handlers.ProductOnSaleHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.repos.content.ProductOnSaleRepository;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductOnSaleControllerTest {

    private ProductOnSaleController productOnSaleController;

    private ValidationRequestController validationRequestController;

    private InputProductOnSaleDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up ProductOnSaleController

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();
        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        ProductOnSaleRepository productOnSaleRepository = ProductOnSaleRepository.getInstance();
        ProductOnSaleHandler productOnSaleHandler = new ProductOnSaleHandler(productOnSaleRepository, validationRequestHandler);

        productOnSaleController = new ProductOnSaleController(productOnSaleHandler);

        // Setting up ValidationRequestController

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        // DTO setup

        dto = new InputProductOnSaleDTO(
                1,
                1,
                ContentType.RAW_PRODUCT.getValue(),
                "test_name",
                "test_description",
                "test_author",
                1.0f,
                10
        );

    }

    @Test
    public void addNewProductOnSale(){
        // Initial check of products on sale status in the system and validation requests
        Assertions.assertEquals(0, productOnSaleController.getProductsOnSale().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> productOnSaleController.addNewProductOnSale(dto));
        // Checking if system has been updated with a new product on sale
        Assertions.assertEquals(1, productOnSaleController.getProductsOnSale().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
        // Printing get results
        System.out.println(productOnSaleController.getProductOnSaleById(1).toString());
        System.out.println(validationRequestController.getValidationRequestById(1).toString());
    }

}