package Domain;

import java.io.Serializable;
public class BPFactory implements IEntityFactory<BP>{
    @Override
    public BP createFromString(String line)  {
        String[] tokens = line.split(",");
        return new BP(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }

    @Override
    public String createFromEntity(BP entity) {
        return entity.getDate() + "," + entity.getSystolicValue() + "," + entity.getDiastolicValue();
    }
}
