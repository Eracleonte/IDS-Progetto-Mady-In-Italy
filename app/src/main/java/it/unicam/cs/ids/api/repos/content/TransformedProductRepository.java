package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.repos.Repository;

import java.util.*;

public class TransformedProductRepository extends Repository<TransformedProduct> {

    private static TransformedProductRepository instance;

    private TransformedProductRepository() {
        super();
    }

    public static TransformedProductRepository getInstance() {
        if (instance == null) instance = new TransformedProductRepository();
        return instance;
    }

}
