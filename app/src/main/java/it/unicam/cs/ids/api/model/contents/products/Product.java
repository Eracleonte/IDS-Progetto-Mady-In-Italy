package it.unicam.cs.ids.api.model.contents.products;

import it.unicam.cs.ids.api.model.contents.Content;

/**
 * Represents a Product
 */
public abstract class Product implements Content {

    private int id;

    private int supplyChainPointId;

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
    public int getSupplyChainPointId() {
        return this.supplyChainPointId;
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
    public void setContentId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Content id must be a positive integer");
        this.id = id;
    }

    @Override
    public void setSupplyChainPointId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("SupplyChainPointId id must be a positive integer");
        this.supplyChainPointId = id;
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
