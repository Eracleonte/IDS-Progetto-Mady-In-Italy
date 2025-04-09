package it.unicam.cs.ids.api.model.contents;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Visualizable;

/**
 * Represents a content in the system
 */
public interface Content extends Visualizable, Approvable {

    int getSupplyChainPointId();

    String getContentType(); // TODO possibly marked for removal

    String getName();

    String getDescription();

    String getAuthor();

    void setContentId(int id);

    void setSupplyChainPointId(int id);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

}
