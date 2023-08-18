package NPC;

public class veter extends NPC{
    private int money;
    public veter(String name, String line, int money) {
        super(name, line);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
