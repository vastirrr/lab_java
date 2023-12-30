package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository   <T extends Entity> implements IRepository<T>{
    List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException {
        if (entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if (findById(entity.getID()) != null)
            throw new DuplicateEntityException("Entity with the same id already exists!");
        entities.add(entity);
    }

    @Override
    public void remove(int id) {
        for (T entity : entities)
        {
            if (entity.getID() == id)
            {
                entities.remove(entity);
                return;
            }
        }
    }

    @Override
    public T findById(int id) {
        for (T entity : entities)
        {
            if (entity.getID() == id)
            {
                return entity;
            }
        }
        return null;
    }



    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public void update(T entity){
        for (int i = 0; i <entities.size(); i++)
        {
            if (entities.get(i).getID() == entity.getID())
            {
                entities.set(i, entity);
                return;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return  new ArrayList<T>(entities).iterator();
    }
}
