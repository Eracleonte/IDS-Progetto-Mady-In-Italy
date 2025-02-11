package it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder;

import it.unicam.cs.ids.api.dto.TransformationProcessDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;

public class TransformationProcessBuilder implements ContentBuilder {

    private TransformationProcess transformationProcess;

    public TransformationProcessBuilder() {
        this.transformationProcess =  new TransformationProcess();
    }

    @Override
    public void setContentID(int contentID) {
        this.transformationProcess.setContentId(contentID);
    }

    @Override
    public void setSupplyChainPointID(int supplyChainPointID) {
       this.transformationProcess.setSupplyChainPointId(supplyChainPointID);
    }

    public TransformationProcess buildTransformationProcessFromDTO(TransformationProcessDTO transformationProcessDTO) {
        this.reset();
        this.setSupplyChainPointID(transformationProcessDTO.getId());
        this.setName(transformationProcessDTO.getName());
        this.setDescription(transformationProcessDTO.getDescription());
        this.setAuthor(transformationProcessDTO.getAuthor());
        this.setCertification(transformationProcessDTO.getCertification());
        this.setTransformationPhases(transformationProcessDTO.getTransformationPhases());
        return this.getResult();
    }

    @Override
    public void setName(String name) {
        this.transformationProcess.setName(name);
    }

    @Override
    public void setDescription(String description) {
        this.transformationProcess.setDescription(description);
    }

    @Override
    public void setAuthor(String author) {
        this.transformationProcess.setAuthor(author);
    }

    public void setCertification(String certification) {
        this.transformationProcess.setCertification(certification);
    }

    public void setTransformationPhases (String transformationPhases) {
        this.transformationProcess.setTransformationPhases(transformationPhases);
    }

    @Override
    public TransformationProcess getResult() {
        return transformationProcess;
    }

    @Override
    public void reset() {
        this.transformationProcess = new TransformationProcess();
    }

}
