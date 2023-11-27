package Repository;

import Domain.HealthData;
import Domain.IEntityFactory;

import java.io.*;

public class TextFileRepository<T extends HealthData> extends MemoryRepository<T> {
    private String fileName;
    IEntityFactory<T> healthDataFactory;

    public TextFileRepository(String fileName, IEntityFactory<T> healthDataFactory) {
        this.fileName = fileName;
        this.healthDataFactory = healthDataFactory;
        loadFile();
    }

    private void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T entity = healthDataFactory.createFromString(line);
                add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(){
        try{
            FileWriter fw = new FileWriter(fileName);
            for(T entity : entities){
                fw.write(healthDataFactory.createFromEntity(entity));
                fw.write("\n");
            }
            fw.close();
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