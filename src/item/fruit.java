package item;

public class fruit extends item{
    private int hungry;
    private double hp;
    public fruit(String name, int price, int hungry, double hp) {
        super(name, price);
        this.hp = hp;
        this.hungry = hungry;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

}
