package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.input.InputProductPackageDTO;
import it.unicam.cs.ids.api.dto.input.InputProductPackageElementDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackageElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPackageBuilder implements ContentBuilder {

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
        this.setProductPackageElements(getProductPackageElements(inputProductPackageDTO.packageElements()));
        return this.productPackage;
    }

    private List<ProductPackageElement> getProductPackageElements
            (List<InputProductPackageElementDTO> inputProductPackageElementDTOS) {
        return inputProductPackageElementDTOS.stream()
                .map(dto -> new ProductPackageElement(dto.productId() , dto.productType()))
                .toList();
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

    public void setProductPackageElements(List<ProductPackageElement> productPackageElements) {
        this.productPackage.setProductsIncluded(productPackageElements);
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
