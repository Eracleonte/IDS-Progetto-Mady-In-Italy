package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputTransformationProcessDTO;
import it.unicam.cs.ids.api.handlers.TransformationProcessHandler;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransformationProcessControllerTest {

    private TransformationProcessController transformationProcessController;

    private ValidationRequestController validationRequestController;

    private InputTransformationProcessDTO dto;

    @BeforeEach
    void setUp() {

        // Setting up TransformationProcessController

        ValidationRequestRepository validationRequestRepository = new ValidationRequestRepository();

        ValidationRequestHandler validationRequestHandler = new ValidationRequestHandler(validationRequestRepository);

        validationRequestController = new ValidationRequestController(validationRequestHandler);

        TransformationProcessRepository transformationProcessRepository = TransformationProcessRepository.getInstance();

        TransformationProcessHandler transformationProcessHandler = new TransformationProcessHandler(transformationProcessRepository, validationRequestHandler);

        transformationProcessController = new TransformationProcessController(transformationProcessHandler);

        // DTO Setup
        dto = new InputTransformationProcessDTO(1,
                "test_name",
                "test_description",
                "test_author",
                "test_certification",
                "test_phases"
        );

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
        // Printing get results
        System.out.println(transformationProcessController.getTransformationProcessById(1).toString());
        System.out.println(validationRequestController.getValidationRequestById(1).toString());
    }

}