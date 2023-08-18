package character;
import character.digimon.digimon;

import java.util.ArrayList;



public class partner extends digimon {

    private String[] gen_name = new String[5];
    private ArrayList<coskill>[] coskill; // 유아기~궁극체까지의 스킬셋
    private ArrayList<buffskill> buff; //궁극체 버프스킬셋
    private int exp;
    private int level = 1;
    private int hungry;

    public partner(String name0, String name1, String name2, String name3, String name4,
                   ArrayList<coskill>[] coskill,
                   String type, double max_hp, double hp, int generation, double power, ArrayList<buffskill> buff){
        super(name0, type, generation, power, max_hp, hp);
        this.gen_name[0] = name0;
        this.gen_name[1] = name1;
        this.gen_name[2] = name2;
        this.gen_name[3] = name3;
        this.gen_name[4] = name4;
        this.coskill = coskill;
        this.buff = buff;
        this.level = 0;
        this.hungry = 0;
    }





    public String[] getGen_name() {
        return gen_name;
    }

    public void setGen_name(int generation, String name){
        gen_name[generation] = name;
    }

    public void setCoskill(int generation, int index, coskill coskill){
        this.coskill[generation].add(index, coskill);
    }

    public ArrayList<coskill> getCoskill(){
        return coskill[this.getGeneration()];
    }

    public ArrayList<coskill> getGenCoskill(int generation){
        return coskill[generation];
    }

    public void deleteCoskill(int generation, int index){
        this.coskill[generation].remove(index);
    }
    public  ArrayList<buffskill> getBuff(){
        return this.buff;
    }

    public void deleteBuff(int index){
        this.buff.remove(index);
    }

    public void setBuff(int index, buffskill buffskill) {
        this.buff.add(index, buffskill);
    }


    public void upgrade(){
        switch(getGeneration()){
            case 0:
                System.out.println(getName() +" 부화!");
                this.setName(gen_name[1]);
                System.out.println(gen_name[1]+"이 되었다!");
                this.setGeneration(getGeneration()+1);
            case 1:
                if(level>=5){
                    System.out.println(getName() +" 진화!");
                    this.setName(gen_name[2]);
                    System.out.println(gen_name[2]+"으로 진화했다!");
                    this.setGeneration(getGeneration()+1);

                    exp = 0;
                }
            case 2:
                if(level>=7){
                    System.out.println(getName()+" 진화!");
                    this.setName(gen_name[3]);
                    System.out.println(gen_name[3]+"으로 진화했다!");
                    this.setGeneration(getGeneration()+1);
                    exp = 0;
                }
            case 3:
                if(level>=15){
                    System.out.println(getName()+" 진화!");
                    this.setName(gen_name[4]);
                    System.out.println(gen_name[4]+"으로 진화했다!");
                    this.setGeneration(getGeneration()+1);
                    exp = 0;
                }
            default:
        }
    }

    public void levelUp(){
        if(exp>=this.getLevel()*getLevel()*5){
            setLevel(getLevel()+1);
            System.out.println(getLevel()+"레벨로 레벨 업!");
            setPower(getPower()+getLevel()*3);
            setMax_hp(getMax_hp()+getLevel()*2);
            setHp(getMax_hp());
            setExp(-getLevel()*getLevel()*5);
            upgrade();
        }
    }



    public void setExp(int exp) {
        this.exp += exp;
    }

    public int getExp() {
        return exp;
    }


    public void setBuff(ArrayList<buffskill> buff) {
        this.buff = buff;
    }

    public void setGen_name(String[] gen_name) {
        this.gen_name = gen_name;
    }

    public void setCoskill(ArrayList<coskill>[] coskill) {
        this.coskill = coskill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public double coskillAttack(digimon digimon, int index){
        double damage;
        damage = this.Attack(digimon) * coskill[getGeneration()].get(index).getCoefficient()
        *coskill[getGeneration()].get(index).getTimes();
        return damage;
    }
    public void useBuff(buffskill buffskill){
        this.setHp(this.getHp()+buffskill.getHeal());
        this.setShield(this.getShield()+buffskill.getShield());
        this.setPower(this.getPower()+buffskill.getPower_up());
    }

}