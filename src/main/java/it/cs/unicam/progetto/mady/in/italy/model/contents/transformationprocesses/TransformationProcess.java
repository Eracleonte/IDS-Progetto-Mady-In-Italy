package it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses;

import it.cs.unicam.progetto.mady.in.italy.model.contents.Content;
import it.cs.unicam.progetto.mady.in.italy.dto.output.OutputTransformationProcessDTO;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import jakarta.persistence.*;

/**
 * Represents a Transformation Process
 */
@Entity
public class TransformationProcess implements Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //private int supplyChainPointId;

    @ManyToOne
    @JoinColumn(name = "supplyChainPointId", referencedColumnName = "id", nullable = false)
    private SupplyChainPoint supplyChainPoint;

    private String name;

    private String description;

    private String author;

    private boolean approved;

    private String certification;

    private String transformationPhases;

    public TransformationProcess() {
    }

    @Override
    public int getId() {
        return id;
    }

    //@Override
    //public int getSupplyChainPointId() {
    //    return this.supplyChainPointId;
    //}


    @Override
    public SupplyChainPoint getSupplyChainPoint() {
        return supplyChainPoint;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public boolean isApproved() {
        return this.approved;
    }

    public String getCertification() {
        return certification;
    }

    public String getTransformationPhases() {
        return transformationPhases;
    }

    @Override
    public void setContentId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Content id must be a positive integer");
        this.id = id;
    }

    //@Override
    //public void setSupplyChainPointId(int id) {
    //    if (id < 0)
    //        throw new IllegalArgumentException("SupplyChainPointId id must be a positive integer");
    //    this.supplyChainPointId = id;
    //}


    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        if (supplyChainPoint == null)
            throw new IllegalArgumentException("SupplyChainPoint must not be null");
        this.supplyChainPoint = supplyChainPoint;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;
    }

    @Override
    public void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be null or empty");
        this.author = author;
    }

    @Override
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setCertification(String certification) {
        if (certification == null || certification.isEmpty())
            throw new IllegalArgumentException("Certification cannot be null or empty");
        this.certification = certification;
    }

    public void setTransformationPhases(String transformationPhases) {
        if (transformationPhases == null || transformationPhases.isEmpty())
            throw new IllegalArgumentException("TransformationPhase cannot be null or empty");
        this.transformationPhases = transformationPhases;
    }

    @Transient
    @Override
    public OutputTransformationProcessDTO getOutputDTO() {
        return new OutputTransformationProcessDTO(this.getId(),
                this.getSupplyChainPoint().getId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getTransformationPhases()
        );
    }

}
