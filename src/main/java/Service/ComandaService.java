package Service;

import Domain.Comanda;
import Domain.Tort;
import Repository.DuplicateEntityException;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class ComandaService {
    public IRepository<Comanda> comandaRepo;

    public ComandaService(IRepository<Comanda> comandaRepo) throws DuplicateEntityException {
        this.comandaRepo = comandaRepo;
    }

    public void addComanda(int id, List<Integer> listId, Date data) throws DuplicateEntityException {
        Comanda comanda = new Comanda(id, listId, data);
        comandaRepo.add(comanda);
    }
    public List<Comanda> getAll(){
        return (List<Comanda>) comandaRepo.getAll();
    }
}
