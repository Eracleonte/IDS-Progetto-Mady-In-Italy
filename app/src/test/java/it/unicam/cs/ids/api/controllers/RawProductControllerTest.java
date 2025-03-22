package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputRawProductDTO;
import it.unicam.cs.ids.api.handlers.RawProductHandler;
import it.unicam.cs.ids.api.repos.content.RawProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RawProductControllerTest {

    private RawProductController rawProductController;

    private InputRawProductDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up RawProductController

        RawProductRepository rawProductRepository = RawProductRepository.getInstance();
        RawProductHandler rawProductHandler = new RawProductHandler(rawProductRepository);

        rawProductController = new RawProductController(rawProductHandler);

        // DTO Setup
        dto = new InputRawProductDTO(
                1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_variety",
                "test_production_method"
        );

    }

    @Test
    void testAddNewRawProduct() {
        // Initial check of raw products status in the system.
        Assertions.assertEquals(0, rawProductController.getRawProducts().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> rawProductController.addNewRawProduct(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, rawProductController.getRawProducts().size());
        // Printing get results
        System.out.println(rawProductController.getRawProductById(1).toString());
    }

}