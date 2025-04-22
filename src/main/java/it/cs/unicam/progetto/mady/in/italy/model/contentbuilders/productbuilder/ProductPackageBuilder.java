package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputProductPackageDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.stereotype.Component;

@Component
public class ProductPackageBuilder implements ContentBuilder<ProductPackage> {

    private ProductPackage productPackage;

    public ProductPackageBuilder() {
        this.productPackage = new ProductPackage();
    }

    /**
     * Builds a Product Package from a InputProductPackageDTO
     *
     * @param inputProductPackageDTO the dto used to build a ProductPackage.
     * @return a new ProductPackage instance.
     */
    public ProductPackage buildProductPackageFromDTO(InputProductPackageDTO inputProductPackageDTO,
                                                     SupplyChainPoint supplyChainPoint) {
        this.reset();
        //this.setSupplyChainPointID(inputProductPackageDTO.supplyChainPointId());
        this.setSupplyChainPoint(supplyChainPoint);
        this.setName(inputProductPackageDTO.name());
        this.setDescription(inputProductPackageDTO.description());
        this.setAuthor(inputProductPackageDTO.author());
        return this.productPackage;
    }

    //@Override
    //public void setSupplyChainPointID(int supplyChainPointID) {
    //    this.productPackage.setSupplyChainPointId(supplyChainPointID);
    //}


    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        this.productPackage.setSupplyChainPoint(supplyChainPoint);
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
