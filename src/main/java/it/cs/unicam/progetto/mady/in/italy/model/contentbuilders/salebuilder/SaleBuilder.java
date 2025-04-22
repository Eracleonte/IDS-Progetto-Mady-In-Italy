package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.salebuilder;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputSaleDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.sale.Sale;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.stereotype.Component;

@Component
public class SaleBuilder implements ContentBuilder<Sale> {

    private Sale sale;

    public SaleBuilder() {
        this.sale = new Sale();
    }

    /**
     * Builds a Sale from a InputSaleDTO.
     *
     * @param inputSaleDTO the dto used to build a Sale.
     * @return a new Sale instance.
     */
    public Sale buildProductOnSaleFromDTO(InputSaleDTO inputSaleDTO,
                                          SupplyChainPoint supplyChainPoint) {
        this.reset();
        //this.setSupplyChainPointID(inputSaleDTO.supplyChainPointId());
        this.setSupplyChainPoint(supplyChainPoint);
        this.setProductId(inputSaleDTO.productId());
        this.setProductType(inputSaleDTO.productType().getValue());
        this.setName(inputSaleDTO.name());
        this.setDescription(inputSaleDTO.description());
        this.setAuthor(inputSaleDTO.author());
        this.setPrice(inputSaleDTO.price());
        this.setQuantity(inputSaleDTO.quantity());
        return sale;
    }

//    @Override
//    public void setSupplyChainPointID(int supplyChainPointID) {
//        this.sale.setSupplyChainPointId(supplyChainPointID);
//    }


    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        this.sale.setSupplyChainPoint(supplyChainPoint);
    }

    public void setProductId(int productId) {
        this.sale.setProductId(productId);
    }

    public void setProductType(String productType) {
        sale.setProductType(productType);
    }

    @Override
    public void setName(String name) {
        sale.setName(name);
    }

    @Override
    public void setDescription(String description) {
        sale.setDescription(description);
    }

    @Override
    public void setAuthor(String author) {
        sale.setAuthor(author);
    }

    public void setPrice(float price) {
        sale.setPrice(price);
    }

    public void setQuantity(int quantity) {
        sale.setQuantity(quantity);
    }

    @Override
    public Sale getResult() {
        return sale;
    }

    @Override
    public void reset() {
        this.sale = new Sale();
    }

    @Override
    public ContentType supports() {
        return ContentType.SALE;
    }

}
