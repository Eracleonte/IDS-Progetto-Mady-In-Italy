package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.repos.Repository;

public class RawProductRepository extends Repository<RawProduct> {

    private static RawProductRepository instance;

    private RawProductRepository() {
        super();
    }

    public static RawProductRepository getInstance() {
        if (instance == null) instance = new RawProductRepository();
        return instance;
    }

}
