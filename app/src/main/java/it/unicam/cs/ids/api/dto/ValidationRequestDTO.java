package it.unicam.cs.ids.api.dto;

public class ValidationRequestDTO {

    private int id;
    private int supplyChainPointId;
    private int contentId;
    private String contentType;

    public ValidationRequestDTO() {
    }

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

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
