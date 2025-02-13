package it.unicam.cs.ids.api.model.contents.products.singles;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.model.contents.products.Product;

public abstract class SingleProduct extends Product {

    private String certification;

    private String variety;

    public SingleProduct(ContentType contentType) {
        super(contentType);
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        if (certification == null || certification.isEmpty())
            throw new IllegalArgumentException("Certification cannot be null or empty");
        this.certification = certification;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        if (variety == null || variety.isEmpty())
            throw new IllegalArgumentException("Variety cannot be null or empty");
        this.variety = variety;
    }

}
