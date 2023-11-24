package Repository;

import Domain.Entity;
import Domain.IEntityFactory;

import java.io.*;
import java.util.Scanner;
import java.util.Properties;

public class FileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;
    private IEntityFactory<T> entityFactory;

    public FileRepository(String fileName, IEntityFactory<T> entityFactory) throws FileNotFoundException, DuplicateEntityException, RepositoryException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;

        readFromFile();
    }

//    private void readFromFile() throws FileNotFoundException, DuplicateEntityException, RepositoryException {
//        File file = new File(fileName);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNext())
//        {
//            String line = scanner.nextLine();
//            T entity = entityFactory.createEntity(line);
//            add(entity);
//        }
//        scanner.close();
//    }

    private void readFromFile() throws FileNotFoundException, DuplicateEntityException, RepositoryException {
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

}
