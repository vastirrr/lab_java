import Domain.Car;
import Domain.CarFactory;
import Domain.Inchiriere;
import Domain.InchiriereFactory;
import Repository.BinaryFileRepository;
import Repository.IRepository;
import Repository.MemoryRepository;
import Repository.TextFileRepository;
import Service.ServiceCar;
import Service.ServiceInchiriere;
import UI.Console;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        IRepository<Car> carRepository = new MemoryRepository<>();
        IRepository<Inchiriere> inchiriereRepository = new MemoryRepository<>();

        Settings settings = new Settings();

        if (Objects.equals(settings.getRepoType(), "text")){
            carRepository = new TextFileRepository<>(settings.getCarsFileName(), new CarFactory());
            inchiriereRepository = new TextFileRepository<>(settings.getInchirieriFileName(), new InchiriereFactory());
        }
        else if (Objects.equals(settings.getRepoType(), "binary")){
            carRepository = new BinaryFileRepository<>(settings.getCarsFileName(), new CarFactory());
            inchiriereRepository = new BinaryFileRepository<>(settings.getInchirieriFileName(), new InchiriereFactory());
        }

        ServiceCar serviceCar = new ServiceCar(carRepository);
        ServiceInchiriere serviceInchiriere = new ServiceInchiriere(carRepository, inchiriereRepository);

        Console console = new Console(serviceCar, serviceInchiriere);
        console.start();
    }
}