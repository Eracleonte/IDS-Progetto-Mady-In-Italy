package it.unicam.cs.ids.api.model.contents.products.singles;

import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;

public class TransformedProduct extends SingleProduct {

    private int transformationProcessId;

    public TransformedProduct() {
    }

    public int getTransformationProcessId() {
        return transformationProcessId;
    }

    public void setTransformationProcessId(int transformationProcessId) {
        this.transformationProcessId = transformationProcessId;
    }

    public OutputTransformedProductDTO getOutputDTO() {
        return new OutputTransformedProductDTO(this.getId(),
                this.getSupplyChainPointId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getVariety(),
                this.getTransformationProcessId()
        );
    }

}
