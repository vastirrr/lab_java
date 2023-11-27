import Domain.BMI;
import Domain.BMIFactory;
import Domain.BP;
import Domain.BPFactory;
import Repository.BinaryFileRepository;
import Repository.IRepository;
import Repository.TextFileRepository;
import Service.ServiceBMI;
import Service.ServiceBP;
import UI.Console;

import java.util.Objects;

public  class Main {
    public static void main(String[] args){
        IRepository<BP> bpRepository = null;
        IRepository<BMI> bmiRepository = null;

        Settings settings = new Settings();

        String typeOfRepo = settings.getRepoType();
        if (Objects.equals(typeOfRepo, "text")){
            bpRepository = new TextFileRepository<>(settings.getBPFileName(), new BPFactory());
            bmiRepository = new TextFileRepository<>(settings.getBMIFileName(), new BMIFactory());
        }
        else if (Objects.equals(typeOfRepo, "binary")){
            bpRepository = new BinaryFileRepository<>(settings.getBPFileName(), new BPFactory());
            bmiRepository = new BinaryFileRepository<>(settings.getBMIFileName(), new BMIFactory());
        }

        ServiceBP serviceBP = new ServiceBP(bpRepository);
        ServiceBMI serviceBMI = new ServiceBMI(bpRepository, bmiRepository);

        Console console = new Console(serviceBP, serviceBMI);
        console.start();

    }
}
