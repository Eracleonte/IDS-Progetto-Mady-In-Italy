package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.repos.Repository;

public class ProductPackageRepository extends Repository<ProductPackage> {

    private static ProductPackageRepository instance;

    private ProductPackageRepository() {
        super();
    }

    public static ProductPackageRepository getInstance() {
        if (instance == null) instance = new ProductPackageRepository();
        return instance;
    }

}
