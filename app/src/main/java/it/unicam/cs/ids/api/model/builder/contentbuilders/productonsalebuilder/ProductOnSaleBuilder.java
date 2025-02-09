package it.unicam.cs.ids.api.model.builder.contentbuilders.productonsalebuilder;

import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.productsonsale.ProductOnSale;

public class ProductOnSaleBuilder implements ContentBuilder {

    private ProductOnSale productOnSale;

    public ProductOnSaleBuilder() {
        this.productOnSale = new ProductOnSale();
    }

    @Override
    public void setContentID(int contentID) {
        this.productOnSale.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.productOnSale.setSupplyChainPointId(supplyChainPointID);
    }

    public void setProductId(int productId) {
        this.productOnSale.setProductId(productId);
    }

    public void setProductType(String productType) {
        productOnSale.setProductType(productType);
    }

    @Override
    public void setName(String name) {
        productOnSale.setName(name);
    }

    @Override
    public void setDescription(String description) {
        productOnSale.setDescription(description);

    }

    @Override
    public void setAuthor(String author) {
        productOnSale.setAuthor(author);
    }

    public void setPrice(float price) {
        productOnSale.setPrice(price);
    }

    public void setQuantity(int quantity) {
        productOnSale.setQuantity(quantity);
    }

    @Override
    public Content getResult() {
        return productOnSale;
    }

    @Override
    public void reset() {
        this.productOnSale = new ProductOnSale();
    }

}
