package Service;

import Domain.Car;
import Domain.Inchiriere;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.RepositoryException;

import java.util.Date;
import java.util.Iterator;

public class ServiceCar {
    public IRepository<Car> repoCar;

    public ServiceCar(IRepository<Car> carRepo) throws DuplicateEntityException {
        this.repoCar = carRepo;
    }

    public Iterator<Car> carIterator() {
        return repoCar.iterator();
    }

    public void addCar(int ID, String marca, String model) throws DuplicateEntityException, RepositoryException {
        Car car = new Car(ID, marca, model);
        if (carExists(car))
            throw new IllegalArgumentException("Car already exists!");
        repoCar.add(car);
    }

    private Boolean carExists(Car car) {
        return repoCar.findById(car.getID()) != null;
    }

    public Car readCar(int ID) {
        Car car = repoCar.findById(ID);
        if (car == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return car;
    }

    public void updateCar(int ID, String marca, String model) {
        Car car = new Car(ID, marca, model);
        if (!carExists(car))
            throw new IllegalArgumentException("Car with specified ID doesn't exist!");
        repoCar.update(ID, marca, model);
    }


    public void deleteCar(int ID) {
        if (repoCar.findById(ID) == null)
            throw new IllegalArgumentException("Car with specified ID doesn't exist!");
        repoCar.remove(ID);
    }

}
