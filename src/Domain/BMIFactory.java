package Domain;

import java.io.Serializable;
public class BMIFactory implements IEntityFactory<BMI>{
    @Override
    public BMI createFromString(String line) {
        String[] tokens = line.split(",");
        return new BMI(tokens[0], Float.parseFloat(tokens[1]));
    }

    @Override
    public String createFromEntity(BMI entity) {
        return entity.getDate() + "," + entity.getValue();
    }
}
