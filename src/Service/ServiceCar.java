package Service;

import Domain.Car;
import Domain.Inchiriere;
import Repository.IRepository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ServiceCar {
    private IRepository<Car> repoCar;

    public ServiceCar(IRepository<Car> carRepo)  {
        this.repoCar = carRepo;
    }
    public void addCar(int ID, String model) {
        Car car = new Car(ID, model);
        repoCar.add(car);
    }
    public Car readCar(int ID) {
        Car car = repoCar.findById(ID);
        if (car == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return car;
    }

    public void updateCar(int ID,String model) {
        Car car = new Car(ID, model);
        repoCar.update(car);
    }

    public void deleteCar(int ID) {
        repoCar.delete(ID);
    }

    public List<Car> getAll() {
        return repoCar.getAll();
    }
}

