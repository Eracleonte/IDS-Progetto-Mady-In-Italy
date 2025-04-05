package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;

public class ProductPackageBuilder implements ContentBuilder<ProductPackage> {

    private ProductPackage productPackage;

    public ProductPackageBuilder() {
        this.productPackage = new ProductPackage();
    }

    public ProductPackage buildProductPackageFromDTO(InputProductPackageDTO inputProductPackageDTO) {
        this.reset();
        this.setSupplyChainPointID(inputProductPackageDTO.supplyChainPointId());
        this.setName(inputProductPackageDTO.name());
        this.setDescription(inputProductPackageDTO.description());
        this.setAuthor(inputProductPackageDTO.author());
        return this.productPackage;
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
    public ProductPackage getResult() {
        return this.productPackage;
    }

    @Override
    public void reset() {
        this.productPackage = new ProductPackage();
    }

    @Override
    public ContentType supports() {
        return ContentType.PRODUCT_PACKAGE;
    }

}
