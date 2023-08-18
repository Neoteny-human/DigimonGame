package NPC;

public class trainer extends NPC{
    private int money; //훈련비용
    private double growth;

    public trainer(String name, String line, int money, double growth) {
        super(name, line);
        this.money = money;
        this.growth = growth;
    }



    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public double getGrowth() {
        return growth;
    }
    public void setGrowth(double growth) {
        this.growth = growth;
    }
}
