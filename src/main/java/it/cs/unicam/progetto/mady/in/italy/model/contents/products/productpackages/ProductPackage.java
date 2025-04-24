package it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages;

import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputRawProductDTO;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputTransformedProductDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.Product;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputProductPackageDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ProductPackage extends Product {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RawProduct> rawProducts;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TransformedProduct> transformedProducts;

    public ProductPackage() {
        this.rawProducts = new HashSet<>();
        this.transformedProducts = new HashSet<>();
    }

    public void addRawProduct(RawProduct rawProduct) {
        this.rawProducts.add(rawProduct);
    }

    public void addTransformedProduct(TransformedProduct transformedProduct) {
        this.transformedProducts.add(transformedProduct);
    }

    @Transient
    @Override
    public OutputProductPackageDTO getOutputDTO() {
        return new OutputProductPackageDTO(
                this.getId(),
                this.getSupplyChainPoint().getId(),
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
