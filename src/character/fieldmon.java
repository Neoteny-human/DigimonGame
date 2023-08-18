package character;

import character.digimon.digimon;

import java.util.ArrayList;

public class fieldmon extends digimon {
    private int fieldNum; //필드몬스터 넘버링
    private String[] gen_name = new String[5];
    private ArrayList<coskill>[] coskill; // 유아기~궁극체까지의 스킬셋
    private ArrayList<buffskill> buff; //궁극체 버프스킬셋

    private int money; //드랍머니
    private int exp; //주는 경험치

    public fieldmon(int fieldNum, String name0, String name1, String name2, String name3, String name4, String type, double power, double max_hp, double hp,
                    ArrayList<coskill>[] coskill, ArrayList<buffskill> buff, int money, int exp){
        super(type, power, max_hp, hp);
        this.gen_name[0] = name0;
        this.gen_name[1] = name1;
        this.gen_name[2] = name2;
        this.gen_name[3] = name3;
        this.gen_name[4] = name4;
        this.setName(gen_name[character.getChapter()]);
        this.fieldNum = fieldNum;
        this.coskill = coskill;
        this.buff = buff;
        this.money = money;
        this.exp = exp;
        this.setGeneration(character.getChapter());
    }
    public fieldmon(){

    }

    public ArrayList<coskill> getCoskill() {
        return coskill[character.getChapter()];
    }

    public void setCoskill(coskill coskill) {
        this.coskill[character.getChapter()].add(coskill);
    }

    public void deleteCoskill(int index){
        this.coskill[character.getChapter()].remove(index);
    }

    public ArrayList<buffskill> getBuff() {
        return buff;
    }

    public void setBuff(buffskill buffskill) {
        this.buff.add(buffskill);
    }

    public void deleteBuff(int index){
        this.buff.remove(index);
    }


    public int getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(int fieldNum) {
        this.fieldNum = fieldNum;
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

    public void setName(){
        this.setName(gen_name[character.getChapter()]);
    }
    @Override
    public String getName(){
        return this.gen_name[character.getChapter()];
    }



    public double coskillAttack(digimon digimon, int index){
        double damage;
        damage = this.Attack(digimon) * coskill[character.getChapter()].get(index).getCoefficient()
                *coskill[character.getChapter()].get(index).getTimes();
        return damage;
    }

    public void useBuff(buffskill buffskill){
        this.setHp(this.getHp()+buffskill.getHeal());
        this.setShield(this.getShield()+buffskill.getShield());
        this.setPower(this.getPower()+buffskill.getPower_up());
    }
}
