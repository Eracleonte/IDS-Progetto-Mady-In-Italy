package it.unicam.cs.ids.api.repos.supplychain;

import it.unicam.cs.ids.api.repos.Repository;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.*;

public class SupplyChainPointRepository implements Repository<SupplyChainPoint,Integer> {

    private Map<Integer, SupplyChainPoint> supplyChainPointMap;

    private int nextSupplyChainPointId;

    public SupplyChainPointRepository() {
        this.supplyChainPointMap = new HashMap<>();
        this.nextSupplyChainPointId = 1;
    }

    @Override
    public SupplyChainPoint save(SupplyChainPoint element) {
        element.setId(this.nextSupplyChainPointId);
        this.supplyChainPointMap.put(element.getId(), element);
        this.nextSupplyChainPointId++;
        return element;
    }

    @Override
    public Optional<SupplyChainPoint> findById(Integer id) {
        return Optional.ofNullable(supplyChainPointMap.get(id));
    }

    @Override
    public List<SupplyChainPoint> findAll() {
        return new ArrayList<>(supplyChainPointMap.values());
    }

}
