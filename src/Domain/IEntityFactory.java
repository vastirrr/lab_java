package Domain;

public interface IEntityFactory<T extends Entity>{
    T createFromString(String line);
    String createFromEntity(T entity);
}
