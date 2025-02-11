package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.TransformedProductDTO;
import it.unicam.cs.ids.api.model.contents.products.singles.TransformedProduct;

public class TransformedProductBuilder implements SingleProductBuilder {

    private TransformedProduct transformedProduct;

    public TransformedProductBuilder() {
        this.transformedProduct = new TransformedProduct();
    }

    @Override
    public void setContentID(int contentID) {
        this.transformedProduct.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.transformedProduct.setSupplyChainPointId(supplyChainPointID);
    }

    public TransformedProduct buildTransformedProductFromDTO(TransformedProductDTO transformedProductDTO) {
        this.reset();
        this.setSupplyChainPointID(transformedProductDTO.getId());
        this.setName(transformedProductDTO.getName());
        this.setDescription(transformedProductDTO.getDescription());
        this.setAuthor(transformedProductDTO.getAuthor());
        this.setCertification(transformedProductDTO.getCertification());
        this.setVariety(transformedProductDTO.getVariety());
        this.setTransformationProcessId(transformedProductDTO.getTransformationProcessID());
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

    @Override
    public void setCertification(String certification) {
        this.transformedProduct.setCertification(certification);
    }

    @Override
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

}
