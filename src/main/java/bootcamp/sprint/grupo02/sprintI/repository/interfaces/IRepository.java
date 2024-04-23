package bootcamp.sprint.grupo02.sprintI.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    void add(T entity);
    void remove(T entity);
}
