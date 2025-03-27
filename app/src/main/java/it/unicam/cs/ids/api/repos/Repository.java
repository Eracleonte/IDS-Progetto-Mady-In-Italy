package it.unicam.cs.ids.api.repos;

import it.unicam.cs.ids.api.abstractions.Approvable;
import it.unicam.cs.ids.api.abstractions.Identifiable;

import java.util.*;

public abstract class Repository<E extends Identifiable & Approvable> {

    private final Map<Integer,E> repository;

    private int nextId;

    public Repository() {
        this.repository = new HashMap<>();
        this.nextId = 1;
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

    /**
     * Approves an approvable content
     *
     * @param id the id of the content to approve
     * @param approvalChoice the approval choice [true for approval, false for rejection]
     * @throws NoSuchElementException if there is no content with the specified ìd
     * @return true if the content has been approved,
     *         otherwise false
     */
    public boolean approve(int id, boolean approvalChoice) {
        E element = repository.get(id);
        if (element != null) {
            if (!approvalChoice) {
                this.repository.remove(id);
                return false;
            } else {
                element.setApproved(true);
                return true;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    public int getNextId() {
        return nextId++;
    }

}
