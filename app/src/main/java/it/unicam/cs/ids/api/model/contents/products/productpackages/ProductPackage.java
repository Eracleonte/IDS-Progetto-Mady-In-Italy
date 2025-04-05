package it.unicam.cs.ids.api.model.contents.products.productpackages;

import it.unicam.cs.ids.api.dto.output.OutputProductPackageDTO;
import it.unicam.cs.ids.api.dto.output.OutputRawProductDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.Product;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPackage extends Product {

    private List<RawProduct> rawProducts;

    private List<TransformedProduct> transformedProducts;

    public ProductPackage() {
        super(ContentType.PRODUCT_PACKAGE);
        this.rawProducts = new ArrayList<>();
        this.transformedProducts = new ArrayList<>();
    }

    public boolean addRawProduct(RawProduct rawProduct) {
        return this.rawProducts.add(rawProduct);
    }

    public boolean addTransformedProduct(TransformedProduct transformedProduct) {
        return this.transformedProducts.add(transformedProduct);
    }

    public boolean addRawProducts(List<RawProduct> rawProducts) {
        return this.rawProducts.addAll(rawProducts);
    }

    public boolean addTransformedProducts(List<TransformedProduct> transformedProducts) {
        return this.transformedProducts.addAll(transformedProducts);
    }

    public List<RawProduct> getRawProducts() {
        return this.rawProducts;
    }

    public List<TransformedProduct> getTransformedProducts() {
        return this.transformedProducts;
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
