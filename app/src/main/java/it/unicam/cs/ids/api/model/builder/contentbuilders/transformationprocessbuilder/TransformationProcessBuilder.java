package it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder;

import it.unicam.cs.ids.api.dto.input.InputTransformationProcessDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.ContentBuilder;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;

public class TransformationProcessBuilder implements ContentBuilder<TransformationProcess> {

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

    public TransformationProcess buildTransformationProcessFromDTO(InputTransformationProcessDTO inputTransformationProcessDTO) {
        this.reset();
        this.setSupplyChainPointID(inputTransformationProcessDTO.supplyChainPointId());
        this.setName(inputTransformationProcessDTO.name());
        this.setDescription(inputTransformationProcessDTO.description());
        this.setAuthor(inputTransformationProcessDTO.author());
        this.setCertification(inputTransformationProcessDTO.certification());
        this.setTransformationPhases(inputTransformationProcessDTO.transformationPhases());
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
