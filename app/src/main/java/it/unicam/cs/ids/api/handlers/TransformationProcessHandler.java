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

    private ValidationRequestHandler validationRequestHandler;

    public TransformationProcessHandler(TransformationProcessRepository transformationProcessRepository,
                                        ValidationRequestHandler validationRequestHandler) {
        this.transformationProcessBuilder = new TransformationProcessBuilder();
        this.transformationProcessRepository = transformationProcessRepository;
        this.validationRequestHandler = validationRequestHandler;
    }

    // CREATE

    public int saveTransformationProcess(InputTransformationProcessDTO inputTransformationProcessDTO) {
        TransformationProcess transformationProcess = this.transformationProcessRepository
                .save(this.transformationProcessBuilder.buildTransformationProcessFromDTO(inputTransformationProcessDTO));
        this.validationRequestHandler.saveValidationRequest(transformationProcess.getValidationRequest());
        return transformationProcess.getContentId();
    }

    // READ

    public OutputTransformationProcessDTO findTransformationProcessById(Integer id) {
        TransformationProcess transformationProcess = this.transformationProcessRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
        return transformationProcess.getOutputTransformationProcessDTO();
    }

    public List<OutputTransformationProcessDTO> findAllTransformationProcess() {
        return this.transformationProcessRepository.findAll()
                .stream()
                .map(TransformationProcess::getOutputTransformationProcessDTO)
                .toList();
    }

}
