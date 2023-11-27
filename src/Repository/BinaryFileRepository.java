package Repository;

import Domain.HealthData;
import Domain.IEntityFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository<T extends HealthData> extends MemoryRepository<T> {
    private String fileName;
    IEntityFactory<T> entityFactory;

    public BinaryFileRepository(String fileName, IEntityFactory<T> entityFactory) {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        loadFile();
    }

    private void loadFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            entities = (List<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            entities = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions appropriately
            // You might want to log or throw an exception
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
    public void delete(String date) {
        super.delete(date);
        writeFile();
    }
}
