package Repository;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException(String msg){
        super(msg);
    }
}
