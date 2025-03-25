package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPointManagement;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointManagementRepository;

import java.util.List;

public class SupplyChainPointManagementHandler {

    private SupplyChainPointManagementRepository supplyChainPointManagementRepository;

    public SupplyChainPointManagementHandler(SupplyChainPointManagementRepository
                                                     supplyChainPointManagementRepository) {
        this.supplyChainPointManagementRepository = supplyChainPointManagementRepository;
    }

    public int saveSupplyChainPointManagement(int supplyChainPointId, int userId) {
        int id = this.supplyChainPointManagementRepository.getNextId();
        SupplyChainPointManagement supplyChainPointManagement =
                new SupplyChainPointManagement(id, supplyChainPointId, userId);
        return this.supplyChainPointManagementRepository.save(supplyChainPointManagement).getId();
    }

    public List<OutputSupplyChainPointManagementDTO> getSupplyChainPointManagementByUserId(int userId) {
        return this.supplyChainPointManagementRepository.findAll()
                .stream()
                .filter((m) -> m.getUserId() == userId)
                .map(SupplyChainPointManagement::toOutputSupplyChainPointManagementDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> getSupplyChainPointManagementBySupplyChainPointId(int supplyChainPointId) {
        return this.supplyChainPointManagementRepository.findAll()
                .stream()
                .filter((m) -> m.getSupplyChainPointId() == supplyChainPointId)
                .map(SupplyChainPointManagement::toOutputSupplyChainPointManagementDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> getAllSupplyChainPointManagement() {
        return this.supplyChainPointManagementRepository.findAll()
                .stream()
                .map(SupplyChainPointManagement::toOutputSupplyChainPointManagementDTO)
                .toList();
    }

    public int approveSupplyChainPointManagement(int id, boolean approvalChoice) {
        return this.supplyChainPointManagementRepository.approve(id, approvalChoice).getId();
    }

}

