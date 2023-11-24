package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository<T extends Entity> implements IRepository<T> {
    List<T> entities = new ArrayList<>(); // altfer ar fi null .. creapa la add
    @Override
    public List<T> getAll() {
        return new ArrayList<>(entities); // face o copie in spate
    }

    @Override
    public T findById(int id) {
        for (T entity: entities){
            if(entity.getId() == id){
                return entity;
            }
        }
        return null;
    }

    @Override
    public void add(T entity) {
        if(entity == null)
            throw new IllegalArgumentException("Entity can't be null!!");
        if(findById(entity.getId()) != null){
            throw new IllegalArgumentException("Entity already exist!!");
        }
        entities.add(entity);
    }

    @Override
    public void update(T entity) {
        if(entity == null)
            throw new IllegalArgumentException("Entity can't be null!!");
        if(findById(entity.getId()) == null){
            throw new IllegalArgumentException("Entity doesn't exist!!");
        }
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getId() == entity.getId()){ // entities.get(i) <=> entities[i]
                entities.set(i, entity);
            }
        }
    }

    @Override
    public void delete(int id) {
        if(findById(id) == null){
            throw new IllegalArgumentException("Entity doesn't exist!!");
        }
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getId() == id){
                entities.remove(i); // stergem elementul ATENTIE: punem i NU id!!!!
            }
        }
    }
}
