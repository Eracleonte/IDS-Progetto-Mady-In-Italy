package it.unicam.cs.ids.api.model.builder.contentbuilders.salebuilder;

import it.unicam.cs.ids.api.dto.input.InputSaleDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.sale.Sale;

public class SaleBuilder implements ContentBuilder<Sale> {

    private Sale sale;

    public SaleBuilder() {
        this.sale = new Sale();
    }

    public Sale buildProductOnSaleFromDTO(InputSaleDTO inputSaleDTO) {
        this.reset();
        this.setSupplyChainPointID(inputSaleDTO.supplyChainPointId());
        this.setProductId(inputSaleDTO.productId());
        this.setProductType(inputSaleDTO.productType());
        this.setName(inputSaleDTO.name());
        this.setDescription(inputSaleDTO.description());
        this.setAuthor(inputSaleDTO.author());
        this.setPrice(inputSaleDTO.price());
        this.setQuantity(inputSaleDTO.quantity());
        return sale;
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
        this.sale.setSupplyChainPointId(supplyChainPointID);
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
