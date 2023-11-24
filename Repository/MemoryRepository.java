package Repository;

import Domain.Car;
import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository <T extends Entity> implements IRepository<T>{
    List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateEntityException, RepositoryException {
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
    public void update(int id, String newMarca, String newModel){
        T carToUpdate = findById(id);

        // Update the car's properties
        if (newMarca != null && !newMarca.isEmpty()) {
            // Assuming that setMarca method exists in your Car class
            ((Car) carToUpdate).setMarca(newMarca);
        }

        if (newModel != null && !newModel.isEmpty()) {
            // Assuming that setModel method exists in your Car class
            ((Car) carToUpdate).setModel(newModel);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return  new ArrayList<T>(entities).iterator();
    }
}
