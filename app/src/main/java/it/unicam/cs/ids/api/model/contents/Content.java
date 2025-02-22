package it.unicam.cs.ids.api.model.contents;

/**
 * Represents a content in the system
 */
public interface Content {

    int getContentId();

    int getSupplyChainPointId();

    String getContentType();

    String getName();

    String getDescription();

    String getAuthor();

    boolean isPublished();

    void setContentId(int id);

    void setSupplyChainPointId(int id);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

    void publish();

    void unpublish();

    /**
     * Returns a ValidationRequest from the data contained in this content entity
     *
     * @return ValidationRequest
     */
    ValidationRequest getValidationRequest();

}
