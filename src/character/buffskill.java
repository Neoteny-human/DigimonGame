package character;
//버프 스킬
public class buffskill extends skill{
    private double heal;
    private double power_up;
    private double shield;

    public buffskill(String name, int max_sp, double heal, double power_up, double shield) {
        super(name, max_sp);
        this.heal = heal;
        this.power_up = power_up;
        this.shield = shield;
    }

    public void setHeal(double heal) {
        this.heal = heal;
    }

    public void setPower_up(double power_up) {
        this.power_up = power_up;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }

    public double getHeal() {
        return heal;
    }

    public double getPower_up() {
        return power_up;
    }

    public double getShield() {
        return shield;
    }
}
