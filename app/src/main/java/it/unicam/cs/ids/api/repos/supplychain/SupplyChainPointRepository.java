package it.unicam.cs.ids.api.repos.supplychain;

import it.unicam.cs.ids.api.repos.Repository;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

import java.util.*;

public class SupplyChainPointRepository implements Repository<SupplyChainPoint,Integer> {

    private Map<Integer, SupplyChainPoint> supplyChainPointMap;

    public SupplyChainPointRepository() {
        this.supplyChainPointMap = new HashMap<>();
    }

    @Override
    public SupplyChainPoint save(SupplyChainPoint element) {
        return this.supplyChainPointMap.put(element.getId(), element);
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
