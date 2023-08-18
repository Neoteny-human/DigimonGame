package NPC.merchant;

import item.spPotion;

import java.util.ArrayList;

public class spMerchant extends merchant{
    public ArrayList<spPotion>[] spPotions;
    public spMerchant(String name, String line, ArrayList<spPotion>[] spPotions) {
        super(name, line);
        this.spPotions = spPotions;
    }

    public ArrayList<spPotion>[] getSpPotions() {
        return spPotions;
    }

    public void setSpPotions(ArrayList<spPotion>[] spPotions) {
        this.spPotions = spPotions;
    }

    public void Show(){
        System.out.println("구매할 에이드를 고르세요.");
        for(int i = 0; i < spPotions[getChapter()].size(); i++){
            System.out.println((i+1) +". "+ spPotions[getChapter()].get(i).getName()+
                    " ("+spPotions[getChapter()].get(i).getNumber()+"개의 스킬을 회복)  가격: "+
                    spPotions[getChapter()].get(i).getPrice()+"골드");
        }
        System.out.println("0. 뒤로");

    }
}