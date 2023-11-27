package Repository;

import Domain.HealthData;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository<T extends HealthData> implements IRepository<T> {
    List<T>  entities = new ArrayList<>();
    @Override
    public List<T> getAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public T findByDate(String date) {
        for (T entity: entities){
            if(entity.getDate().equals(date)){
                return entity;
            }
        }
        return null;
    }

    @Override
    public void add(T entity) {
        if(entity == null)
            throw new IllegalArgumentException("HealthData can't be null!!");
        if(findByDate(entity.getDate())!=null)
            throw new IllegalArgumentException("HealthData already exist!");
        entities.add(entity);
    }

    @Override
    public void update(T entity) {
        if(entity == null)
            throw new IllegalArgumentException("HealthData can't be null!");
        if(findByDate(entity.getDate())==null)
            throw new IllegalArgumentException("HealthData doesn't exist!");
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getDate().equals(entity.date)){
                entities.set(i,entity);
            }
        }

    }

    @Override
    public void delete(String date) {
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getDate().equals(date)){
                entities.remove(i);
            }
        }
    }
}
