package Repository;

import Domain.Entity;
import Domain.IEntityFactory;

import java.io.*;

public class TextFileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;
    private IEntityFactory<T> entityFactory;

    public TextFileRepository(String fileName, IEntityFactory<T> entityFactory) throws FileNotFoundException, DuplicateEntityException{
        this.fileName = fileName;
        this.entityFactory = entityFactory;

        readFromFile();
    }

    private void readFromFile() throws FileNotFoundException, DuplicateEntityException{
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T entity = entityFactory.createEntity(line);
                add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    private void writeToFile() {
        Iterable<T> list = super.getAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for (T d: list)
                bw.write(entityFactory.toFileString(d));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(T entity) throws DuplicateEntityException {
        super.add(entity);
        this.writeToFile();
    }
    @Override
    public void update(T entity)  {
        super.update(entity);
        this.writeToFile();
    }
    @Override
    public void remove(int id) {
        super.remove(id);
        this.writeToFile();
    }
}
