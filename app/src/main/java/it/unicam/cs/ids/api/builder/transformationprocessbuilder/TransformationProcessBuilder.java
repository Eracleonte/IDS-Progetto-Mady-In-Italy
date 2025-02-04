package it.unicam.cs.ids.api.builder.transformationprocessbuilder;

import it.unicam.cs.ids.api.builder.ContentBuilder;
import it.unicam.cs.ids.api.contents.Content;
import it.unicam.cs.ids.api.contents.transformationprocesses.TransformationProcess;

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
    public Content getResult() {
        return transformationProcess;
    }

    @Override
    public void reset() {
        this.transformationProcess = new TransformationProcess();
    }

}
