package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;
import it.unicam.cs.ids.api.repos.supplychain.SupplyChainPointRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class SupplyChainPointHandler {

    private SupplyChainPointRepository scpRepository;

    public SupplyChainPointHandler(SupplyChainPointRepository scpRepository) {
        this.scpRepository = scpRepository;
    }

    public OutputSupplyChainPointDTO findSupplyChainPointById(Integer id) {
        SupplyChainPoint scp = this.scpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find supply chain point with id: " + id));
        return scp.getOutputSupplyChainPointDTO();
    }

    public List<OutputSupplyChainPointDTO> findAllSupplyChainPoints() {
        return this.scpRepository.findAll()
                .stream()
                .map(SupplyChainPoint::getOutputSupplyChainPointDTO)
                .toList();
    }

    public List<OutputSupplyChainPointDTO> getSupplyChainPointsIf(Predicate<? super SupplyChainPoint> filter) {
        return this.scpRepository.findAll()
                .stream()
                .filter(filter)
                .map(SupplyChainPoint::getOutputSupplyChainPointDTO)
                .toList();
    }

}
