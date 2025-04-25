package it.unicam.cs.ids.api.model.contents.products.productpackages;

import it.unicam.cs.ids.api.dto.output.OutputProductPackageDTO;
import it.unicam.cs.ids.api.dto.output.OutputRawProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;
import it.unicam.cs.ids.api.model.contents.products.Product;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPackage extends Product {

    private final List<RawProduct> rawProducts;

    private final List<TransformedProduct> transformedProducts;

    public ProductPackage() {
        this.rawProducts = new ArrayList<>();
        this.transformedProducts = new ArrayList<>();
    }

    public void addRawProduct(RawProduct rawProduct) {
        this.rawProducts.add(rawProduct);
    }

    public void addTransformedProduct(TransformedProduct transformedProduct) {
        this.transformedProducts.add(transformedProduct);
    }

    @Override
    public OutputProductPackageDTO getOutputDTO() {
        return new OutputProductPackageDTO(
                this.getId(),
                this.getSupplyChainPointId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getRawProductsDTO(),
                this.getTransformedProductsDTO()
        );
    }

    private List<OutputRawProductDTO> getRawProductsDTO() {
        return this.rawProducts.stream()
                .map(RawProduct::getOutputDTO)
                .collect(Collectors.toList());
    }

    private List<OutputTransformedProductDTO> getTransformedProductsDTO() {
        return this.transformedProducts.stream()
                .map(TransformedProduct::getOutputDTO)
                .collect(Collectors.toList());
    }

}
