package it.unicam.cs.ids.api.model.builder.contentbuilders;

import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;

/**
 *
 * Represents a Builder for Content entities.
 *
 * @param <C> the particular type of Content this Content Builder instance builds.
 */
public interface ContentBuilder<C extends Content> {

    void setSupplyChainPointID(int supplyChainPointID);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

    C getResult();

    void reset();

    ContentType supports();

}
