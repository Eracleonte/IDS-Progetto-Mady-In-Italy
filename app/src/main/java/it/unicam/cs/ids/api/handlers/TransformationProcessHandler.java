package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.dto.input.InputTransformationProcessDTO;
import it.unicam.cs.ids.api.dto.output.OutputTransformationProcessDTO;
import it.unicam.cs.ids.api.model.builder.contentbuilders.transformationprocessbuilder.TransformationProcessBuilder;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformationProcessHandler {

    private TransformationProcessBuilder transformationProcessBuilder ;

    private TransformationProcessRepository transformationProcessRepository;

    public TransformationProcessHandler(TransformationProcessRepository transformationProcessRepository) {
        this.transformationProcessBuilder = new TransformationProcessBuilder();
        this.transformationProcessRepository = transformationProcessRepository;
    }

    // CREATE

    public int saveTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO) {
        TransformationProcess transformationProcess = this.transformationProcessRepository
                .save(this.transformationProcessBuilder.buildTransformationProcessFromDTO(inputTransformationProcessDTO));
        return transformationProcess.getId();
    }

    // READ

    public OutputTransformationProcessDTO findTransformationProcessById(Integer id) {
        TransformationProcess transformationProcess = this.transformationProcessRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
        return transformationProcess.getOutputDTO();
    }

    public List<OutputTransformationProcessDTO> findAllTransformationProcess() {
        return this.transformationProcessRepository.findAll()
                .stream()
                .map(TransformationProcess::getOutputDTO)
                .toList();
    }

    public String approveTransformationProcess(int id, boolean approvalChoice) {
            boolean result = this.transformationProcessRepository.approve(id, approvalChoice);
            if (!result)
                return "Transformation Process with Id " + id + " has been rejected";
            else
                return "Transformation Process with Id " + id + " has been approved";
    }

}
