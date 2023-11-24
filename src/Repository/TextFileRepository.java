package Repository;

import Domain.Car;
import Domain.Entity;
import Domain.IEntityFactory;

import java.io.*;

public class TextFileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;
    IEntityFactory<T> entityFactory;

    public TextFileRepository(String fileName, IEntityFactory<T> entityFactory) {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        loadFile();
    }

    private void loadFile(){

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T entity = entityFactory.createFromString(line);
                add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

//        try {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line = br.readLine();
//            while (line != null && !line.isEmpty()){
//                //avem nevoie de clasa ajutatoare pt ca pt car si inchiriere e diferit de aici incolo
//               T entity = entityFactory.createFromString(line);
//               add(entity);
//               line = br.readLine(); // mai citim o linie
//            }
//            br.close(); // inchidem fisierul
//        }catch (FileNotFoundException e){
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void writeFile(){
        try {
            FileWriter fw = new FileWriter(fileName);
            for(T entity: entities){
                fw.write(entityFactory.createFromEntity(entity));
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
    public void delete(int id) {
        super.delete(id);
        writeFile();
    }
}
