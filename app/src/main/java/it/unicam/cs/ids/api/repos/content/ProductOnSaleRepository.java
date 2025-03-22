package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.productsonsale.ProductOnSale;
import it.unicam.cs.ids.api.repos.Repository;

public class ProductOnSaleRepository extends Repository<ProductOnSale> {

    private static ProductOnSaleRepository instance;

    public ProductOnSaleRepository() {
        super();
    }

    public static ProductOnSaleRepository getInstance() {
        if (instance == null) instance = new ProductOnSaleRepository();
        return instance;
    }

}
