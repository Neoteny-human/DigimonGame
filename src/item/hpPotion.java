package item;

public class hpPotion extends item{
    public double hp;
    public hpPotion(String name, int price, double hp){
        super(name, price);
        this.hp = hp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
}
