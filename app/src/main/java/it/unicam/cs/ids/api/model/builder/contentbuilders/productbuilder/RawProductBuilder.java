package it.unicam.cs.ids.api.model.builder.contentbuilders.productbuilder;

import it.unicam.cs.ids.api.dto.RawProductDTO;
import it.unicam.cs.ids.api.model.contents.products.singles.RawProduct;

public class RawProductBuilder implements SingleProductBuilder {

    private RawProduct rawProduct;

    public RawProductBuilder() {
        this.rawProduct = new RawProduct();
    }

    @Override
    public void setContentID(int contentID) {
        this.rawProduct.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.rawProduct.setSupplyChainPointId(supplyChainPointID);
    }

    /**
     * Builds a Raw Product from a RawProductDTO
     * @param rawProductDTO
     * @return
     */
    public RawProduct buildRawProductFromDTO(RawProductDTO rawProductDTO) {
        this.reset();
        this.setSupplyChainPointID(rawProductDTO.getId());
        this.setName(rawProductDTO.getName());
        this.setDescription(rawProductDTO.getDescription());
        this.setAuthor(rawProductDTO.getAuthor());
        this.setCertification(rawProductDTO.getCertification());
        this.setVariety(rawProductDTO.getVariety());
        this.setProductionMethod(rawProductDTO.getProductionMethod());
        return this.getResult();
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
