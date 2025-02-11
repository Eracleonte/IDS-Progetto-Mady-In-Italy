package it.unicam.cs.ids.api.dto;

import it.unicam.cs.ids.api.model.contents.ContentType;

public class TransformedProductDTO {

    private int id;
    private int supplyChainPointId;
    private String contentType;
    private String name;
    private String description;
    private String author;
    private boolean published;
    private String certification;
    private String variety;
    private int transformationProcessID;

    public TransformedProductDTO() {this.contentType = ContentType.TRANSFORMED_PRODUCT.getValue(); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplyChainPointId() {
        return supplyChainPointId;
    }

    public void setSupplyChainPointId(int supplyChainPointId) {
        this.supplyChainPointId = supplyChainPointId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public int getTransformationProcessID() {
        return transformationProcessID;
    }

    public void setTransformationProcessID(int transformationProcessID) {
        this.transformationProcessID = transformationProcessID;
    }

}
