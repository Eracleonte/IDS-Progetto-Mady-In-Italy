package it.unicam.cs.ids.api.repos.supplychain;

import it.unicam.cs.ids.api.model.contents.ManagedContent;
import it.unicam.cs.ids.api.repos.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SupplyChainPointManagedContentsRepository implements Repository<ManagedContent, Integer> {

    private Map<Integer, ManagedContent> supplyChainPointManagedContentsMap;

    @Override
    public ManagedContent save(ManagedContent element) {
        return this.supplyChainPointManagedContentsMap.put(element.getId(), element);
    }

    @Override
    public Optional<ManagedContent> findById(Integer id) {
        return Optional.ofNullable(supplyChainPointManagedContentsMap.get(id));
    }

    @Override
    public List<ManagedContent> findAll() {
        return new ArrayList<>(supplyChainPointManagedContentsMap.values());
    }

}
