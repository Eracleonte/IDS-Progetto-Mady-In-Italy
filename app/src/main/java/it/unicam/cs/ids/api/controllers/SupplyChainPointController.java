package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;
import it.unicam.cs.ids.api.handlers.SupplyChainPointHandler;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.List;
import java.util.function.Predicate;

public class SupplyChainPointController {

    private SupplyChainPointHandler supplyChainPointHandler;

    public SupplyChainPointController(SupplyChainPointHandler supplyChainPointHandler) {
        this.supplyChainPointHandler = supplyChainPointHandler;
    }

    // READ

    public OutputSupplyChainPointDTO getSupplyChainPointById(int id) {
        return this.supplyChainPointHandler.findSupplyChainPointById(id);
    }

    public List<OutputSupplyChainPointDTO> getAllSupplyChainPoints() {
        return this.supplyChainPointHandler.findAllSupplyChainPoints();
    }

    public List<OutputSupplyChainPointDTO> getSupplyChainPointsIf(Predicate<? super SupplyChainPoint> filter) {
        return this.supplyChainPointHandler.getSupplyChainPointsIf(filter);
    }

}
