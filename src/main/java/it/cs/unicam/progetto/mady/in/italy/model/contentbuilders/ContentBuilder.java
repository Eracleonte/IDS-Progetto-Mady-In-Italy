package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders;

import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;

/**
 *
 * Represents a Builder for Content entities.
 *
 * @param <C> the particular type of Content this Content Builder instance builds.
 */
public interface ContentBuilder<C extends Content> {

    //void setSupplyChainPointID(int supplyChainPointID);

    void setSupplyChainPoint(SupplyChainPoint supplyChainPoint);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

    C getResult();

    void reset();

    ContentType supports();

}
