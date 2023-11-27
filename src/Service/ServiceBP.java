package Service;


import Domain.BMI;
import Domain.BP;
import Repository.IRepository;

import java.util.List;

public class ServiceBP {
    private IRepository<BP> repoBP;

    public ServiceBP(IRepository<BP> BPrepo)  {
        this.repoBP = BPrepo;
    }
    public void addBP(String date,Integer systolicValue, Integer diastolicValue){
        BP bp = new BP(date, systolicValue, diastolicValue);
        repoBP.add(bp);
    }
    public BP readBP(String date) {
        BP bp = repoBP.findByDate(date);
        if (bp == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return bp;
    }

    public void updateBP(String date,Integer systolicValue, Integer diastolicValue) {
        BP bp = new BP(date, systolicValue, diastolicValue);
        repoBP.update(bp);
    }

    public void deleteBP(String date) {
        repoBP.delete(date);
    }

    public List<BP> getAll() {
        return repoBP.getAll();
    }

}



