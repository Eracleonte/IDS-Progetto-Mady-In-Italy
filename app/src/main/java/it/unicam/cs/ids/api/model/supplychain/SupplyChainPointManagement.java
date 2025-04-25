package it.unicam.cs.ids.api.model.supplychain;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Visualizable;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointManagementDTO;

public class SupplyChainPointManagement implements Visualizable, Approvable {

    private final int id;

    private final int supplyChainPointId;

    private final int userId;

    private boolean approved;

    public SupplyChainPointManagement(int id, int supplyChainPointId, int userId) {
        this.id = id;
        this.supplyChainPointId = supplyChainPointId;
        this.userId = userId;
    }

    @Override
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

    @Override
    public OutputSupplyChainPointManagementDTO getOutputDTO() {
        return new OutputSupplyChainPointManagementDTO(this.id, this.supplyChainPointId, this.userId);
    }

}
