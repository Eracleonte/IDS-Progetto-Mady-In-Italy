package it.unicam.cs.ids.api.model.contents.products.productpackages;

import it.unicam.cs.ids.api.dto.output.OutputProductPackageDTO;
import it.unicam.cs.ids.api.dto.output.OutputProductPackageElementDTO;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductPackage extends Product {

    // TODO remove productsIncluded... productsIncluded are only supposed to be in dtos

    private List<ProductPackageElement> productsIncluded;

    public ProductPackage() {
        super(ContentType.PRODUCT_PACKAGE);
        this.productsIncluded = new ArrayList<>();
    }

    public List<ProductPackageElement> getProductsIncluded() {
        return productsIncluded;
    }

    public void setProductsIncluded(List<ProductPackageElement> productsIncluded) {
        if (productsIncluded == null || productsIncluded.isEmpty())
            throw new IllegalArgumentException("productsIncluded cannot be null or empty");
        this.productsIncluded = productsIncluded;
    }

    public OutputProductPackageDTO getOutputProductPackageDTO() {
        List<OutputProductPackageElementDTO> outputProductPackageElementDTOs = this.productsIncluded
                .stream()
                .map(ProductPackageElement::getOutputProductPackageElementDTO)
                .toList();
        return new OutputProductPackageDTO(this.getContentId(),
                this.getSupplyChainPointId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                outputProductPackageElementDTOs
        );
    }

}
