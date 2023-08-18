package NPC.merchant;

import item.fruit;

import java.util.ArrayList;

public class fruitMerchant extends merchant{
    public ArrayList<fruit>[] fruits;
    public fruitMerchant(String name, String line, ArrayList<fruit>[] fruits) {
        super(name, line);
        this.fruits = fruits;
    }

    public ArrayList<fruit>[] getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<fruit>[] fruits) {
        this.fruits = fruits;
    }

    public void Show(){
        System.out.println("구매할 열매를 고르세요.");
        for(int i = 0; i < fruits[getChapter()].size(); i++){
            System.out.println((i+1) +". "+ fruits[getChapter()].get(i).getName()+
                    " (배고픔"+fruits[getChapter()].get(i).getHungry()+", hp"+(int)fruits[getChapter()].get(i).getHp()
                    +" 회복)  가격: "+ fruits[getChapter()].get(i).getPrice()+"골드");
        }
        System.out.println("0. 뒤로");

    }
}