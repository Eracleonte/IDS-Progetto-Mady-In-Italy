package it.unicam.cs.ids.api.model.contents.products.singles;

import it.unicam.cs.ids.api.dto.output.OutputTransformedProductDTO;
import it.unicam.cs.ids.api.model.contents.ContentType;

public class TransformedProduct extends SingleProduct {

    private int transformationProcessId;

    public TransformedProduct() {
        super(ContentType.TRANSFORMED_PRODUCT);
    }

    public int getTransformationProcessId() {
        return transformationProcessId;
    }

    public void setTransformationProcessId(int transformationProcessId) {
        this.transformationProcessId = transformationProcessId;
    }

    public OutputTransformedProductDTO getOutputTransformedProductDTO() {
        return new OutputTransformedProductDTO(this.getContentId(),
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
