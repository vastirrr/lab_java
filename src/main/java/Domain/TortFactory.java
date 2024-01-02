package Domain;

public class TortFactory implements IEntityFactory<Tort> {
    @Override
    public Tort createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String tip = line.split(",")[1];

        return new Tort(id, tip);
    }

    @Override
    public String toFileString(Tort d) {
        return d.getID() + "," + d.getTip() + "\n";
    }
}
