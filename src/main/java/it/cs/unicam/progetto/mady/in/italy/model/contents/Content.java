package it.cs.unicam.progetto.mady.in.italy.model.contents;

import it.cs.unicam.progetto.mady.in.italy.abstractions.Approvable;
import it.cs.unicam.progetto.mady.in.italy.abstractions.Visualizable;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;

/**
 * Represents a content in the system
 */
public interface Content extends Visualizable, Approvable {

    //int getSupplyChainPointId();

    SupplyChainPoint getSupplyChainPoint();

    String getName();

    String getDescription();

    String getAuthor();

    void setContentId(int id);

    //void setSupplyChainPointId(int id);

    void setSupplyChainPoint(SupplyChainPoint supplyChainPoint);

    void setName(String name);

    void setDescription(String description);

    void setAuthor(String author);

}
