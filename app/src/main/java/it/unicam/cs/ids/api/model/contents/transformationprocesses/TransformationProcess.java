package it.unicam.cs.ids.api.model.contents.transformationprocesses;

import it.unicam.cs.ids.api.dto.output.OutputTransformationProcessDTO;
import it.unicam.cs.ids.api.model.contents.Content;
import it.unicam.cs.ids.api.model.contents.ContentType;

/**
 * Represents a Transformation Process
 */
public class TransformationProcess implements Content {

    private int id;

    private int supplyChainPointId;

    private String CONTENT_TYPE;

    private String name;

    private String description;

    private String author;

    private boolean published;

    private String certification;

    private String transformationPhases;

    public TransformationProcess() {
        this.CONTENT_TYPE = ContentType.TRANSFORMATION_PROCESS.getValue();
    }

    @Override
    public int getContentId() {
        return this.id;
    }

    @Override
    public int getSupplyChainPointId() {
        return this.supplyChainPointId;
    }

    @Override
    public String getContentType() {
        return this.CONTENT_TYPE;
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
    public boolean isPublished() {
        return this.published;
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

    @Override
    public void setSupplyChainPointId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("SupplyChainPointId id must be a positive integer");
        this.supplyChainPointId = id;
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
    public void publish() {
        this.published = true;
    }

    @Override
    public void unpublish() {
        this.published = false;
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

    public OutputTransformationProcessDTO getOutputTransformationProcessDTO() {
        return new OutputTransformationProcessDTO(this.getContentId(),
                this.getSupplyChainPointId(),
                this.getName(),
                this.getDescription(),
                this.getAuthor(),
                this.getCertification(),
                this.getTransformationPhases()
        );
    }

}
