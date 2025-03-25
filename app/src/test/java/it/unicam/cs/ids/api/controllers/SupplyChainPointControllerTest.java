package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.handlers.SupplyChainPointHandler;
import it.unicam.cs.ids.api.handlers.SupplyChainPointManagementHandler;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointManagementRepository;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplyChainPointControllerTest {

    private SupplyChainPointController supplyChainPointController;

    private InputSupplyChainPointDTO inputSupplyChainPointDTO;

    @BeforeEach
    void setUp() {

        // Setup supply chain point controller

        SupplyChainPointRepository scpRepo = SupplyChainPointRepository.getInstance();
        SupplyChainPointHandler scpHandler = new SupplyChainPointHandler(scpRepo);

        SupplyChainPointManagementRepository scpmRepo = SupplyChainPointManagementRepository.getInstance();
        SupplyChainPointManagementHandler scpmHandler = new SupplyChainPointManagementHandler(scpmRepo);

        supplyChainPointController = new SupplyChainPointController(scpHandler, scpmHandler);

        // DTO setup

        inputSupplyChainPointDTO = new InputSupplyChainPointDTO(45.0f,
                45.0f,
                "test_name",
                true,
                true,
                true,
                true);

    }

    @Test
    //@Disabled
    void addSupplyChainPoint() {
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints().size());
        Assertions.assertDoesNotThrow(() -> supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPoints().size());
        System.out.println(supplyChainPointController.getSupplyChainPointById(1));
    }

    @Test
    //@Disabled
    void addSupplyChainPointManagement() {
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPointManagement().size());
        Assertions.assertDoesNotThrow(() -> supplyChainPointController.addSupplyChainPointManagement(1, 1));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPointManagement().size());
        System.out.println(supplyChainPointController.getSupplyChainPointManagementByUserId(1));
    }

    @Test
    @Disabled
    void rejectSupplyChainPoint() {
        supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO);
        System.out.println(supplyChainPointController.approveSupplyChainPoint(1, false));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints().size());
    }

    @Test
    @Disabled
    void approveSupplyChainPoint() {
        supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO);
        System.out.println(supplyChainPointController.approveSupplyChainPoint(1, true));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPoints().size());
    }

    @Test
    @Disabled
    void rejectSupplyChainPointManagement() {
        supplyChainPointController.addSupplyChainPointManagement(1, 1);
        System.out.println(supplyChainPointController.approveSupplyChainPointManagement(1, false));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPointManagement().size());
    }

    @Test
    @Disabled
    void approveSupplyChainPointManagement() {
        supplyChainPointController.addSupplyChainPointManagement(1, 1);
        System.out.println(supplyChainPointController.approveSupplyChainPointManagement(1, true));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPointManagement().size());
    }

}