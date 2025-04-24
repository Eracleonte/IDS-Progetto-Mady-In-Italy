package it.cs.unicam.progetto.mady.in.italy.handlers;

import it.cs.unicam.progetto.mady.in.italy.model.contents.ContentType;
import it.cs.unicam.progetto.mady.in.italy.model.contents.transformationprocesses.TransformationProcess;
import it.cs.unicam.progetto.mady.in.italy.repos.content.TransformationProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Handler for Transformation Processes
 */
@Service
public class TransformationProcessHandler implements ContentHandler<TransformationProcess> {

    private final TransformationProcessRepository transformationProcessRepository;

    @Autowired
    public TransformationProcessHandler(TransformationProcessRepository transformationProcessRepository) {
        this.transformationProcessRepository = transformationProcessRepository;
    }

    @Override
    public int saveContent(TransformationProcess transformationProcess) {
        return this.transformationProcessRepository.save(transformationProcess).getId();
    }

    @Override
    public TransformationProcess findContentById(int id) {
        return this.transformationProcessRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find Transformation Process with id: " + id));
    }

    @Override
    public List<TransformationProcess> findAllContents() {
        return this.transformationProcessRepository.findAll();
    }

    @Override
    public String approveContent(int id, boolean approvalChoice) {
        TransformationProcess toApprove = checkIfTransformationProcessExists(id);
        if (!toApprove.isApproved()) {
            if (!approvalChoice) {
                this.transformationProcessRepository.reject(id);
                return "Transformation Process with Id " + id + " has been rejected";
            } else {
                this.transformationProcessRepository.approve(id);
                return "Transformation Process with Id " + id + " has been approved";
            }
        } else
            return "Transformation Process with Id " + id + " is already approved";
    }

    @Override
    public ContentType supports() {
        return ContentType.TRANSFORMATION_PROCESS;
    }

    // UTILITY

    /**
     * Checks if a Transformation Process with the given ID exists.
     *
     * @param id the supposed ID of a Transformation Process.
     * @throws NoSuchElementException if there's not a Transformation Process for the given ID.
     * @return the Transformation Process.
     */
    private TransformationProcess checkIfTransformationProcessExists(int id) {
        TransformationProcess transformationProcess = this.findContentById(id);
        if (transformationProcess == null)
            throw new NoSuchElementException("Cannot find Transformation Process with id: " + id);
        else
            return transformationProcess;
    }

}
