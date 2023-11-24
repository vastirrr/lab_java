package Repository;

import Domain.Entity;

import java.util.Collection;

public interface IRepository <T extends Entity> extends Iterable<T> {
    void add(T entity) throws DuplicateEntityException, RepositoryException;
    void remove(int id);
    T findById(int id);
    Collection<T> getAll();
    void update(int id, String newMarca, String newModel);
}
