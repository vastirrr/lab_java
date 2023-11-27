package Service;


import Domain.BMI;
import Domain.BP;
import Repository.IRepository;

import java.util.List;

public class ServiceBMI {
    private IRepository<BP> repoBP;
    private IRepository<BMI> repoBMI;

    public ServiceBMI(IRepository<BP> BPrepo, IRepository<BMI> BMIrepo)  {
        this.repoBP = BPrepo;
        this.repoBMI = BMIrepo;
    }
    public void addBMI(String date, Float value) {
        BMI bmi = new BMI(date, value);
        repoBMI.add(bmi);
    }
    public BMI readBMI(String date) {
        BMI bmi = repoBMI.findByDate(date);
        if (bmi == null)
            throw new IllegalArgumentException("Specified ID doesn't exist!");
        return bmi;
    }

    public void updateBMI(String date, Float value) {
        BMI bmi = new BMI(date, value);
        repoBMI.update(bmi);
    }

    public void deleteBMI(String date) {
        repoBMI.delete(date);
    }

    public List<BMI> getAll() {
        return repoBMI.getAll();
    }

}


