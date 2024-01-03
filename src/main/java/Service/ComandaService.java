package Service;

import Domain.Comanda;
import Domain.Tort;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import ViewModels.TortNrComenzi;

import java.util.*;

public class ComandaService {
    public IRepository<Comanda> comandaRepo;
    public IRepository<Tort> tortRepo;

    public ComandaService(IRepository<Comanda> comandaRepo, IRepository<Tort> tortRepo) throws DuplicateEntityException {
        this.comandaRepo = comandaRepo;
        this.tortRepo = tortRepo;
    }

    public void addComanda(int id, List<Integer> listId, Date data) throws DuplicateEntityException {
        Comanda comanda = new Comanda(id, listId, data);
        comandaRepo.add(comanda);
    }
    public List<Comanda> getAll(){
        return (List<Comanda>) comandaRepo.getAll();
    }

    ///functionalitati
    public Map<Date, Integer> nrTorturiPerZi(){
        Map<Date, Integer> rezultat = new HashMap<>();
        List<Comanda> comenzi = getAll();

        for (Comanda comanda: comenzi) {
            Date dataRezultat = comanda.getData();
            if(rezultat.containsKey(dataRezultat)){
                rezultat.put(dataRezultat, rezultat.get(dataRezultat) + comanda.getTorturi().size());
            }
            else {
                rezultat.put(dataRezultat, comanda.getTorturi().size());
            }
        }
        return rezultat;
    }

    public Map<Integer, Integer> nrTorturiPerLuna(){
        Map<Integer, Integer> rezultat = new HashMap<>();
        List<Comanda> comenzi = getAll();

        //le-am initializat pe toate cu 0
        for(int i = 1; i <= 12; i++){
            rezultat.put(i, 0);
        }

        //parcurgem comenzile
        for (Comanda comanda: comenzi) {
            Integer luna = comanda.getData().getMonth() + 1;
            rezultat.put(luna, rezultat.get(luna) + comanda.getTorturi().size());
        }
        return rezultat;
    }

    public List<TortNrComenzi> celeMaiComandate(){
        List<TortNrComenzi> rezultat = new ArrayList<>();
        List<Comanda> comenzi = getAll();
        List<Tort> torturi = (List<Tort>) tortRepo.getAll();

        Map<Integer, Integer> rezultatIntermediar = new HashMap<>();

        //initializam cu 0
        for(Tort tort: torturi){
            rezultatIntermediar.put(tort.getID(),0);
        }

        for (Comanda comanda: comenzi) {
            List<Integer> tortIdComanda = comanda.getTorturi();

            for (Integer id: tortIdComanda) {
                rezultatIntermediar.put(id, rezultatIntermediar.get(id) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> tortIdSiNrComenzi: rezultatIntermediar.entrySet()) {
            Tort tort = null;
            for (Tort t: torturi) {
                if(t.getID() == tortIdSiNrComenzi.getKey()){
                    tort = t;
                }
            }
            rezultat.add(new TortNrComenzi(tort, tortIdSiNrComenzi.getValue()));
        }
        rezultat.sort((o1, o2) -> o2.getNrComenzi().compareTo(o1.getNrComenzi()));
        return rezultat;
    }

    ///filtrare : torturile care au fost comandate intre 2 date
    public List<Tort> torturiIntreDate(Date startDate, Date endDate){
        List<Tort> rezultat = new ArrayList<>();

        List<Comanda> comenzi = getAll();
        List<Tort> torturi = (List<Tort>) tortRepo.getAll();

        for (Tort tort: torturi) {
            boolean gasit = false;
            for(Comanda comanda: comenzi){
                // compareTo() returneaza < 0 daca d1 < d2 sauuu 0 daca d1 == d2 sauuuu > 0 d1 > d2
                if(comanda.getData().compareTo(startDate) > 0 && comanda.getData().compareTo(endDate) < 0){
                    for(Integer tortId: comanda.getTorturi()){
                        if(tortId == tort.getID()){
                            gasit = true;
                        }
                    }
                }
            }
            if(gasit){
                rezultat.add(tort);
            }
        }
        return rezultat;
    }

}
