import Domain.Car;
import Domain.CarFactory;
import Domain.IEntityFactory;
import Domain.Inchiriere;
import Repository.*;
import Service.ServiceCar;
import Service.ServiceInchiriere;
import UI.Console;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // Read configuration from settings.properties
            Properties config = readConfig();

            // Get repository type and file names from config
            String repositoryType = config.getProperty("Repository");
            String carsFile = config.getProperty("Cars");
            String inchirieriFile = config.getProperty("Inchirieri");

            if ("memory".equals(repositoryType)) {
                // Use MemoryRepository
                useMemoryRepository(carsFile);
            } else if ("binary".equals(repositoryType)) {
                // Use BinaryRepository
                useBinaryRepository(carsFile, inchirieriFile);
            } else {
                // Handle other repository types if needed
                System.out.println("Unsupported repository type.");
            }

        } catch (IOException | DuplicateEntityException | RepositoryException e) {
            e.printStackTrace();
        }
    }

    private static void useMemoryRepository(String carsFile) throws IOException, DuplicateEntityException, RepositoryException {
        // Initialize Car-related components for MemoryRepository
        IEntityFactory<Car> carFactory = new CarFactory();
        IRepository<Car> carRepo = new MemoryRepository<>();
        readDataFromFile(carsFile, carRepo, carFactory);

        // Initialize and use ServiceCar and Console
        IRepository<Inchiriere> inchiriereRepo = new MemoryRepository<>();
        ServiceCar serviceCar = new ServiceCar(carRepo);
        ServiceInchiriere serviceInchiriere = new ServiceInchiriere(carRepo, inchiriereRepo);
        Console console = new Console(serviceCar, serviceInchiriere);
        console.start();
    }

    private static void useBinaryRepository(String carsFile, String inchirieriFile) throws DuplicateEntityException, RepositoryException, IOException {
        // Initialize Car-related components for BinaryRepository
        IEntityFactory<Car> carFactory = new CarFactory();
        //IRepository<Car> carRepo = new BinaryRepository<>(carsFile);
        //readDataFromFile(carsFile, carRepo, carFactory);
        IRepository<Car> carRepo = new FileRepository<>("C:\\Users\\rusva\\Desktop\\MAP\\a3-vastirrr\\cars.txt", carFactory);


        // Initialize and use ServiceCar and Console
        ServiceCar serviceCar = new ServiceCar(carRepo);
        ServiceInchiriere serviceInchiriere = new ServiceInchiriere(carRepo, new BinaryRepository<>(inchirieriFile));
        Console console = new Console(serviceCar, serviceInchiriere);
        console.start();
    }

    private static void readDataFromFile(String fileName, IRepository<Car> carRepo, IEntityFactory<Car> carFactory)
            throws IOException, DuplicateEntityException, RepositoryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = carFactory.createEntity(line);
                carRepo.add(car);
            }
        }
    }

    private static Properties readConfig() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("C:\\Users\\rusva\\Desktop\\MAP\\a3-vastirrr\\settings.properties")) {
            properties.load(input);
        }
        return properties;
    }

}






















//import Domain.*;
//import Repository.*;
//import Service.ServiceCar;
//import Service.ServiceInchiriere;
//import UI.Console;
//
//import java.io.FileNotFoundException;
//
//public class Main {
//    public static void main(String[] args) throws DuplicateEntityException, FileNotFoundException, RepositoryException {
//
//        //IRepository<Car> carRepo = new MemoryRepository<>();
//       // IRepository<Inchiriere> inchiriereRepo = new MemoryRepository<>();
//
//        IEntityFactory<Car> carFactory = new CarFactory();
//        IRepository<Car> carRepo = new FileRepository<>("C:\\Users\\rusva\\Desktop\\MAP\\a3-vastirrr\\cars.txt", carFactory);
//
//        IRepository<Inchiriere> inchiriereRepo = new BinaryRepository<>("C:\\Users\\rusva\\Desktop\\MAP\\a3-vastirrr\\inchiriere.bin");
//
//        ServiceCar serviceCar = new ServiceCar(carRepo);
//        ServiceInchiriere serviceInchiriere = new ServiceInchiriere(carRepo, inchiriereRepo);
//        Console console = new Console(serviceCar, serviceInchiriere);
//
//        console.start();
//    }
//}
