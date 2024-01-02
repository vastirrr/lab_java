package Domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ComandaFactory  implements IEntityFactory<Comanda> {
    @Override
    public Comanda createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        Date data = null;
        try {
                 data = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(line.split(",")[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Integer> torturiId = new ArrayList<>();
        for (int i = 2;i < line.split(",").length; i++){
            torturiId.add(Integer.parseInt(line.split(",")[i]));
        }

        return new Comanda(id, torturiId, data);
    }

    @Override
    public String toFileString(Comanda d) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String line = d.getID() + "," + dateFormat.format(d.getData());
        for(Integer tortId: d.torturiID){
            line = line + ","+ tortId;
        }
        line = line + "\n";
        return line;
    }

}