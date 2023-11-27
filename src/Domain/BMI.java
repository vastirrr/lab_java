package Domain;

public class BMI extends HealthData{
    private float value;

    public BMI(String date, float value) {
        super(date);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean isNormalValue(){
        BMI bmi = new BMI(date, value);
        if (bmi.getValue() > 18.5 && bmi.getValue() < 25) return true;
        else return false;
    };

}

