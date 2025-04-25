package it.unicam.cs.ids.api.model.contents.products.singles;

import it.unicam.cs.ids.api.dto.output.OutputRawProductDTO;

public class RawProduct extends SingleProduct {

    private String productionMethod;

    public RawProduct() {

    }

    public String getProductionMethod() {
        return productionMethod;
    }

    public void setProductionMethod(String productionMethod) {
        if (productionMethod == null || productionMethod.isEmpty())
            throw new IllegalArgumentException("ProductionMethod cannot be null or empty");
        this.productionMethod = productionMethod;
    }

    public OutputRawProductDTO getOutputDTO() {
        return new OutputRawProductDTO(this.getId(),
                this.getSupplyChainPointId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getVariety(),
                this.getProductionMethod()
        );
    }

}
