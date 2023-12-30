package Domain;

import java.util.Date;
import java.util.List;

public class Comanda extends Entity{
    List<Tort> torturi;
    Date data;
    public Comanda(int ID, List<Tort> torturi, Date data) {
        super(ID);
        this.torturi = torturi;
        this.data = data;
    }

    public List<Tort> getTorturi() {
        return torturi;
    }

    public void setTorturi(List<Tort> torturi) {
        this.torturi = torturi;
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
                "torturi=" + torturi +
                ", data=" + data +
                ", ID=" + ID +
                '}';
    }
}
