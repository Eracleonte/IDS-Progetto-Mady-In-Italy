package it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles;

import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputTransformedProductDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class TransformedProduct extends Product {

    private String certification;

    private String variety;

    private int transformationProcessId;

    public TransformedProduct() {
    }

    public String getCertification() {
        return certification;
    }

    public String getVariety() {
        return variety;
    }

    public int getTransformationProcessId() {
        return transformationProcessId;
    }

    public void setCertification(String certification) {
        if (certification == null || certification.isEmpty())
            throw new IllegalArgumentException("Certification cannot be null or empty");
        this.certification = certification;
    }

    public void setVariety(String variety) {
        if (variety == null || variety.isEmpty())
            throw new IllegalArgumentException("Variety cannot be null or empty");
        this.variety = variety;
    }

    public void setTransformationProcessId(int transformationProcessId) {
        if (transformationProcessId < 0)
            throw new IllegalArgumentException("TransformationProcessId cannot be negative");
        this.transformationProcessId = transformationProcessId;
    }

    @Transient
    @Override
    public OutputTransformedProductDTO getOutputDTO() {
        return new OutputTransformedProductDTO(this.getId(),
                this.getSupplyChainPoint().getId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getVariety(),
                this.getTransformationProcessId()
        );
    }

}
