package Service;

import Domain.Entity;
import Domain.Tort;
import Repository.DuplicateEntityException;
import Repository.IRepository;

import java.util.Iterator;
import java.util.List;

public class Service {
    public IRepository<Tort> repoCar;

    public Service(IRepository<Tort> carRepo) throws DuplicateEntityException {
        this.repoCar = carRepo;
    }

    public Iterator<Tort> carIterator() {
        return repoCar.iterator();
    }

    public void addCar(int id, String tip) throws DuplicateEntityException {
        Tort car = new Tort(id, tip);

        repoCar.add(car);
    }
    public List<Tort> getAll(){
        return (List<Tort>) repoCar.getAll();
    }
}
