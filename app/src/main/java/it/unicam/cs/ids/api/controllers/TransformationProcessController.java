package it.unicam.cs.ids.api.controllers;

import it.unicam.cs.ids.api.dto.input.InputTransformationProcessDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformationProcessDTO;
import it.unicam.cs.ids.api.handlers.TransformationProcessHandler;

import java.util.List;

public class TransformationProcessController {

    private TransformationProcessHandler transformationProcessHandler;

    public TransformationProcessController(TransformationProcessHandler transformationProcessHandler) {
        this.transformationProcessHandler = transformationProcessHandler;
    }

    /**

    // CREATION

    public int addNewTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO) {
        return this.transformationProcessHandler.saveTransformationProcess(inputTransformationProcessDTO);
    }

    // READ

    public OutputTransformationProcessDTO getTransformationProcessById(int id) {
        return this.transformationProcessHandler.findTransformationProcessById(id);
    }

    public List<OutputTransformationProcessDTO> getTransformationProcesses() {
        return this.transformationProcessHandler.findAllTransformationProcess();
    }

    public String approveTransformationProcess(int id, boolean approvalChoice) {
        return this.transformationProcessHandler.approveTransformationProcess(id, approvalChoice);
    }

     */

}
