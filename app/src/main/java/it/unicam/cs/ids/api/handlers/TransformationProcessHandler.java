package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformationProcessHandler {

    private TransformationProcessRepository transformationProcessRepository;

    public TransformationProcessHandler(TransformationProcessRepository transformationProcessReposity) {
        this.transformationProcessRepository = transformationProcessReposity;
    }

    public TransformationProcess saveTransformationProcess(TransformationProcess transformationProcess) {
        if (transformationProcess == null)
            throw new NullPointerException("Cannot save a null transformation process");
        return this.transformationProcessRepository.save(transformationProcess);
    }

    public TransformationProcess findTransformationProcessById(Integer id) {
        return transformationProcessRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find transformation process with id: " + id));
    }

    public List<TransformationProcess> findAllTransformationProcesses() {
        return this.transformationProcessRepository.findAll();
    }

}
