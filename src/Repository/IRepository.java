package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository<T extends Entity> {
    List<T> getAll();
    T findById(int id);
    void add(T entity);
    void update(T entity);
    void delete(int id);
}
