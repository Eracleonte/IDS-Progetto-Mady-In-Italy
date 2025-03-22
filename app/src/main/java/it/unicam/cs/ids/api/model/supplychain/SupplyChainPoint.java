package it.unicam.cs.ids.api.model.supplychain;

import it.unicam.cs.ids.api.abstractions.Identifiable;
import it.unicam.cs.ids.api.dto.output.OutputSupplyChainPointDTO;

/**
 * Represents a supply chain point
 */
public class SupplyChainPoint implements Identifiable {

    private int id;

    private float latitude;

    private float longitude;

    private String name;

    private boolean isProduction;

    private boolean isTransformation;

    private boolean isDistribution;

    private boolean isResale;

    public SupplyChainPoint() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        if (latitude < -90.0f || latitude > 90.0f)
            throw new IllegalArgumentException();
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        if (longitude < -180.0f || longitude > 180.0f)
            throw new IllegalArgumentException();
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();
        this.name = name;
    }

    public boolean isProduction() {
        return isProduction;
    }

    public void setProduction(boolean production) {
        isProduction = production;
    }

    public boolean isTransformation() {
        return isTransformation;
    }

    public void setTransformation(boolean transformation) {
        isTransformation = transformation;
    }

    public boolean isDistribution() {
        return isDistribution;
    }

    public void setDistribution(boolean distribution) {
        isDistribution = distribution;
    }

    public boolean isResale() {
        return isResale;
    }

    public void setResale(boolean resale) {
        isResale = resale;
    }

    @Override
    public String toString() {
        return "SupplyChainPoint{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", isProduction=" + isProduction +
                ", isTransformation=" + isTransformation +
                ", isDistribution=" + isDistribution +
                ", isResale=" + isResale +
                '}';
    }

    public OutputSupplyChainPointDTO getOutputSupplyChainPointDTO() {
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