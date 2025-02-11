package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.TransformationProcessDTO;
import it.unicam.cs.ids.api.dto.ValidationRequestDTO;
import it.unicam.cs.ids.api.handlers.TransformationProcessHandler;
import it.unicam.cs.ids.api.handlers.ValidationRequestHandler;
import it.unicam.cs.ids.api.model.builder.ValidationRequestBuilder;
import it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder.TransformationProcessBuilder;
import it.unicam.cs.ids.api.model.contents.ValidationRequest;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;


import java.util.List;

public class TransformationProcessController {

    private TransformationProcessHandler transformationProcessHandler;

    private ValidationRequestHandler validationRequestHandler;

    private TransformationProcessBuilder transformationProcessBuilder;

    private ValidationRequestBuilder validationRequestBuilder;

    public TransformationProcessController(TransformationProcessHandler transformationProcessHandler,
                                ValidationRequestHandler validationRequestHandler) {
        this.transformationProcessHandler = transformationProcessHandler;
        this.validationRequestHandler = validationRequestHandler;
        this.transformationProcessBuilder = new TransformationProcessBuilder();
        this.validationRequestBuilder = new ValidationRequestBuilder();
    }

    // CREATION

    public TransformationProcess addNewTransformationProcess(TransformationProcessDTO transformationProcessDTO) {
        TransformationProcess transformationProcess = this.transformationProcessHandler.saveTransformationProcess(
                this.transformationProcessBuilder.buildTransformationProcessFromDTO(transformationProcessDTO));
        this.validationRequestHandler.saveValidationRequest(this.generateValidationRequestFrom(transformationProcessDTO));
        return transformationProcess;
    }

    // READ

    public TransformationProcess getTransformationProcessById(int id) {
        return this.transformationProcessHandler.findTransformationProcessById(id);
    }

    public List<TransformationProcess> getTransformationProcesses() {
        return this.transformationProcessHandler.findAllTransformationProcesses();
    }

    // UTILITIES

    private ValidationRequest generateValidationRequestFrom(TransformationProcessDTO transformationProcessDTO) {
        ValidationRequestDTO validationRequestDTO = new ValidationRequestDTO();
        validationRequestDTO.setSupplyChainPointId(transformationProcessDTO.getSupplyChainPointId());
        validationRequestDTO.setContentId(transformationProcessDTO.getId());
        validationRequestDTO.setContentType(transformationProcessDTO.getContentType());
        return this.validationRequestBuilder.buildValidationRequestFromDTO(validationRequestDTO);
    }

}
