package it.unicam.cs.ids.api.model.contents.sale;

import it.unicam.cs.ids.api.dto.output.OutputSaleDTO;
import it.unicam.cs.ids.api.model.contents.Content;

/**
 * Represents a Product set on sale
 */
public class Sale implements Content {

    private int id;

    private int supplyChainPointId;

    private int productId;

    private String productType;

    private String name;

    private String description;

    private String author;

    private boolean approved;

    private float price;

    private int quantity;

    public Sale() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getSupplyChainPointId() {
        return this.supplyChainPointId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
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

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
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

    public void setProductId(int productId) {
        if (productId < 0)
            throw new IllegalArgumentException("Illegal product ID");
        this.productId = productId;
    }

    /**
     * Sets the product type of the product set on sale.
     * Once set the product type cannot be changed.
     *
     * @param productType the type of the product set on sale.
     */
    public void setProductType(String productType) {
        if (productType == null)
            throw new NullPointerException("Null product type");
        if (this.productType == null)
            this.productType = productType;
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

    public void setPrice(float price) {
        if (price <= 0.0f)
            throw new IllegalArgumentException("Price must be greater than zero");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        this.quantity = quantity;
    }

    public void reduceSaleQuantity(int quantity) {
        if (quantity < 0 || quantity > this.quantity)
            throw new IllegalArgumentException("Quantity must be greater than zero and lesser than the current quantity");
        this.quantity -= quantity;
    }

    public void updateSaleQuantity(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        this.quantity += quantity;
    }

    @Override
    public OutputSaleDTO getOutputDTO() {
        return new OutputSaleDTO(this.id ,
                this.supplyChainPointId ,
                this.productId ,
                this.productType ,
                this.name ,
                this.description ,
                this.author ,
                this.price ,
                this.quantity);
    }

}
