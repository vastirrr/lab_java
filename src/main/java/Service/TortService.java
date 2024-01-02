package Service;

import Domain.Tort;
import Repository.DuplicateEntityException;
import Repository.IRepository;

import java.util.Iterator;
import java.util.List;

public class TortService {
    public IRepository<Tort> repoTort;

    public TortService(IRepository<Tort> tortRepo) throws DuplicateEntityException {
        this.repoTort = tortRepo;
    }

    public void addTort(int id, String tip) throws DuplicateEntityException {
        Tort car = new Tort(id, tip);

        repoTort.add(car);
    }
    public List<Tort> getAll(){
        return (List<Tort>) repoTort.getAll();
    }
}
