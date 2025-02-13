package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackageElement;

import java.util.*;

public class ProductPackageRepository implements Repository<ProductPackage, Integer> {

    private Map<Integer, ProductPackage> productPackageMap;

    private int nextPackageId;

    public ProductPackageRepository() {
        this.productPackageMap = new HashMap<>();
        this.nextPackageId = 1;
    }

    @Override
    public ProductPackage save(ProductPackage element) {
        element.setContentId(nextPackageId);
        productPackageMap.put(nextPackageId, element);
        this.nextPackageId++;
        return null;
    }

    @Override
    public Optional<ProductPackage> findById(Integer id) {
        return Optional.ofNullable(this.productPackageMap.get(id));
    }

    @Override
    public List<ProductPackage> findAll() {
        return new ArrayList<>(this.productPackageMap.values());
    }

}
