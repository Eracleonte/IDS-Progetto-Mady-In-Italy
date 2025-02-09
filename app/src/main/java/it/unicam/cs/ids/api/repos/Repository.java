package it.unicam.cs.ids.api.repos;

import java.util.List;
import java.util.Optional;

public interface Repository<T,I> {

    T save(T element);

    Optional<T> findById(I id);

    List<T> findAll();

    // boolean deleteById(int id);

}
