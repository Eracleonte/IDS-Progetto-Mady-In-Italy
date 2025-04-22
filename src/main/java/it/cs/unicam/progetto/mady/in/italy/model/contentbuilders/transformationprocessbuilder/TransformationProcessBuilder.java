package it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.transformationprocessbuilder;

import it.cs.unicam.progetto.mady.in.italy.dto.input.InputTransformationProcessDTO;
import it.cs.unicam.progetto.mady.in.italy.model.contentbuilders.ContentBuilder;
import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.model.supplychain.SupplyChainPoint;
import org.springframework.stereotype.Component;

@Component
public class TransformationProcessBuilder implements ContentBuilder<TransformationProcess> {

    private TransformationProcess transformationProcess;

    public TransformationProcessBuilder() {
        this.transformationProcess =  new TransformationProcess();
    }

    /**
     * Builds a Transformation Process from a InputTransformationProcessDTO.
     *
     * @param inputTransformationProcessDTO the dto used to build a TransformationProcess.
     * @return a new TransformationProcess instance.
     */
    public TransformationProcess buildTransformationProcessFromDTO(InputTransformationProcessDTO inputTransformationProcessDTO,
                                                                   SupplyChainPoint supplyChainPoint) {
        this.reset();
        //this.setSupplyChainPointID(inputTransformationProcessDTO.supplyChainPointId());
        this.transformationProcess.setSupplyChainPoint(supplyChainPoint);
        this.setName(inputTransformationProcessDTO.name());
        this.setDescription(inputTransformationProcessDTO.description());
        this.setAuthor(inputTransformationProcessDTO.author());
        this.setCertification(inputTransformationProcessDTO.certification());
        this.setTransformationPhases(inputTransformationProcessDTO.transformationPhases());
        return this.getResult();
    }

//    @Override
//    public void setSupplyChainPointID(int supplyChainPointID) {
//        this.transformationProcess.setSupplyChainPointId(supplyChainPointID);
//    }


    @Override
    public void setSupplyChainPoint(SupplyChainPoint supplyChainPoint) {
        this.transformationProcess.setSupplyChainPoint(supplyChainPoint);
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

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMATION_PROCESS;
    }

}
