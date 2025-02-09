package it.unicam.cs.ids.api.model.contents;

/**
 * Represents a validation request a Curator may handle
 */
public class ValidationRequest {

    private int id;

    private int supplyChainPointId;

    private int contentId;

    private String contentType;

    public ValidationRequest() {
    }

    public ValidationRequest(int id, int supplyChainPointId, int contentId, String contentType) {
        if (id < 0)
            throw new IllegalArgumentException("Id must be a positive integer");
        if (supplyChainPointId < 0)
            throw new IllegalArgumentException("SupplyChainPointId cannot be lesser than 0");
        if (contentId < 0)
            throw new IllegalArgumentException("ContentId cannot be lesser than 0");
        this.id = id;
        this.supplyChainPointId = supplyChainPointId;
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public int getSupplyChainPointId() {
        return supplyChainPointId;
    }

    public int getContentId() {
        return contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSupplyChainPointId(int supplyChainPointId) {
        this.supplyChainPointId = supplyChainPointId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
