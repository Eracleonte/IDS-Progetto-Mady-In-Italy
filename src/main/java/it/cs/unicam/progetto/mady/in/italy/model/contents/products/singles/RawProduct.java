package it.cs.unicam.progetto.mady.in.italy.model.contents.products.singles;

import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputRawProductDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contents.products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class RawProduct extends Product {

    private String certification;

    private String variety;

    private String productionMethod;

    public RawProduct() {

    }

    public String getCertification() {
        return certification;
    }

    public String getVariety() {
        return variety;
    }

    public String getProductionMethod() {
        return productionMethod;
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

    public void setProductionMethod(String productionMethod) {
        if (productionMethod == null || productionMethod.isEmpty())
            throw new IllegalArgumentException("ProductionMethod cannot be null or empty");
        this.productionMethod = productionMethod;
    }

    @Transient
    @Override
    public OutputRawProductDTO getOutputDTO() {
        return new OutputRawProductDTO(this.getId(),
                this.getSupplyChainPoint().getId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getVariety(),
                this.getProductionMethod()
        );
    }

}
