package Repository;

import Domain.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryRepository <T extends Entity> extends MemoryRepository<T> {
    private String fileName;

    // FIXME: Constructor should be BinaryRepository, not the method name
    public BinaryRepository(String fileName) {
        super(); // FIXME: The super() should be called without any argument
        this.fileName = fileName;
        loadFile();
    }

    @Override
    public void add(T entity) throws DuplicateEntityException {
        super.add(entity);
        // Save file only if super.add() doesn't throw an exception
        saveFile();
    }

    private void loadFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            entities = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File doesn't exist, initialize entities as an empty list
            entities = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions appropriately
            // You might want to log or throw an exception
        }
    }


    private void saveFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
