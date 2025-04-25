package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.input.SupplyChainPointSearchFilterDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPointManagement;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointManagementRepository;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class SupplyChainPointHandler {

    private final SupplyChainPointRepository scpRepository;

    private final SupplyChainPointManagementRepository scpmRepository;

    public SupplyChainPointHandler(SupplyChainPointRepository scpRepository,
                                   SupplyChainPointManagementRepository scpmRepository) {
        if (scpRepository == null)
            throw new NullPointerException("scpRepository is null");
        if (scpmRepository == null)
            throw new NullPointerException("scpmRepository is null");
        this.scpRepository = scpRepository;
        this.scpmRepository = scpmRepository;
    }

    /**
     *
     * Saves a new SupplyChainPoint in the system and generates the initial SupplyChainPointManagement.
     * Initially the new SupplyChainPoint will result as "to be managed" by the User having the userId.
     *
     * @param dto the InputSupplyChainPointDTO that will be used in order to create the new SupplyChainPoint.
     * @param userId the id of the user creating the new SupplyChainPoint and that should be considered as its first manager.
     * @return the id of the new SupplyChainPoint.
     */
    public int saveSupplyChainPoint(InputSupplyChainPointDTO dto, int userId) {
        SupplyChainPoint toSave = SupplyChainPoint.getSupplyChainPointFromInputSupplyChainPointDTO(dto);
        toSave.setId(scpRepository.getNextId());
        int scpId = this.scpRepository.save(toSave).getId();
        saveSupplyChainPointManagement(scpId, userId);
        return scpId;
    }

    public OutputSupplyChainPointDTO findSupplyChainPointById(Integer id) {
        SupplyChainPoint scp = this.scpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find supply chain point with id: " + id));
        return scp.getOutputDTO();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPoints(boolean approved) {
        return this.scpRepository.findAll()
                .stream()
                .filter((scp) -> scp.isApproved() == approved)
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPointsFiltered(SupplyChainPointSearchFilterDTO filter,
                                                                            boolean approved) {
        List<OutputSupplyChainPointDTO> scps = this.scpRepository.findAll().stream()
                .filter((scp) -> scp.isApproved() == approved)
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
        if (filter.supplyChainPointName() != null)
            scps = scps.stream().filter((scp) -> scp.name().equalsIgnoreCase(filter.supplyChainPointName())).toList();
        scps = retrieveSupplyChainPointsSpecializedIn(scps, filter.isProduction(), filter.isTransformation(),filter.isDistribution(),filter.isResale());
        if (filter.managerId() != 0)
            scps = retrieveSupplyChainPointsManagedBy(scps, filter.managerId(), approved);
        return scps;
    }

    /**
     *
     * Retrieves, from a List of OutputSupplyChainPointDTOS, only the OutputSupplyChainPointDTOS
     * that respect the given business specializations.
     *
     * @param scps the input List of OutputSupplyChainPointDTOS.
     * @param isProduction equals true if Production supply chain points are being searched.
     * @param isTransformation equals true if Production supply chain points are being searched.
     * @param isDistribution equals true if Production supply chain points are being searched.
     * @param isResale equals true if Production supply chain points are being searched.
     * @return a List of OutputSupplyChainPointDTOS that respect the given business specializations.
     */
    private List<OutputSupplyChainPointDTO> retrieveSupplyChainPointsSpecializedIn(List<OutputSupplyChainPointDTO> scps,
                                                                                   boolean isProduction,
                                                                                   boolean isTransformation,
                                                                                   boolean isDistribution,
                                                                                   boolean isResale) {
        return scps.stream()
                .filter((scp) -> scp.isProduction() == isProduction)
                .filter((scp) -> scp.isTransformation() == isTransformation)
                .filter((scp) -> scp.isDistribution() == isDistribution)
                .filter((scp) -> scp.isResale() == isResale)
                .toList();
    }

    /**
     *
     * Retrieves, from a List of OutputSupplyChainPointDTOS, only the OutputSupplyChainPointDTOS
     * that are managed by the required user.
     *
     * @param scps the input List of OutputSupplyChainPointDTOS.
     * @param userId the id of the user.
     * @param approved depends on which supply chain points are being searched.
     *                 (Ex. if looking for approved supply chain points,
     *                 only supply chain points that have approved management for the specified user will be gathered)
     * @return the List of OutputSupplyChainPointDTOS that are managed by the required user.
     */
    private List<OutputSupplyChainPointDTO> retrieveSupplyChainPointsManagedBy(List<OutputSupplyChainPointDTO> scps,
                                                                               int userId,
                                                                               boolean approved) {
        List<Integer> scpms = this.findAllSupplyChainPointManagementByUserId(userId, approved).stream()
                .map(OutputSupplyChainPointManagementDTO::supplyChainPointId).toList();
        return scps.stream().filter((scp) -> scpms.contains(scp.getId())).toList();
    }

    public String approveSupplyChainPoint(int id, boolean approvalChoice) {
        boolean result = this.scpRepository.approve(id, approvalChoice);
        if (!result)
            return "Supply Chain Point with Id " + id + " has been rejected";
        else
            return "Supply Chain Point with Id " + id + " has been approved";
    }

    public int saveSupplyChainPointManagement(int supplyChainPointId, int userId) {
        int id = this.scpmRepository.getNextId();
        SupplyChainPointManagement supplyChainPointManagement =
                new SupplyChainPointManagement(id, supplyChainPointId, userId);
        return this.scpmRepository.save(supplyChainPointManagement).getId();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagementByUserId(int userId, boolean approved) {
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.getUserId() == userId)
                .filter((management) -> management.isApproved() == approved)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagementBySupplyChainPointId(int supplyChainPointId,
                                                                                                           boolean approved) {
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.getSupplyChainPointId() == supplyChainPointId)
                .filter((management) -> management.isApproved() == approved)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointManagementDTO> findAllSupplyChainPointManagement(boolean approved) {
        return this.scpmRepository.findAll()
                .stream()
                .filter((management) -> management.isApproved() == approved)
                .map(SupplyChainPointManagement::getOutputDTO)
                .toList();
    }

    public String approveSupplyChainPointManagement(int id, boolean approvalChoice) {
        boolean result = this.scpmRepository.approve(id, approvalChoice);
        if (!result)
            return "Supply Chain Point Management with Id " + id + " has been rejected";
        else
            return "Supply Chain Point Management with Id " + id + " has been approved";
    }

}
