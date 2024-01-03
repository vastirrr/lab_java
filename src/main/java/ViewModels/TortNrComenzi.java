package ViewModels;

import Domain.Tort;

public class TortNrComenzi {
    Tort tort;
    Integer nrComenzi;

    public TortNrComenzi(Tort tort, Integer nrComenzi) {
        this.tort = tort;
        this.nrComenzi = nrComenzi;
    }

    public Integer getNrComenzi() {
        return nrComenzi;
    }

    @Override
    public String toString() {
        return "TortNrComenzi{" +
                "tort=" + tort +
                ", nrComenzi=" + nrComenzi +
                '}';
    }
}
