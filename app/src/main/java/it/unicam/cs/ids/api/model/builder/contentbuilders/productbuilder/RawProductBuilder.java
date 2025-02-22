package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.input.InputRawProductDTO;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;

public class RawProductBuilder implements SingleProductBuilder {

    private RawProduct rawProduct;

    public RawProductBuilder() {
        this.rawProduct = new RawProduct();
    }

    /**
     * Builds a Raw Product from a InputRawProductDTO
     * @param inputRawProductDTO
     * @return
     */
    public RawProduct buildRawProductFromDTO(InputRawProductDTO inputRawProductDTO) {
        this.reset();
        this.setSupplyChainPointID(inputRawProductDTO.supplyChainPointId());
        this.setName(inputRawProductDTO.name());
        this.setDescription(inputRawProductDTO.description());
        this.setAuthor(inputRawProductDTO.author());
        this.setCertification(inputRawProductDTO.certification());
        this.setVariety(inputRawProductDTO.variety());
        this.setProductionMethod(inputRawProductDTO.productionMethod());
        return this.getResult();
    }

    @Override
    public void setContentID(int contentID) {
        this.rawProduct.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.rawProduct.setSupplyChainPointId(supplyChainPointID);
    }

    @Override
    public void setName(String name) {
        this.rawProduct.setName(name);
    }

    @Override
    public void setDescription(String description) {
        this.rawProduct.setDescription(description);
    }

    @Override
    public void setAuthor(String author) {
        this.rawProduct.setAuthor(author);
    }

    @Override
    public void setCertification(String certification) {
        this.rawProduct.setCertification(certification);
    }

    @Override
    public void setVariety(String variety) {
        this.rawProduct.setVariety(variety);
    }

    public void setProductionMethod(String productionMethod) {
        this.rawProduct.setProductionMethod(productionMethod);
    }

    @Override
    public RawProduct getResult() {
        return this.rawProduct;
    }

    @Override
    public void reset() {
        this.rawProduct = new RawProduct();
    }

}
