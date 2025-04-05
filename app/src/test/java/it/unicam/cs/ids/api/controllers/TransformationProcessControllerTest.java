package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputTransformationProcessDTO;
import it.unicam.cs.ids.api.handlers.TransformationProcessHandler;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransformationProcessControllerTest {

    private TransformationProcessController transformationProcessController;

    private InputTransformationProcessDTO dto;

    @BeforeEach
    void setUp() {

        TransformationProcessRepository transformationProcessRepository = TransformationProcessRepository.getInstance();

        TransformationProcessHandler transformationProcessHandler = new TransformationProcessHandler(transformationProcessRepository);

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

    /**
    @Test
    void addTransformationProcess() {
        // Initial check of trasformation process status in the system and validation requests
        Assertions.assertEquals(0, transformationProcessController.getTransformationProcesses().size());
        // Insertion
        Assertions.assertDoesNotThrow(() -> transformationProcessController.addNewTransformationProcess(dto));
        // Checking if system has been updated with a new raw product
        Assertions.assertEquals(1, transformationProcessController.getTransformationProcesses().size());
        // Printing get results
        System.out.println(transformationProcessController.getTransformationProcessById(1).toString());
    }
    */

}