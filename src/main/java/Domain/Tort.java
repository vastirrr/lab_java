package Domain;

public class Tort extends Entity{
    String tip;
    public Tort(int ID, String tip) {
        super(ID);
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Tort{" +
                "tip='" + tip + '\'' +
                ", ID=" + ID +
                '}';
    }
}
