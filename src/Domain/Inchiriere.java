package Domain;

import java.util.Date;

public class Inchiriere extends Entity{
    private Date dataInceput;
    private Date dataSfarsit;

    public Inchiriere(int id, Date dataInceput, Date dataSfarsit) {
        super(id);
        this.dataInceput = dataInceput;
        this.dataSfarsit = dataSfarsit;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }
}
