package NPC;

public class part extends trainer{
    private int exp; //레벨 X 추가 경험치

    public part(String name, String line, int money, double growth, int exp) {
        super(name, line, money, growth);
        this.exp = exp;
    }



    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

}
