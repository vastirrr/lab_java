package Domain;

public class BP extends HealthData{
    private Integer systolicValue;
    private Integer diastolicValue;

    public BP(String date, Integer systolicValue, Integer diastolicValue) {
        super(date);
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
    }

    public Integer getSystolicValue() {
        return systolicValue;
    }

    public Integer getDiastolicValue() {
        return diastolicValue;
    }

    public void setSystolicValue(Integer systolicValue) {
        this.systolicValue = systolicValue;
    }

    public void setDiastolicValue(Integer diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

    @Override
    public boolean isNormalValue(){
        BP bp = new BP(date, systolicValue, diastolicValue);
        if(bp.getSystolicValue() > 100 && bp.getSystolicValue() < 130
                && bp.getDiastolicValue() > 60 && bp.getDiastolicValue() < 80) return true;
        else return false;
    };

}
