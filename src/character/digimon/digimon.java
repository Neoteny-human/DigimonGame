package character.digimon;

import character.character;

public class digimon extends character {
    private double max_hp;
    private double hp;
    private double power;
    private String type;
    private int generation; //알, 유아기, 성숙기, 완전체, 궁극체 (0~4)
    private double shield;

    public digimon(String name, String type, int generation, double power, double max_hp, double hp){
        super(name);
        this.type = type;
        this.generation = generation;
        this.power = power;
        this.max_hp = max_hp;
        this.hp = hp;
        shield = 0;
    }
    public digimon(String type, double power, double max_hp, double hp){
        this.type = type;
        this.power = power;
        this.max_hp = max_hp;
        this.hp = hp;
        shield = 0;
    }

    public digimon(){

    }


    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(double max_hp) {
        this.max_hp = max_hp;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getShield() {
        return shield;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }


    public double Attack(digimon digimon){
        double damage;
        switch (this.type){
            case "Va" :
                switch (digimon.type){
                    case "Vi" :
                        damage = this.power * 1.2;
                        return damage;
                    case "Da" :
                    case "Un" :
                        damage = this.power * 0.8;
                        return damage;
                }
            case "Vi" :
                switch (digimon.type){
                    case "Da" :
                        damage = this.power * 1.2;
                        return damage;
                    case "Va" :
                    case "Un" :
                        damage = this.power * 0.8;
                        return  damage;
                }
            case "Da" :
                switch (digimon.type){
                    case "Va" :
                        damage = this.power * 1.2;
                        return damage;
                    case "Vi" :
                    case "Un" :
                        damage = this.power * 0.8;
                        return damage;
                }
            case "Un" :
                switch (digimon.type){
                    case "Va" :
                    case "Da" :
                    case "Vi" :
                        damage = this.power * 1.2;
                        return damage;
                }
            default:
                damage = this.power;
                return damage;
        }
    }
}


