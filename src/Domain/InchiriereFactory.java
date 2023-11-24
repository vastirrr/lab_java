package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InchiriereFactory implements IEntityFactory<Inchiriere>{
    @Override
    public Inchiriere createFromString(String line) {
        String[] tokens = line.split(",");
        try {
            return new Inchiriere(Integer.parseInt(tokens[0]),
                    new SimpleDateFormat("dd/MM/yyyy").parse(tokens[1]),
                    new SimpleDateFormat("dd/MM/yyyy").parse(tokens[2]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String createFromEntity(Inchiriere entity) {
        return entity.getId() + "," + entity.getDataInceput() + "," + entity.getDataSfarsit();
    }
}
