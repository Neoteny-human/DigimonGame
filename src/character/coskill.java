package character;
//계수 스킬
public class coskill extends skill{
    private double coefficient;
    private int times;

    public coskill(String name, int max_sp, double coefficient, int times) {
        super(name, max_sp);
        this.coefficient = coefficient;
        this.times = times;

    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getTimes() {
        return times;
    }
}
