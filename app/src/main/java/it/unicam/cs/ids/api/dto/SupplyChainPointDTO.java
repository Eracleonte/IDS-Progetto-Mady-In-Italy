package it.unicam.cs.ids.api.dto;

public class SupplyChainPointDTO {

    private int id;
    private float latitude;
    private float longitude;
    private String name;
    private boolean isProduction;
    private boolean isTransformation;
    private boolean isDistribution;
    private boolean isResale;

    public SupplyChainPointDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

}
