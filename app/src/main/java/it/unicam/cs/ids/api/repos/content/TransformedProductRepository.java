package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.repos.Repository;

import java.util.*;

public class TransformedProductRepository implements Repository<TransformedProduct,Integer> {

    private Map<Integer, TransformedProduct> transformedProductMap;

    private int nextTransformedProductId;

    public TransformedProductRepository() {
        this.transformedProductMap = new HashMap<>();
        this.nextTransformedProductId = 1;
    }

    @Override
    public TransformedProduct save(TransformedProduct element) {
        element.setContentId(nextTransformedProductId);
        this.transformedProductMap.put(element.getContentId(), element);
        this.nextTransformedProductId++;
        return element;
    }

    @Override
    public Optional<TransformedProduct> findById(Integer id) {
        return Optional.ofNullable(transformedProductMap.get(id));
    }

    @Override
    public List<TransformedProduct> findAll() {
        return new ArrayList<>(transformedProductMap.values());
    }

}
