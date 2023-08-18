package NPC;


import character.buffskill;
import character.coskill;

import java.util.ArrayList;

public class chief extends trainer{
    ArrayList<coskill> coskills = new ArrayList<>();
    ArrayList<buffskill> buffskills = new ArrayList<>();

    public chief(String name, String line, int money, double growth, ArrayList<coskill> coskills,
                 ArrayList<buffskill> buffskills) {
        super(name, line, money, growth);
        this.coskills = coskills;
        this.buffskills = buffskills;
    }

    public ArrayList<coskill> getCoskills() {
        return coskills;
    }

    public void setCoskills(ArrayList<coskill> coskills) {
        this.coskills = coskills;
    }

    public ArrayList<buffskill> getBuffskills() {
        return buffskills;
    }

    public void setBuffskills(ArrayList<buffskill> buffskills) {
        this.buffskills = buffskills;
    }
}
