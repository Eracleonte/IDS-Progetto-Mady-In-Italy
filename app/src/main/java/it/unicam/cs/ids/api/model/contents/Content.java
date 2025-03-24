package it.unicam.cs.ids.api.model.contents;

import it.unicam.cs.ids.api.abstractions.Identifiable;

/**
 * Represents a content in the system
 */
public interface Content extends Identifiable {

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

}
