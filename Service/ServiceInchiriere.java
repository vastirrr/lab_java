package Service;

import Domain.Car;
import Domain.Inchiriere;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.RepositoryException;
import Service.ServiceCar;

import java.util.Date;
import java.util.Iterator;

public class ServiceInchiriere extends ServiceCar{
    //private IRepository<Car> repoCar;
    private IRepository<Inchiriere> repoInchiriere;

    public ServiceInchiriere(IRepository<Car> carRepository, IRepository<Inchiriere> inchiriereRepository) throws DuplicateEntityException {
        super(carRepository);
        this.repoInchiriere = inchiriereRepository;
    }

    public Iterator<Car> carIterator() {
        return repoCar.iterator();
    }

    public Iterator<Inchiriere> inchiriereIterator() {
        return repoInchiriere.iterator();
    }

    private Boolean carExists(Car car) {
        return repoCar.findById(car.getID()) != null;
    }

    public void addInchiriere(int ID, int carID, Date startDate, Date endDate) throws DuplicateEntityException, RepositoryException {
        Car car = repoCar.findById(carID);
        if (car == null)
            throw new IllegalArgumentException("The specified car doesn't exist!");

        Inchiriere inchiriere = new Inchiriere(ID, car, startDate, endDate);
        validateInchiriere(inchiriere);
        repoInchiriere.add(inchiriere);
    }

    private void validateInchiriere(Inchiriere inchiriere) {
        if (repoInchiriere.findById(inchiriere.getID()) != null)
            throw new IllegalArgumentException("There exists another rental with this ID!");
        if (inchiriere.getStartDate().compareTo(inchiriere.getEndDate()) > 0)
            throw new IllegalArgumentException("Start date cannot be after end date!");
        if (!carIsFree(inchiriere.getStartDate(), inchiriere.getEndDate(), inchiriere.getCar()))
            throw new IllegalArgumentException("Car is not free!");
    }

    private Boolean carIsFree(Date startDate, Date endDate, Car car) {
        for (Inchiriere inchiriere : repoInchiriere.getAll()) {
            if (inchiriere.getCar().equals(car)) {
                if (inchiriere.getEndDate().compareTo(startDate) > 0)
                    return false;
                if (inchiriere.getStartDate().compareTo(endDate) < 0)
                    return false;
            }
        }
        return true;
    }

    public Inchiriere readInchiriere(int ID) {
        Inchiriere inchiriere = repoInchiriere.findById(ID);
        if (inchiriere == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return inchiriere;
    }

    public void updateInchiriere(int ID, int carID, Date startDate, Date endDate) throws DuplicateEntityException, RepositoryException {
        Car car = repoCar.findById(carID);
        if (car == null)
            throw new IllegalArgumentException("The specified car doesn't exist!");

        Inchiriere thisInchiriere = new Inchiriere(ID, car, startDate, endDate);
        Inchiriere old = repoInchiriere.findById(ID);
        if (old == null)
            throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
        repoInchiriere.remove(ID);

        try {
            validateInchiriere(thisInchiriere);
            repoInchiriere.add(thisInchiriere);
        } catch (IllegalArgumentException | DuplicateEntityException exception) {
            repoInchiriere.add(old);
            throw exception;
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteInchiriere(int ID) {
        if (repoInchiriere.findById(ID) == null)
            throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
        repoInchiriere.remove(ID);
    }
}
