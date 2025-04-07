package it.unicam.cs.ids.api.handlers;

import it.unicam.cs.ids.api.model.contents.ContentType;
import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.content.TransformationProcessRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TransformationProcessHandler implements ContentHandler<TransformationProcess> {

    private final TransformationProcessRepository transformationProcessRepository;

    public TransformationProcessHandler(TransformationProcessRepository transformationProcessRepository) {
        if (transformationProcessRepository == null)
            throw new NullPointerException("transformationProcessRepository is null");
        this.transformationProcessRepository = transformationProcessRepository;
    }

    @Override
    public int saveContent(TransformationProcess transformationProcess) {
        transformationProcess.setContentId(this.transformationProcessRepository.getNextId());
        return this.transformationProcessRepository.save(transformationProcess).getId();
    }

    @Override
    public TransformationProcess findContentById(int id) {
        return this.transformationProcessRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find raw product with id: " + id));
    }

    @Override
    public List<TransformationProcess> findAllContents() {
        return this.transformationProcessRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        boolean result = this.transformationProcessRepository.approve(id, approvalChoice);
        if (!result)
            return "Transformation Process with Id " + id + " has been rejected";
        else
            return "Transformation Process with Id " + id + " has been approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMATION_PROCESS;
    }

}
