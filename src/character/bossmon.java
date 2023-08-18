package character;

import character.digimon.digimon;

import java.util.ArrayList;

public class bossmon extends digimon {
    private int bossOrder;
    private int money;
    private int exp;
    private ArrayList<coskill> coskill;
    private ArrayList<buffskill> buffskill;
    private String line;
    private String will;


    public bossmon(int bossOrder, String name, String type, int generation, double power, double max_hp, double hp,
                   int money, int exp, ArrayList<coskill> coskill, ArrayList<buffskill> buffskill, String line, String will){
        super(name, type, generation, power, max_hp, hp);
        this.bossOrder = bossOrder;
        this.money = money;
        this.exp = exp;
        this.coskill = coskill;
        this.buffskill = buffskill;
        this.line = line;
        this.will = will;
    }
    public bossmon(){

    }


    public int getBossOrder() {
        return bossOrder;
    }

    public void setBossOrder(int bossOrder) {
        this.bossOrder = bossOrder;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ArrayList<coskill> getCoskill() {
        return coskill;
    }

    public void setCoskill(ArrayList<coskill> coskill) {
        this.coskill = coskill;
    }

    public ArrayList<buffskill> getBuffskill() {
        return buffskill;
    }

    public void setBuffskill(ArrayList<buffskill> buffskill) {
        this.buffskill = buffskill;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWill() {
        return will;
    }

    public void setWill(String will) {
        this.will = will;
    }

    public double coskillAttack(digimon digimon, int index){
        double damage;
        damage = this.Attack(digimon) * coskill.get(index).getCoefficient()
                *coskill.get(index).getTimes();
        return damage;
    }
    public void useBuff(buffskill buffskill){
        this.setHp(this.getHp()+buffskill.getHeal());
        this.setShield(this.getShield()+buffskill.getShield());
        this.setPower(this.getPower()+buffskill.getPower_up());
    }


}
