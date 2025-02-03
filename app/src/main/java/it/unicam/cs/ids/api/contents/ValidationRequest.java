package it.unicam.cs.ids.api.contents;

/**
 * Represents a validation request a Curator may handle
 */
public class ValidationRequest {

    private final int supplyChainPointId;

    private final int contentId;

    private final String contentType;

    public ValidationRequest(int supplyChainPointId, int contentId, ContentType contentType) {
        if (supplyChainPointId < 0)
            throw new IllegalArgumentException("SupplyChainPointId cannot be lesser than 0");
        if (contentId < 0)
            throw new IllegalArgumentException("ContentId cannot be lesser than 0");
        this.supplyChainPointId = supplyChainPointId;
        this.contentId = contentId;
        this.contentType = contentType.getValue();
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
