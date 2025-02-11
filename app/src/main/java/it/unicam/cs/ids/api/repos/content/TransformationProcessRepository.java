package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.Repository;

import java.util.*;

public class TransformationProcessRepository implements Repository<TransformationProcess, Integer> {

    private Map<Integer, TransformationProcess> transformationProcessesMap;

    private int nextTransformationProcessId;

    public TransformationProcessRepository() {
        this.transformationProcessesMap = new HashMap<>();
        this.nextTransformationProcessId = 1;
    }

    @Override
    public TransformationProcess save(TransformationProcess element) {
        element.setContentId(nextTransformationProcessId);
        this.transformationProcessesMap.put(element.getContentId(), element);
        this.nextTransformationProcessId++;
        return element;
    }

    @Override
    public Optional<TransformationProcess> findById(Integer id) {
         return Optional.ofNullable(transformationProcessesMap.get(id));
    }

    @Override
    public List<TransformationProcess> findAll() {
        return new ArrayList<>(transformationProcessesMap.values());
    }

}
