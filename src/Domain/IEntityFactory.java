package Domain;

public interface IEntityFactory <T extends HealthData>{
    T createFromString(String line);
    String createFromEntity(T entity);
}
