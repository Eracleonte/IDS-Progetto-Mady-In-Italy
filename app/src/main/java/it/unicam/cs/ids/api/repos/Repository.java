package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Identifiable;

import java.util.*;

public abstract class Repository<E extends Identifiable & Approvable> {

    private final Map<Integer,E> repository;

    private int nextId;

    public Repository() {
        this.repository = new HashMap<>();
        this.nextId = 0;
    }

    public E save(E element) {
        this.repository.put(element.getId(), element);
        return element;
    }

    public Optional<E> findById(int id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<E> findAll() {
        return new ArrayList<>(repository.values());
    }

    public E approve(int id, boolean approvalChoice) {
        E element = repository.get(id);
        if (element != null) {
            if (!approvalChoice)
                this.repository.remove(id);
            else
                element.setApproved(true);
            return element;
        }
        throw new NoSuchElementException("Element not found");
    }

    public int getNextId() {
        return nextId++;
    }

}
