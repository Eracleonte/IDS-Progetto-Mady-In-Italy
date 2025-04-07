package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.input.InputTransformedProductDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;

public class TransformedProductBuilder implements ContentBuilder<TransformedProduct> {

    private TransformedProduct transformedProduct;

    public TransformedProductBuilder() {
        this.transformedProduct = new TransformedProduct();
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.transformedProduct.setSupplyChainPointId(supplyChainPointID);
    }

    /**
     * Builds a Transformed Product from a inputTransformedProductDTO.
     *
     * @param inputTransformedProductDTO the dto used to build a TransformedProduct.
     * @return a new TransformedProduct instance.
     */
    public TransformedProduct buildTransformedProductFromDTO(InputTransformedProductDTO inputTransformedProductDTO) {
        this.reset();
        this.setSupplyChainPointID(inputTransformedProductDTO.supplyChainPointId());
        this.setName(inputTransformedProductDTO.name());
        this.setDescription(inputTransformedProductDTO.description());
        this.setAuthor(inputTransformedProductDTO.author());
        this.setCertification(inputTransformedProductDTO.certification());
        this.setVariety(inputTransformedProductDTO.variety());
        this.setTransformationProcessId(inputTransformedProductDTO.transformationProcessId());
        return this.getResult();
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
