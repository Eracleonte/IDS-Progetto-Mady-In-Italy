package it.unicam.cs.ids.api.repos.supplychain;

import it.unicam.cs.ids.api.model.supplychain.SupplyChainPointManagement;
import it.unicam.cs.ids.api.repos.Repository;

public class SupplyChainPointManagementRepository extends Repository<SupplyChainPointManagement> {

    private static SupplyChainPointManagementRepository instance;

    private SupplyChainPointManagementRepository() {
        super();
    }

    public static SupplyChainPointManagementRepository getInstance() {
        if (instance == null) instance = new SupplyChainPointManagementRepository();
        return instance;
    }

}
