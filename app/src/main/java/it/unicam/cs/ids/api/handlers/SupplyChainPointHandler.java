package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputSupplyChainPointDTO;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class SupplyChainPointHandler {

    private final SupplyChainPointRepository scpRepository;

    public SupplyChainPointHandler(SupplyChainPointRepository scpRepository) {
        if (scpRepository == null)
            throw new NullPointerException("scpRepository is null");
        this.scpRepository = scpRepository;
    }

    public int saveSupplyChainPoint(InputSupplyChainPointDTO dto) {
        SupplyChainPoint toSave = SupplyChainPoint.getSupplyChainPointFromInputSupplyChainPointDTO(dto);
        toSave.setId(scpRepository.getNextId());
        return this.scpRepository.save(toSave).getId();
    }

    public OutputSupplyChainPointDTO findSupplyChainPointById(Integer id) {
        SupplyChainPoint scp = this.scpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find supply chain point with id: " + id));
        return scp.getOutputDTO();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPoints() {
        return this.scpRepository.findAll()
                .stream()
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
    }

    public List<OutputSupplyChainPointDTO> getSupplyChainPointsIf(Predicate<? super SupplyChainPoint> filter) {
        return this.scpRepository.findAll()
                .stream()
                .filter(filter)
                .map(SupplyChainPoint::getOutputDTO)
                .toList();
    }

    public String approveSupplyChainPoint(int id, boolean approvalChoice) {
        boolean result = this.scpRepository.approve(id, approvalChoice);
        if (!result)
            return "Supply Chain Point with Id " + id + " has been rejected";
        else
            return "Supply Chain Point with Id " + id + " hase been approved";
    }

}
