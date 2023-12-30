package Domain;
import java.io.Serializable;
public abstract class Entity implements Serializable {
    public int ID;
    private static final long serialVersionUID = 1L;

    public Entity(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
