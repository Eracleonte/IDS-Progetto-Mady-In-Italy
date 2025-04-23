package it.cs.unicam.progetto.mady.in.italy.model.supplychain;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Approvable;
import it.cs.unicam.progetto.mady.in.italy.abstractions.Visualizable;
import it.cs.unicam.progetto.mady.in.italy.dto.input.InputSupplyChainPointDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputSupplyChainPointDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a supply chain point
 */
@Entity
public class SupplyChainPoint implements Visualizable, Approvable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float latitude;

    private float longitude;

    private String name;

    private boolean isProduction;

    private boolean isTransformation;

    private boolean isDistribution;

    private boolean isResale;

    private boolean approved;

    public SupplyChainPoint(float latitude, float longitude, String name) {
        if (latitude < -90.0f || latitude > 90.0f)
            throw new IllegalArgumentException();
        this.latitude = latitude;
        if (longitude < -180.0f || longitude > 180.0f)
            throw new IllegalArgumentException();
        this.longitude = longitude;
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();
        this.name = name;
    }

    public SupplyChainPoint() {}

    @Override
    public int getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public boolean isProduction() {
        return isProduction;
    }

    public boolean isTransformation() {
        return isTransformation;
    }

    public boolean isDistribution() {
        return isDistribution;
    }

    public boolean isResale() {
        return isResale;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        this.id = id;
    }

    public void setLatitude(float latitude) {
        if (latitude < -90.0f || latitude > 90.0f)
            throw new IllegalArgumentException();
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        if (longitude < -180.0f || longitude > 180.0f)
            throw new IllegalArgumentException();
        this.longitude = longitude;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();
        this.name = name;
    }

    public void setProduction(boolean production) {
        isProduction = production;
    }

    public void setTransformation(boolean transformation) {
        isTransformation = transformation;
    }

    public void setDistribution(boolean distribution) {
        isDistribution = distribution;
    }

    public void setResale(boolean resale) {
        isResale = resale;
    }

    @Override
    public boolean isApproved() {
        return this.approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public static SupplyChainPoint getSupplyChainPointFromInputSupplyChainPointDTO(InputSupplyChainPointDTO dto) {
        SupplyChainPoint s = new SupplyChainPoint(dto.latitude(), dto.longitude(), dto.name());
        s.setProduction(dto.isProduction());
        s.setTransformation(dto.isTransformation());
        s.setDistribution(dto.isDistribution());
        s.setResale(dto.isResale());
        return s;
    }

    @Override
    public OutputSupplyChainPointDTO getOutputDTO() {
        return new OutputSupplyChainPointDTO(this.id,
                this.latitude,
                this.longitude,
                this.name,
                this.isProduction,
                this.isTransformation,
                this.isDistribution,
                this.isResale
        );
    }

}