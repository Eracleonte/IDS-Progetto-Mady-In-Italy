package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.model.contents.productsonsale.ProductOnSale;

import java.util.*;

public class ProductOnSaleRepository implements Repository<ProductOnSale , Integer> {

    private Map<Integer, ProductOnSale> productOnSaleMap;

    private int nextProductOnSaleId;

    public ProductOnSaleRepository() {
        this.productOnSaleMap = new HashMap<>();
        this.nextProductOnSaleId = 1;
    }

    @Override
    public ProductOnSale save(ProductOnSale element) {
        element.setProductId(nextProductOnSaleId);
        this.productOnSaleMap.put(nextProductOnSaleId, element);
        this.nextProductOnSaleId++;
        return element;
    }

    @Override
    public Optional<ProductOnSale> findById(Integer id) {
        return Optional.ofNullable(this.productOnSaleMap.get(id));
    }

    @Override
    public List<ProductOnSale> findAll() {
        return new ArrayList<>(this.productOnSaleMap.values());
    }

}
