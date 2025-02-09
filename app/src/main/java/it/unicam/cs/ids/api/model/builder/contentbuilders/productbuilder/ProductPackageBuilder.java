package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;

public class ProductPackageBuilder implements ContentBuilder {

    private ProductPackage productPackage;

    public ProductPackageBuilder() {
        this.productPackage = new ProductPackage();
    }

    @Override
    public void setContentID(int contentID) {
        this.productPackage.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.productPackage.setSupplyChainPointId(supplyChainPointID);
    }

    @Override
    public void setName(String name) {
        this.productPackage.setName(name);
    }

    @Override
    public void setDescription(String description) {
        this.productPackage.setDescription(description);
    }

    @Override
    public void setAuthor(String author) {
        this.productPackage.setAuthor(author);
    }

    @Override
    public Content getResult() {
        return this.productPackage;
    }

    @Override
    public void reset() {
        this.productPackage = new ProductPackage();
    }

}
