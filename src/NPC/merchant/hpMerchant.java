package NPC.merchant;

import item.hpPotion;

import java.util.ArrayList;

public class hpMerchant extends merchant{
    public ArrayList<hpPotion>[] hpPotions;
    public hpMerchant(String name, String line, ArrayList<hpPotion>[] hpPotions) {
        super(name, line);
        this.hpPotions = hpPotions;
    }

    public ArrayList<hpPotion>[] getHpPotions() {
        return hpPotions;
    }

    public void setHpPotions(ArrayList<hpPotion>[] hpPotions) {
        this.hpPotions = hpPotions;
    }

    public void Show(){
        System.out.println("구매할 포션을 고르세요.");
        for(int i = 0; i < hpPotions[getChapter()].size(); i++){
            System.out.println((i+1) +". "+ hpPotions[getChapter()].get(i).getName()+
                    " (hp "+(int)hpPotions[getChapter()].get(i).getHp()+"회복)  가격: "
                    +hpPotions[getChapter()].get(i).getPrice()+"골드");
        }
        System.out.println("0. 뒤로");

    }
}
