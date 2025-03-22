package it.unicam.cs.ids.api.repos.supplychain;

import it.unicam.cs.ids.api.repos.Repository;
import it.unicam.cs.ids.api.model.supplychain.SupplyChainPoint;

public class SupplyChainPointRepository extends Repository<SupplyChainPoint> {

    private static SupplyChainPointRepository instance;

    private SupplyChainPointRepository() {
        super();
    }

    public static SupplyChainPointRepository getInstance() {
        if (instance == null) instance = new SupplyChainPointRepository();
        return instance;
    }

}
