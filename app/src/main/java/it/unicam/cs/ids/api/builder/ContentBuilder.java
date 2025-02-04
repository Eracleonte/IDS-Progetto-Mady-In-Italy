package it.unicam.cs.ids.api.builder;

import it.unicam.cs.ids.api.contents.Content;

public interface ContentBuilder {

    void setContentID(int contentID);

    void setSupplyChainPointID(int supplyChainPointID);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

    Content getResult();

    void reset();

}
