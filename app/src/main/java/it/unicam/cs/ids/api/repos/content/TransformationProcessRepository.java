package it.unicam.cs.ids.api.repos.content;

import it.unicam.cs.ids.api.model.contents.transformationprocesses.TransformationProcess;
import it.unicam.cs.ids.api.repos.Repository;

public class TransformationProcessRepository extends Repository<TransformationProcess> {

    private static TransformationProcessRepository instance;

    private TransformationProcessRepository() {
        super();
    }

    public static TransformationProcessRepository getInstance() {
        if (instance == null) instance = new TransformationProcessRepository();
        return instance;
    }

}
