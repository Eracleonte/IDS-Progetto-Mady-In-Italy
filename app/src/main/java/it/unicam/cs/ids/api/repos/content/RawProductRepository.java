package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.repos.Repository;

import java.util.*;

public class RawProductRepository implements Repository<RawProduct, Integer> {

    private Map<Integer,RawProduct> rawProductMap;

    // Will be obsolete in future
    private int nextRawProductId;

    public RawProductRepository() {
        this.rawProductMap = new HashMap<>();
        this.nextRawProductId = 1;
    }

    @Override
    public RawProduct save(RawProduct element) {
        element.setContentId(nextRawProductId);
        this.rawProductMap.put(element.getContentId(), element);
        this.nextRawProductId++;
        return element;
    }

    @Override
    public Optional<RawProduct> findById(Integer id) {
        return Optional.ofNullable(rawProductMap.get(id));
    }

    @Override
    public List<RawProduct> findAll() {
        return new ArrayList<>(rawProductMap.values());
    }

}
