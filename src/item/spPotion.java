package item;

public class spPotion extends item{
    private int number;
    public spPotion(String name, int price, int number) {
        super(name, price);
        this.number = number;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
