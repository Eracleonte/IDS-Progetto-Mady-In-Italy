package it.unicam.cs.ids.api.model.contents;

/**
 * Represents a content managed by a specific supply chain point
 */
public class ManagedContent {

    private int id;

    private int supplyChainPointId;

    private int contentId;

    private String contentType;

    public ManagedContent(int id, int supplyChainPointId, int contentId, ContentType contentType) {
        if (id < 0)
            throw new IllegalArgumentException("Id must be a positive integer");
        if (supplyChainPointId < 0)
            throw new IllegalArgumentException("SupplyChainPointId cannot be lesser than 0");
        if (contentId < 0)
            throw new IllegalArgumentException("ContentId cannot be lesser than 0");
        this.id = id;
        this.supplyChainPointId = supplyChainPointId;
        this.contentId = contentId;
        this.contentType = contentType.getValue();
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

}
