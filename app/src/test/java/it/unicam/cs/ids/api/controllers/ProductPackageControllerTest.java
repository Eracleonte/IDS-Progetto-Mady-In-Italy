package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.dto.input.InputProductPackageElementDTO;
import it.unicam.cs.ids.api.handlers.ProductPackageHandler;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.repos.content.ProductPackageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProductPackageControllerTest {

    private ProductPackageController productPackageController;

    private InputProductPackageDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up ProductPackageController

        ProductPackageRepository productPackageRepository = ProductPackageRepository.getInstance();
        ProductPackageHandler productPackageHandler = new ProductPackageHandler(productPackageRepository);

        productPackageController = new ProductPackageController(productPackageHandler);


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
        // Insertion
        Assertions.assertDoesNotThrow(() -> productPackageController.addNewProductPackage(dto));
        // Checking if system has been updated with a new product package
        Assertions.assertEquals(1, productPackageController.getProductPackages().size());
        // Printing get results
        System.out.println(productPackageController.getProductPackageById(1).toString());
    }

}