package Domain;

import java.io.Serializable;
public abstract class HealthData implements Serializable{
    public String date;
    public boolean isNormalValue(){
        return true;
    };

    public HealthData(String date) {
        this.date = date;
    }

    public String toString() {
        return "HealthData{" +
                "date=" + getDate() +
                ", isNormalValue='" + isNormalValue() + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }
}
