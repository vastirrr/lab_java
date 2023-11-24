package Domain;

import java.io.Serializable;
public class CarFactory  implements IEntityFactory<Car>, Serializable{
    @Override
    public Car createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String marca = line.split(",")[1];
        String model = line.split(",")[2];

        return new Car(id, marca, model);
    }
}
