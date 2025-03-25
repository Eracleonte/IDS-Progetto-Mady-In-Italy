package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;
import it.unicam.cs.ids.api.handlers.SupplyChainPointHandler;
import it.unicam.cs.ids.api.handlers.SupplyChainPointManagementHandler;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.List;
import java.util.function.Predicate;

public class SupplyChainPointController {

    private SupplyChainPointHandler supplyChainPointHandler;

    private SupplyChainPointManagementHandler supplyChainPointManagementHandler;

    public SupplyChainPointController(SupplyChainPointHandler supplyChainPointHandler,
                                      SupplyChainPointManagementHandler supplyChainPointManagementHandler) {
        this.supplyChainPointHandler = supplyChainPointHandler;
        this.supplyChainPointManagementHandler = supplyChainPointManagementHandler;
    }

    // Supply Chain Point operations

    public int addSupplyChainPoint(InputSupplyChainPointDTO inputSupplyChainPointDTO) {
        return this.supplyChainPointHandler.saveSupplyChainPoint(inputSupplyChainPointDTO);
    }

    public OutputSupplyChainPointDTO getSupplyChainPointById(int id) {
        return this.supplyChainPointHandler.findSupplyChainPointById(id);
    }

    public List<OutputSupplyChainPointDTO> getAllSupplyChainPoints() {
        return this.supplyChainPointHandler.findAllSupplyChainPoints();
    }

    public List<OutputSupplyChainPointDTO> getSupplyChainPointsIf(Predicate<? super SupplyChainPoint> filter) {
        return this.supplyChainPointHandler.getSupplyChainPointsIf(filter);
    }

    public String approveSupplyChainPoint(int id, boolean approvalChoice) {
        return this.supplyChainPointHandler.approveSupplyChainPoint(id, approvalChoice);
    }

    // Supply Chain Point Management operations

    public int addSupplyChainPointManagement(int supplyChainPointId, int userId) {
        return this.supplyChainPointManagementHandler.saveSupplyChainPointManagement(supplyChainPointId, userId);
    }

    public List<OutputSupplyChainPointManagementDTO> getSupplyChainPointManagementByUserId(int userId) {
        return this.supplyChainPointManagementHandler.getSupplyChainPointManagementByUserId(userId);
    }

    public List<OutputSupplyChainPointManagementDTO> getSupplyChainPointManagementBySupplyChainPointId(int supplyChainPointId) {
        return this.supplyChainPointManagementHandler.getSupplyChainPointManagementBySupplyChainPointId(supplyChainPointId);
    }

    public List<OutputSupplyChainPointManagementDTO> getAllSupplyChainPointManagement() {
        return this.supplyChainPointManagementHandler.getAllSupplyChainPointManagement();
    }

    public String approveSupplyChainPointManagement(int id, boolean approvalChoice) {
        return this.supplyChainPointManagementHandler.approveSupplyChainPointManagement(id, approvalChoice);
    }

}
