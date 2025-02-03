package it.unicam.cs.ids.api.contents.products.singles;

import it.unicam.cs.ids.api.contents.ContentType;

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

}
