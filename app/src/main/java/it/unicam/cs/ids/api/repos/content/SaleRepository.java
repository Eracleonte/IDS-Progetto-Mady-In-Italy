package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.sale.Sale;
import it.unicam.cs.ids.api.repos.Repository;

public class SaleRepository extends Repository<Sale> {

    private static SaleRepository instance;

    private SaleRepository() {
        super();
    }

    public static SaleRepository getInstance() {
        if (instance == null) instance = new SaleRepository();
        return instance;
    }

}
