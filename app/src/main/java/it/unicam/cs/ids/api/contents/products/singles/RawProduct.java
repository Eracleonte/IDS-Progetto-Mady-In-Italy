package it.unicam.cs.ids.api.contents.products.singles;

import it.unicam.cs.ids.api.contents.ContentType;

public class RawProduct extends SingleProduct {

    private String productionMethod;

    public RawProduct() {
        super(ContentType.RAW_PRODUCT);
    }

    public String getProductionMethod() {
        return productionMethod;
    }

    public void setProductionMethod(String productionMethod) {
        if (productionMethod == null || productionMethod.isEmpty())
            throw new IllegalArgumentException("ProductionMethod cannot be null or empty");
        this.productionMethod = productionMethod;
    }

}
