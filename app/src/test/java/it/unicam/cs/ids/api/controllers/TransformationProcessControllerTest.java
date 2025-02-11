package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.TransformationProcessDTO;
import it.unicam.cs.ids.api.handlers.TransformationProcessHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.repos.ValidationRequestRepository;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransformationProcessControllerTest {

    private TransformationProcessController transformationProcessController;

    private ValidationRequestController validationRequestController;

    private TransformationProcessDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up RawProductController

        TransformationProcessRepository transformationProcessRepository= new TransformationProcessRepository();
        TransformationProcessHandler transformationProcessHandler = new TransformationProcessHandler(transformationProcessRepository);

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();
        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        transformationProcessController = new TransformationProcessController(transformationProcessHandler, validationRequestHandler);

        // Setting up ValidationRequestController

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        // DTO Setup
        dto = new TransformationProcessDTO();
        dto.setId(1);
        dto.setSupplyChainPointId(1);
        dto.setName("test_name");
        dto.setDescription("test_description");
        dto.setAuthor("test_author");
        dto.setCertification("test_certification");
        dto.setTransformationPhases("test_phases");

    }

    @Test
    void addTransformationProcess() {
        // Initial check of trasformation process status in the system and validation requests
        Assertions.assertEquals(0, transformationProcessController.getTransformationProcesses().size());
        Assertions.assertEquals(0, validationRequestController.getValidationRequests().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> transformationProcessController.addNewTransformationProcess(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, transformationProcessController.getTransformationProcesses().size());
        Assertions.assertEquals(1, validationRequestController.getValidationRequests().size());
    }

}