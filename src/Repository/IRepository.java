package Repository;

import Domain.HealthData;

import java.util.List;

public interface IRepository<T extends HealthData> {
    List<T> getAll();
    public T findByDate(String date);
    void add(T entity);
    void update(T entity);
    void delete(String date);
}