package it.unicam.cs.ids.api.model.builder.contentbuilders;

import it.unicam.cs.ids.api.model.contents.Content;

public interface ContentBuilder<C extends Content> {

    void setContentID(int contentID);

    void setSupplyChainPointID(int supplyChainPointID);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

    C getResult();

    void reset();

}
