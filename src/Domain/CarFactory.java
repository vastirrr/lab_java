package Domain;

public class CarFactory implements IEntityFactory<Car>{
    @Override
    public Car createFromString(String line) {
        String[] tokens = line.split(",");
        return new Car(Integer.parseInt(tokens[0]), tokens[1]);
    }

    @Override
    public String createFromEntity(Car entity) {
        return entity.getId() + "," + entity.getModel();
    }

}
