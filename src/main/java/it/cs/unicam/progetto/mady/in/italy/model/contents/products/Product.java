package it.cs.unicam.progetto.mady.in.italy.model.contents.products;

import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import jakarta.persistence.*;

/**
 * Represents a Product
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product implements Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "supplyChainPointId", referencedColumnName = "id", nullable = false)
    private SupplyChainPoint supplyChainPoint;

    private String name;

    private String description;

    private String author;

    private boolean approved;

    public Product() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public SupplyChainPoint getSupplyChainPoint() {
        return supplyChainPoint;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        if (supplyChainPoint == null)
            throw new IllegalArgumentException("Supply chain point cannot be null");
        this.supplyChainPoint = supplyChainPoint;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;
    }

    @Override
    public void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be null or empty");
        this.author = author;
    }

    @Override
    public boolean isApproved() {
        return this.approved;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
