package Repository;

import Domain.Entity;
import Domain.IEntityFactory;

import java.io.*;
import java.util.List;

public class BinaryFileRepository<T extends Entity> extends MemoryRepository<T>{
    private String fileName;
    IEntityFactory<T> entityFactory;

    public BinaryFileRepository(String fileName, IEntityFactory<T> entityFactory) {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        loadFile();
    }

    private void loadFile(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)); //citeste tot fisierul de odata
            entities = (List<T>) ois.readObject(); //convertim obiectul citit la tipul atributului entities adica List<T>
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(entities);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(T entity) {
        super.add(entity);
        writeFile();
    }
    @Override
    public void update(T entity) {
        super.update(entity);
        writeFile();
    }

    @Override
    public void delete(int id) {
        super.delete(id);
        writeFile();
    }
}
