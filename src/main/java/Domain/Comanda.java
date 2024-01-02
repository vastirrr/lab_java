package Domain;

import java.util.Date;
import java.util.List;

public class Comanda extends Entity{
    List<Integer> torturiID;
    Date data;
    public Comanda(int ID, List<Integer> torturi, Date data) {
        super(ID);
        this.torturiID = torturi;
        this.data = data;
    }

    public List<Integer> getTorturi() {
        return torturiID;
    }

    public void setTorturi(List<Integer> torturi) {
        this.torturiID = torturi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "torturiID=" + torturiID +
                ", data=" + data +
                ", ID=" + ID +
                '}';
    }
}
