package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.input.SupplyChainPointSearchFilterDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;
import it.unicam.cs.ids.api.handlers.SupplyChainPointHandler;

import java.util.List;

public class SupplyChainPointController {

    private final SupplyChainPointHandler supplyChainPointHandler;

    public SupplyChainPointController(SupplyChainPointHandler supplyChainPointHandler) {
        if (supplyChainPointHandler == null)
            throw new NullPointerException("Cannot create a SupplyChainController due to supplyChainPointHandler being null");
        this.supplyChainPointHandler = supplyChainPointHandler;
    }

    // Supply Chain Point operations

    public int addSupplyChainPoint(InputSupplyChainPointDTO inputSupplyChainPointDTO, int userId) {
        if (inputSupplyChainPointDTO == null)
            throw new NullPointerException("Input supply chain point cannot be null");
        return this.supplyChainPointHandler.saveSupplyChainPoint(inputSupplyChainPointDTO, userId);
    }

    public OutputSupplyChainPointDTO getSupplyChainPointById(int id) {
        return this.supplyChainPointHandler.findSupplyChainPointById(id);
    }

    public List<OutputSupplyChainPointDTO> getAllSupplyChainPoints(boolean approved) {
        return this.supplyChainPointHandler.findAllSupplyChainPoints(approved);
    }

    /**
     *
     * Returns a list of OutputSupplyChainPointDTO that respect the given SupplyChainPointSearchFilterDTO.
     *
     * @param filter the filter to apply for the research.
     * @param approved equals true if only approved supply chain points are supposed to be gathered.
     * @return a List of OutputSupplyChainPointDTO that respect the given SupplyChainPointSearchFilterDTO.
     */
    public List<OutputSupplyChainPointDTO> getAllSupplyChainPointsFiltered(SupplyChainPointSearchFilterDTO filter,
                                                                           boolean approved) {
        if (filter == null)
            throw new NullPointerException("Supply chain point search filter cannot be null");
        return this.supplyChainPointHandler.findAllSupplyChainPointsFiltered(filter, approved);
    }

    public String approveSupplyChainPoint(int id, boolean approvalChoice) {
        return this.supplyChainPointHandler.approveSupplyChainPoint(id, approvalChoice);
    }

    // Supply Chain Point Management operations

    public int addSupplyChainPointManagement(int supplyChainPointId, int userId) {
        return this.supplyChainPointHandler.saveSupplyChainPointManagement(supplyChainPointId, userId);
    }

    public List<OutputSupplyChainPointManagementDTO> getAllSupplyChainPointManagementByUserId(int userId,
                                                                                              boolean approved) {
        return this.supplyChainPointHandler.findAllSupplyChainPointManagementByUserId(userId, approved);
    }

    public List<OutputSupplyChainPointManagementDTO> getAllSupplyChainPointManagementBySupplyChainPointId(int supplyChainPointId,
                                                                                                          boolean approved) {
        return this.supplyChainPointHandler.findAllSupplyChainPointManagementBySupplyChainPointId(supplyChainPointId, approved);
    }

    public List<OutputSupplyChainPointManagementDTO> getAllSupplyChainPointManagement(boolean approved) {
        return this.supplyChainPointHandler.findAllSupplyChainPointManagement(approved);
    }

    public String approveSupplyChainPointManagement(int id, boolean approvalChoice) {
        return this.supplyChainPointHandler.approveSupplyChainPointManagement(id, approvalChoice);
    }

}
