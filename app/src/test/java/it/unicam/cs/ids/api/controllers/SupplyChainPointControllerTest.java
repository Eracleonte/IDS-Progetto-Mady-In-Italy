package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.input.SupplyChainPointSearchFilterDTO;
import it.unicam.cs.ids.api.handlers.SupplyChainPointHandler;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointManagementRepository;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// IMPORTANT NOTE: tests work if run singularly due to issues with id handling.

class SupplyChainPointControllerTest {

    private SupplyChainPointController supplyChainPointController;

    private InputSupplyChainPointDTO inputSupplyChainPointDTO;

    private SupplyChainPointSearchFilterDTO supplyChainPointSearchFilterDTO;

    @BeforeEach
    void setUp() {

        // Setup supply chain point controller

        SupplyChainPointRepository scpRepo = SupplyChainPointRepository.getInstance();
        SupplyChainPointManagementRepository scpmRepo = SupplyChainPointManagementRepository.getInstance();
        SupplyChainPointHandler scpHandler = new SupplyChainPointHandler(scpRepo,scpmRepo);

        supplyChainPointController = new SupplyChainPointController(scpHandler);

        // DTO setup

        inputSupplyChainPointDTO = new InputSupplyChainPointDTO(45.0f,
                45.0f,
                "test_name",
                true,
                true,
                true,
                true);

        supplyChainPointSearchFilterDTO = new SupplyChainPointSearchFilterDTO(null,
                true,
                true,
                true,
                true,
                1);

    }

    @Disabled
    @Test
    void addSupplyChainPoint() {
        Assertions.assertThrows(NullPointerException.class, () -> supplyChainPointController.addSupplyChainPoint(null,1));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints(false).size());
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints(true).size());
        Assertions.assertDoesNotThrow(() -> supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO,1));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPoints(false).size());
        System.out.println(supplyChainPointController.getSupplyChainPointById(1));
    }

    @Disabled
    @Test
    void addSupplyChainPointManagement() {
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPointManagement(false).size());
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPointManagement(true).size());
        Assertions.assertDoesNotThrow(() -> supplyChainPointController.addSupplyChainPointManagement(1, 1));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPointManagement(false).size());
        System.out.println(supplyChainPointController.getAllSupplyChainPointManagementByUserId(1,false));
        System.out.println(supplyChainPointController.getAllSupplyChainPointManagementBySupplyChainPointId(1,false));
    }

    @Disabled
    @Test
    void findAllSupplyChainPointsFiltered() {
        Assertions.assertDoesNotThrow(() -> supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO,1));
        System.out.println(supplyChainPointController.approveSupplyChainPoint(1, true));
        System.out.println(supplyChainPointController.approveSupplyChainPointManagement(1, true));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPointsFiltered(supplyChainPointSearchFilterDTO,true).size());
    }

    @Disabled
    @Test
    void rejectSupplyChainPoint() {
        supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO,1);
        System.out.println(supplyChainPointController.approveSupplyChainPoint(1, false));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints(false).size());
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints(true).size());
    }

    @Disabled
    @Test
    void approveSupplyChainPoint() {
        supplyChainPointController.addSupplyChainPoint(inputSupplyChainPointDTO,1);
        System.out.println(supplyChainPointController.approveSupplyChainPoint(1, true));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPoints(false).size());
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPoints(true).size());
    }

    @Disabled
    @Test
    void rejectSupplyChainPointManagement() {
        supplyChainPointController.addSupplyChainPointManagement(1, 1);
        System.out.println(supplyChainPointController.approveSupplyChainPointManagement(1, false));
        Assertions.assertEquals(0, supplyChainPointController.getAllSupplyChainPointManagement(false).size());
    }

    @Disabled
    @Test
    void approveSupplyChainPointManagement() {
        supplyChainPointController.addSupplyChainPointManagement(1, 1);
        System.out.println(supplyChainPointController.approveSupplyChainPointManagement(1, true));
        Assertions.assertEquals(1, supplyChainPointController.getAllSupplyChainPointManagement(true).size());
    }

}

