package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputTransformedProductDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.TransformedProduct;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.stereotype.Component;

/**
 * A ContentBuilder for TransformedProducts
 */
@Component
public class TransformedProductBuilder implements ContentBuilder<TransformedProduct> {

    private TransformedProduct transformedProduct;

    public TransformedProductBuilder() {
        this.transformedProduct = new TransformedProduct();
    }

    /**
     * Builds a Transformed Product from a inputTransformedProductDTO.
     *
     * @param inputTransformedProductDTO the dto used to build a TransformedProduct.
     * @return a new TransformedProduct instance.
     */
    public TransformedProduct buildTransformedProductFromDTO(InputTransformedProductDTO inputTransformedProductDTO,
                                                             SupplyChainPoint supplyChainPoint) {
        this.reset();
        this.setSupplyChainPoint(supplyChainPoint);
        this.setName(inputTransformedProductDTO.name());
        this.setDescription(inputTransformedProductDTO.description());
        this.setAuthor(inputTransformedProductDTO.author());
        this.setCertification(inputTransformedProductDTO.certification());
        this.setVariety(inputTransformedProductDTO.variety());
        this.setTransformationProcessId(inputTransformedProductDTO.transformationProcessId());
        return this.getResult();
    }

    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        this.transformedProduct.setSupplyChainPoint(supplyChainPoint);
    }

    @Override
    public void setName(String name) {
        this.transformedProduct.setName(name);
    }

    @Override
    public void setDescription(String description) {
        this.transformedProduct.setDescription(description);
    }

    @Override
    public void setAuthor(String author) {
        this.transformedProduct.setAuthor(author);
    }

    public void setCertification(String certification) {
        this.transformedProduct.setCertification(certification);
    }

    public void setVariety(String variety) {
        this.transformedProduct.setVariety(variety);
    }

    public void setTransformationProcessId(int transformationProcessId) {
        this.transformedProduct.setTransformationProcessId(transformationProcessId);
    }

    @Override
    public TransformedProduct getResult() {
        return this.transformedProduct;
    }

    @Override
    public void reset() {
        this.transformedProduct = new TransformedProduct();
    }

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMED_PRODUCT;
    }

}
