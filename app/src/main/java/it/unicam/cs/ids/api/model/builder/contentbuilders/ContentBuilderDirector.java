package it.unicam.cs.ids.api.model.builder.contentbuilders;

import it.unicam.cs.ids.api.dto.input.*;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.ProductPackageBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.RawProductBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder.TransformedProductBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.salebuilder.SaleBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder.TransformationProcessBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.productpackages.ProductPackage;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;
import it.unicam.cs.ids.api.model.contents.sale.Sale;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a director of content builders
 */
public class ContentBuilderDirector {

    private final Map<ContentType, ContentBuilder<? extends Content>> contentBuilders;

    public ContentBuilderDirector(List<ContentBuilder<? extends Content>> contentBuilders) {
        if (contentBuilders == null)
            throw new NullPointerException("contentBuilders is null");
        if (contentBuilders.isEmpty())
            throw new IllegalArgumentException("contentBuilders is empty");
        List<ContentBuilder<? extends Content>> builders = List.copyOf(contentBuilders.stream().filter(Objects::nonNull).toList());
        this.contentBuilders = Map.copyOf(builders.stream().collect(Collectors.toMap(ContentBuilder::supports, b -> b)));
    }

    /**
     * Builds a Raw Product from an input raw product dto.
     *
     * @param dto the input raw product dto.
     * @throws IllegalStateException if a raw product builder is not available
     *                               for this director instance.
     * @return a new RawProduct.
     */
    public RawProduct getRawProductFromDto(InputRawProductDTO dto) {
        RawProductBuilder rawProductBuilder = (RawProductBuilder) this.contentBuilders.get(ContentType.RAW_PRODUCT);
        if (rawProductBuilder == null)
            throw new IllegalStateException("Attempt at creating a raw product when a raw product builder is not available");
        else
            return rawProductBuilder.buildRawProductFromDTO(dto);
    }

    /**
     * Builds a Transformed Product from an input transformed product dto.
     *
     * @param dto the input transformed product dto.
     * @throws IllegalStateException if a transformed product builder is not available
     *                               for this director instance.
     * @return a new TransformedProduct.
     */
    public TransformedProduct getTransformedProductFromDto(InputTransformedProductDTO dto) {
        TransformedProductBuilder transformedProductBuilder = (TransformedProductBuilder) this.contentBuilders.get(ContentType.TRANSFORMED_PRODUCT);
        if (transformedProductBuilder == null)
            throw new IllegalStateException("Attempt at creating a transformed product when a transformed product builder is not available");
        else
            return transformedProductBuilder.buildTransformedProductFromDTO(dto);
    }

    /**
     * Builds a Product Package from an input product package dto.
     *
     * @param dto the input product package dto.
     * @throws IllegalStateException if a product package builder is not available
     *                               for this director instance.
     * @return a new ProductPackage.
     */
    public ProductPackage getProductPackageFromDto(InputProductPackageDTO dto) {
        ProductPackageBuilder productPackageBuilder = (ProductPackageBuilder) this.contentBuilders.get(ContentType.PRODUCT_PACKAGE);
        if (productPackageBuilder == null)
            throw new IllegalStateException("Attempt at creating a product package when a product package builder is not available");
        else
            return productPackageBuilder.buildProductPackageFromDTO(dto);
    }

    /**
     * Builds a Sale from an input sale dto.
     *
     * @param dto the input sale dto.
     * @throws IllegalStateException if a sale builder is not available for this director instance.
     * @return a new Sale.
     */
    public Sale getSaleFromDto(InputSaleDTO dto) {
        SaleBuilder saleBuilder = (SaleBuilder) this.contentBuilders.get(ContentType.SALE);
        if (saleBuilder == null)
            throw new IllegalStateException("Attempt at creating a sale when a sale builder is not available");
        else
            return saleBuilder.buildProductOnSaleFromDTO(dto);
    }

    /**
     * Builds a TransformationProcess from an input transformation process dto.
     *
     * @param dto the transformation process dto.
     * @throws IllegalStateException if a transformation process builder is not available for this director instance.
     * @return a new TransformationProcess.
     */
    public TransformationProcess getTransformationProcessFromDto(InputTransformationProcessDTO dto) {
        TransformationProcessBuilder transformationProcessBuilder = (TransformationProcessBuilder) this.contentBuilders.get(ContentType.TRANSFORMATION_PROCESS);
        if (transformationProcessBuilder == null)
            throw new IllegalStateException("Attempt at creating a sale when a sale builder is not available");
        else
            return transformationProcessBuilder.buildTransformationProcessFromDTO(dto);
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
