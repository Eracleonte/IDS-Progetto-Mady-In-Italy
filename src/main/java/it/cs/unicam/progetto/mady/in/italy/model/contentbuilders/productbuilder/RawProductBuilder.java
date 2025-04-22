package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.productbuilder;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputRawProductDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles.RawProduct;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.stereotype.Component;

@Component
public class RawProductBuilder implements ContentBuilder<RawProduct> {

    private RawProduct rawProduct;

    public RawProductBuilder() {
        this.rawProduct = new RawProduct();
    }

    /**
     * Builds a Raw Product from a InputRawProductDTO.
     *
     * @param inputRawProductDTO the dto used to build a RawProduct.
     * @return a new RawProduct instance.
     */
    public RawProduct buildRawProductFromDTO(InputRawProductDTO inputRawProductDTO,
                                             SupplyChainPoint supplyChainPoint) {
        this.reset();
        //this.setSupplyChainPointID(inputRawProductDTO.supplyChainPointId());
        this.rawProduct.setSupplyChainPoint(supplyChainPoint);
        this.setName(inputRawProductDTO.name());
        this.setDescription(inputRawProductDTO.description());
        this.setAuthor(inputRawProductDTO.author());
        this.setCertification(inputRawProductDTO.certification());
        this.setVariety(inputRawProductDTO.variety());
        this.setProductionMethod(inputRawProductDTO.productionMethod());
        return this.getResult();
    }

    //@Override
    //public void setSupplyChainPointID(int supplyChainPointID) {
    //    this.rawProduct.setSupplyChainPointId(supplyChainPointID);
    //}


    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        this.rawProduct.setSupplyChainPoint(supplyChainPoint);
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

    public void setCertification(String certification) {
        this.rawProduct.setCertification(certification);
    }

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

    @Override
    public ContentType supports() {
        return ContentType.RAW_PRODUCT;
    }

}
