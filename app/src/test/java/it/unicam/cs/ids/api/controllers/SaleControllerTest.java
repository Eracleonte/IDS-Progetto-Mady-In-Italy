package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSaleDTO;
import it.unicam.cs.ids.api.handlers.SaleHandler;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.repos.content.SaleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaleControllerTest {

    private SaleController saleController;

    private InputSaleDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up ProductOnSaleController

        SaleRepository saleRepository = SaleRepository.getInstance();
        SaleHandler saleHandler = new SaleHandler(saleRepository);

        saleController = new SaleController(saleHandler);

        // DTO setup

        dto = new InputSaleDTO(
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
        Assertions.assertEquals(0, saleController.getProductsOnSale().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> saleController.addNewProductOnSale(dto));
        // Checking if system has been updated with a new product on sale
        Assertions.assertEquals(1, saleController.getProductsOnSale().size());
        // Printing get results
        System.out.println(saleController.getProductOnSaleById(1).toString());
    }

}