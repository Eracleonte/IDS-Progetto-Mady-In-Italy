package it.unicam.cs.ids.api.model.supplychain;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;

public class SupplyChainPointManagement implements Identifiable, Approvable {

    private int id;

    private int supplyChainPointId;

    private int userId;

    private boolean approved;

    public SupplyChainPointManagement(int id, int supplyChainPointId, int userId) {
        this.id = id;
        this.supplyChainPointId = supplyChainPointId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getSupplyChainPointId() {
        return supplyChainPointId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean isApproved() {
        return approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public OutputSupplyChainPointManagementDTO toOutputSupplyChainPointManagementDTO() {
        return new OutputSupplyChainPointManagementDTO(this.id, this.supplyChainPointId, this.userId);
    }

}
