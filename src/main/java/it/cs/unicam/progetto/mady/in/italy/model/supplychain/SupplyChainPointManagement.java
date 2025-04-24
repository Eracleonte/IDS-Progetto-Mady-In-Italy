package it.cs.unicam.progetto.mady.in.italy.model.supplychain;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Approvable;
import it.cs.unicam.progetto.mady.in.italy.abstractions.Visualizable;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputSupplyChainPointManagementDTO;
import it.cs.unicam.progetto.mady.in.italy.model.user.User;
import jakarta.persistence.*;

/**
 * Represents a Supply Chain Point Management
 */
@Entity
public class SupplyChainPointManagement implements Visualizable, Approvable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "supplyChainPointId", referencedColumnName = "id", nullable = false)
    private SupplyChainPoint supplyChainPoint;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    private boolean approved;

    public SupplyChainPointManagement(SupplyChainPoint supplyChainPoint, User user) {
        if (supplyChainPoint == null)
            throw new NullPointerException("supplyChainPoint is null");
        if (user == null)
            throw new NullPointerException("user is null");
        this.supplyChainPoint = supplyChainPoint;
        this.user = user;
    }

    public SupplyChainPointManagement() {

    }

    @Override
    public int getId() {
        return id;
    }


    public SupplyChainPoint getSupplyChainPoint() {
        return supplyChainPoint;
    }

    public User getUser() {
        return user;
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
        return new OutputSupplyChainPointManagementDTO(this.id, this.supplyChainPoint.getId(), this.user.getId());
    }

}
