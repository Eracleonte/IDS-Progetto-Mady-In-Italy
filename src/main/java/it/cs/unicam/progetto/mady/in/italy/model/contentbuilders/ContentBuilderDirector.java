package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders;

import it.cs.unicam.progetto.mady.in.italy.dto.input.*;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder.ProductPackageBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder.RawProductBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder.TransformedProductBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.salebuilder.SaleBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.transformationprocessbuilder.TransformationProcessBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.productpackages.ProductPackage;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a director of content builders
 */
@Component
public class ContentBuilderDirector {

    private final Map<ContentType, ContentBuilder<? extends Content>> contentBuilders;

    @Autowired
    public ContentBuilderDirector(List<ContentBuilder<? extends Content>> contentBuilders) {
        if (contentBuilders == null)
            throw new NullPointerException("contentBuilders is null");
        if (contentBuilders.isEmpty())
            throw new IllegalArgumentException("contentBuilders is empty");
        this.contentBuilders = Map.copyOf(contentBuilders.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toMap(ContentBuilder::supports, b -> b)));
    }

    /**
     * Builds a Raw Product from an input raw product dto.
     *
     * @param dto the input raw product dto.
     * @throws IllegalStateException if a raw product builder is not available
     *                               for this director instance.
     * @return a new RawProduct.
     */
    public RawProduct getRawProductFromDto(InputRawProductDTO dto,
                                           SupplyChainPoint supplyChainPoint) {
        RawProductBuilder rawProductBuilder = (RawProductBuilder) this.contentBuilders.get(ContentType.RAW_PRODUCT);
        if (rawProductBuilder == null)
            throw new IllegalStateException("Attempt at creating a raw product when a raw product builder is not available");
        else
            return rawProductBuilder.buildRawProductFromDTO(dto,supplyChainPoint);
    }

    /**
     * Builds a Transformed Product from an input transformed product dto.
     *
     * @param dto the input transformed product dto.
     * @throws IllegalStateException if a transformed product builder is not available
     *                               for this director instance.
     * @return a new TransformedProduct.
     */
    public TransformedProduct getTransformedProductFromDto(InputTransformedProductDTO dto,
                                                           SupplyChainPoint supplyChainPoint) {
        TransformedProductBuilder transformedProductBuilder = (TransformedProductBuilder) this.contentBuilders.get(ContentType.TRANSFORMED_PRODUCT);
        if (transformedProductBuilder == null)
            throw new IllegalStateException("Attempt at creating a transformed product when a transformed product builder is not available");
        else
            return transformedProductBuilder.buildTransformedProductFromDTO(dto,supplyChainPoint);
    }

    /**
     * Builds a Product Package from an input product package dto.
     *
     * @param dto the input product package dto.
     * @throws IllegalStateException if a product package builder is not available
     *                               for this director instance.
     * @return a new ProductPackage.
     */
    public ProductPackage getProductPackageFromDto(InputProductPackageDTO dto,
                                                   SupplyChainPoint supplyChainPoint) {
        ProductPackageBuilder productPackageBuilder = (ProductPackageBuilder) this.contentBuilders.get(ContentType.PRODUCT_PACKAGE);
        if (productPackageBuilder == null)
            throw new IllegalStateException("Attempt at creating a product package when a product package builder is not available");
        else
            return productPackageBuilder.buildProductPackageFromDTO(dto,supplyChainPoint);
    }

    /**
     * Builds a Sale from an input sale dto.
     *
     * @param dto the input sale dto.
     * @throws IllegalStateException if a sale builder is not available for this director instance.
     * @return a new Sale.
     */
    public Sale getSaleFromDto(InputSaleDTO dto,SupplyChainPoint supplyChainPoint) {
        SaleBuilder saleBuilder = (SaleBuilder) this.contentBuilders.get(ContentType.SALE);
        if (saleBuilder == null)
            throw new IllegalStateException("Attempt at creating a sale when a sale builder is not available");
        else
            return saleBuilder.buildProductOnSaleFromDTO(dto,supplyChainPoint);
    }

    /**
     * Builds a TransformationProcess from an input transformation process dto.
     *
     * @param dto the transformation process dto.
     * @throws IllegalStateException if a transformation process builder is not available for this director instance.
     * @return a new TransformationProcess.
     */
    public TransformationProcess getTransformationProcessFromDto(InputTransformationProcessDTO dto,
                                                                 SupplyChainPoint supplyChainPoint) {
        TransformationProcessBuilder transformationProcessBuilder = (TransformationProcessBuilder) this.contentBuilders.get(ContentType.TRANSFORMATION_PROCESS);
        if (transformationProcessBuilder == null)
            throw new IllegalStateException("Attempt at creating a sale when a sale builder is not available");
        else
            return transformationProcessBuilder.buildTransformationProcessFromDTO(dto,supplyChainPoint);
    }

    /**
     * Returns a list of content types the builders, of this content builder director instance,
     * are able to build.
     *
     * @return a list of content types.
     */
    public List<ContentType> supportedContentTypes() {
        return this.contentBuilders.keySet().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
