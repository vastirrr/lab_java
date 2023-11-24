package Service;

import Domain.Car;
import Domain.Inchiriere;
import Repository.IRepository;
import Service.ServiceCar;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ServiceInchiriere {
    private IRepository<Car> repoCar;
    private IRepository<Inchiriere> repoInchiriere;

    public ServiceInchiriere(IRepository<Car> carRepository, IRepository<Inchiriere> inchiriereRepository) {
        this.repoCar = carRepository;
        this.repoInchiriere = inchiriereRepository;
    }

    public void addInchiriere(int ID, int carID, Date startDate, Date endDate) {
        Car car = repoCar.findById(carID);
        if (car == null)
            throw new IllegalArgumentException("The specified car doesn't exist!");

        Inchiriere inchiriere = new Inchiriere(ID, startDate, endDate);
        validateInchiriere(inchiriere);
        repoInchiriere.add(inchiriere);
    }

    private void validateInchiriere(Inchiriere inchiriere) {
        if (repoInchiriere.findById(inchiriere.getId()) != null)
            throw new IllegalArgumentException("There exists another rental with this ID!");
        if (inchiriere.getDataInceput().compareTo(inchiriere.getDataSfarsit()) > 0)
            throw new IllegalArgumentException("Start date cannot be after end date!");
    }

    public Inchiriere readInchiriere(int ID) {
        Inchiriere inchiriere = repoInchiriere.findById(ID);
        if (inchiriere == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return inchiriere;
    }

    public void updateInchiriere(int ID, int carID, Date startDate, Date endDate) {
        Car car = repoCar.findById(carID);
        if (car == null)
            throw new IllegalArgumentException("The specified car doesn't exist!");

        Inchiriere thisInchiriere = new Inchiriere(ID, startDate, endDate);
        Inchiriere old = repoInchiriere.findById(ID);
        if (old == null)
            throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
        repoInchiriere.delete(ID);

        try {
            validateInchiriere(thisInchiriere);
            repoInchiriere.add(thisInchiriere);
        } catch (IllegalArgumentException exception) {
            repoInchiriere.add(old);
            throw exception;
        }
    }

        public void deleteInchiriere(int ID){
            if (repoInchiriere.findById(ID) == null)
                throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
            repoInchiriere.delete(ID);
        }

    public List<Inchiriere> getAll() {
        return repoInchiriere.getAll();
    }
}
