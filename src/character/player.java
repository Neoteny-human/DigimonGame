package character;

import item.fruit;
import item.hpPotion;
import item.item;
import item.spPotion;

import java.util.ArrayList;
import java.util.Scanner;

public class player extends character{
    private int money;
    private ArrayList<hpPotion>[] hpPotions;
    private ArrayList<spPotion>[] spPotions;
    private ArrayList<fruit>[] fruits;
    private ArrayList<partner> partner;
    private int shiningStone;
    private int ticket;

    public player(String name, int money, ArrayList<hpPotion>[] hpPotions, ArrayList<spPotion>[] spPotions,
                  ArrayList<fruit>[] fruits, ArrayList<partner> partner) {
        super(name);
        this.money = money;
        this.fruits = fruits;
        this.hpPotions = hpPotions;
        this.spPotions = spPotions;
        this.partner = partner;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<hpPotion>[] getHpPotions() {
        return hpPotions;
    }

    public void setHpPotions(ArrayList<hpPotion>[] hpPotions) {
        this.hpPotions = hpPotions;
    }

    public ArrayList<spPotion>[] getSpPotions() {
        return spPotions;
    }

    public void setSpPotions(ArrayList<spPotion>[] spPotions) {
        this.spPotions = spPotions;
    }

    public ArrayList<fruit>[] getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<fruit>[] fruits) {
        this.fruits = fruits;
    }

    public ArrayList<partner> getPartner() {
        return partner;
    }

    public void setPartner(ArrayList<partner> partner) {
        this.partner = partner;
    }

    public int getShiningStone() {
        return shiningStone;
    }

    public void setShiningStone(int shiningStone) {
        this.shiningStone = shiningStone;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    //여기까지 겟세터.



    public void BuyItem(item item, int number) {
        if (item.getPrice() * number <= this.getMoney()) {
            if (item instanceof hpPotion) {
                hpPotion hpPotion = (hpPotion) item;
                switch (hpPotion.getName()) {
                    case "포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[0].add(hpPotion);
                        }
                        this.setMoney(this.getMoney() - (hpPotion.getPrice()*number));
                        break;
                    case "슈퍼포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[1].add(hpPotion);
                        }
                        this.setMoney(this.getMoney() - (hpPotion.getPrice()*number));
                        break;
                    case "하이퍼포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[2].add(hpPotion);
                        }
                        this.setMoney(this.getMoney() - (hpPotion.getPrice()*number));
                        break;
                    case "맥스포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[3].add(hpPotion);
                        }
                        this.setMoney(this.getMoney() - (hpPotion.getPrice()*number));
                        break;
                }
            } else if (item instanceof spPotion) {
                spPotion spPotion = (spPotion) item;
                switch (spPotion.getName()) {
                    case "sp에이드":
                        for (int i = 0; i < number; i++) {
                            spPotions[0].add(spPotion);
                        }
                        this.setMoney(this.getMoney() - (spPotion.getPrice()*number));
                        break;
                    case "sp이더":
                        for (int i = 0; i < number; i++) {
                            spPotions[1].add(spPotion);
                        }
                        this.setMoney(this.getMoney() - (spPotion.getPrice()*number));
                        break;
                    case "sp엘릭서":
                        for (int i = 0; i < number; i++) {
                            spPotions[2].add(spPotion);
                        }
                        this.setMoney(this.getMoney() - (spPotion.getPrice()*number));
                        break;
                    case "sp맥스":
                        for (int i = 0; i < number; i++) {
                            spPotions[3].add(spPotion);
                        }
                        this.setMoney(this.getMoney() - (spPotion.getPrice()*number));
                        break;
                }
            }
            if (item instanceof fruit) {
                fruit fruit = (fruit) item;
                switch (fruit.getName()) {
                    case "나무열매":
                        for (int i = 0; i < number; i++) {
                            fruits[0].add(fruit);
                        }
                        this.setMoney(this.getMoney() - (fruit.getPrice()*number));
                        break;
                    case "빨간열매":
                        for (int i = 0; i < number; i++) {
                            fruits[1].add(fruit);
                        }
                        this.setMoney(this.getMoney() - (fruit.getPrice()*number));
                        break;
                    case "과사열매":
                        for (int i = 0; i < number; i++) {
                            fruits[2].add(fruit);
                        }
                        this.setMoney(this.getMoney() - (fruit.getPrice()*number));
                        break;
                    case "황금열매":
                        for (int i = 0; i < number; i++) {
                            fruits[3].add(fruit);
                        }
                        this.setMoney(this.getMoney() - (fruit.getPrice()*number));
                        break;
                }

            }
            System.out.println("구매 완료!");
        }
        else {
            System.out.println("돈이 부족해!");
        }
    }

    public void GetItem(item item, int number) {
            if (item instanceof hpPotion) {
                hpPotion hpPotion = (hpPotion) item;
                switch (hpPotion.getName()) {
                    case "포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[0].add(hpPotion);
                        }
                        System.out.println("포션" + number +"개 획득!");
                        break;
                    case "슈퍼포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[1].add(hpPotion);
                        }
                        System.out.println("슈퍼포션" + number +"개 획득!");
                        break;
                    case "하이퍼포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[2].add(hpPotion);
                        }
                        System.out.println("하이퍼포션" + number +"개 획득!");
                        break;
                    case "맥스포션":
                        for (int i = 0; i < number; i++) {
                            hpPotions[3].add(hpPotion);
                        }
                        System.out.println("맥스포션" + number +"개 획득!");
                        break;
                }
            } else if (item instanceof spPotion) {
                spPotion spPotion = (spPotion) item;
                switch (spPotion.getName()) {
                    case "sp에이드":
                        for (int i = 0; i < number; i++) {
                            spPotions[0].add(spPotion);
                        }
                        System.out.println("sp에이드" + number +"개 획득!");
                        break;
                    case "sp이더":
                        for (int i = 0; i < number; i++) {
                            spPotions[1].add(spPotion);
                        }
                        System.out.println("sp이더" + number +"개 획득!");
                        break;
                    case "sp엘릭서":
                        for (int i = 0; i < number; i++) {
                            spPotions[2].add(spPotion);
                        }
                        System.out.println("sp엘릭서" + number +"개 획득!");
                        break;
                    case "sp맥스":
                        for (int i = 0; i < number; i++) {
                            spPotions[3].add(spPotion);
                        }
                        System.out.println("sp맥스" + number +"개 획득!");
                        break;
                }
            }
            if (item instanceof fruit) {
                fruit fruit = (fruit) item;
                switch (fruit.getName()) {
                    case "나무열매":
                        for (int i = 0; i < number; i++) {
                            fruits[0].add(fruit);
                        }
                        System.out.println("나무열매" + number +"개 획득!");
                        break;
                    case "빨간열매":
                        for (int i = 0; i < number; i++) {
                            fruits[1].add(fruit);
                        }
                        System.out.println("빨간열매" + number +"개 획득!");
                        break;
                    case "과사열매":
                        for (int i = 0; i < number; i++) {
                            fruits[2].add(fruit);
                        }
                        System.out.println("과사열매" + number +"개 획득!");
                        break;
                    case "황금열매":
                        for (int i = 0; i < number; i++) {
                            fruits[3].add(fruit);
                        }
                        System.out.println("황금열매" + number +"개 획득!");
                        break;
                }

            }
        }



    public void showItems(){
        System.out.println("빛나는 광석: "+ this.shiningStone +"개");
        System.out.println("광산 입장권: "+ this.ticket +"개");

        if(!this.hpPotions[0].isEmpty()) {
            System.out.println("포션 (" + hpPotions[0].size() + "개)");
        }
        if(!this.hpPotions[1].isEmpty()) {
            System.out.println("슈퍼포션 (" + hpPotions[1].size() + "개)");
        }
        if(!this.hpPotions[2].isEmpty()) {
            System.out.println("하이퍼포션(" + hpPotions[2].size() + "개)");
        }
        if(!this.hpPotions[3].isEmpty()) {
            System.out.println("맥스포션(" + hpPotions[3].size() + "개)");
        }

        if(!this.spPotions[0].isEmpty()) {
            System.out.println("sp에이드(" + spPotions[0].size() + "개)");
        }
        if(!this.spPotions[1].isEmpty()) {
            System.out.println("sp이더(" + spPotions[1].size() + "개)");
        }
        if(!this.spPotions[2].isEmpty()) {
            System.out.println("sp엘릭서(" + spPotions[2].size() + "개)");
        }
        if(!this.spPotions[3].isEmpty()) {
            System.out.println("sp맥스포션(" + spPotions[3].size() + "개)");
        }

        if(!this.fruits[0].isEmpty()) {
            System.out.println("나무열매(" + fruits[0].size() + "개)");
        }
        if(!this.fruits[1].isEmpty()) {
            System.out.println("빨간열매(" + fruits[1].size() + "개)");
        }
        if(!this.fruits[2].isEmpty()) {
            System.out.println("과사열매(" + fruits[2].size() + "개)");
        }
        if(!this.fruits[3].isEmpty()) {
            System.out.println("황금열매(" + fruits[3].size() + "개)");
        }

    }


    public void useHpPotion(hpPotion hpPotion, partner partner){

        if(partner.getHp() + hpPotion.hp >= partner.getMax_hp()) {
            partner.setHp(partner.getMax_hp());
        }
        else{
            partner.setHp(partner.getHp() + hpPotion.hp);
        }
        switch(hpPotion.getName()){
            case "포션" :
                this.hpPotions[0].remove(0);
                break;
            case "슈퍼포션" :
                this.hpPotions[1].remove(0);
                break;
            case "하이퍼포션" :
                this.hpPotions[2].remove(0);
                break;
            case "맥스포션" :
                this.hpPotions[3].remove(0);
                break;
        }
    }


    public void useSpPotion(spPotion spPotion,String cobuff, partner partner){
        Scanner scan = new Scanner(System.in);
        int[] index = new int[spPotion.getNumber()];
        loop1:
        for(int i=0; i < index.length; i++)
        {
            System.out.print("회복할 스킬을" + (index.length - i) + "번 더 입력하세요.");
            for(int j = 0; j < partner.getCoskill().size(); i++) {
                System.out.println((j+1)+". "+partner.getCoskill().get(j).getName());
            }
            int j = scan.nextInt();
            if(cobuff == "co"){
                if(partner.getCoskill().size() <= j){
                    System.out.println("없는 스킬입니다!");
                    i = i-1;
                    continue loop1;
                }
        }
        if(cobuff == "buff"){
            if(partner.getBuff().size() <= j){
                System.out.println("없는 스킬입니다!");
                i = i-1;
                continue loop1;
            }
        }
            index[i] = j;
        }

        switch(spPotion.getName()){
            case "sp에이드" :
                for(int i = 0; i < index.length; i++) {
                    if (cobuff == "co") {
                        partner.getCoskill().get(index[i]).setSp(partner.getCoskill().get(index[i]).getMax_sp());
                    } else if (cobuff == "buff") {
                        partner.getBuff().get(index[i]).setSp(partner.getBuff().get(index[i]).getMax_sp());
                    }
                }
                this.spPotions[0].remove(0);
                break;
            case "sp이더" :
                for(int i = 0; i < index.length; i++) {
                    if (cobuff == "co") {
                        partner.getCoskill().get(index[i]).setSp(partner.getCoskill().get(index[i]).getMax_sp());
                    } else if (cobuff == "buff") {
                        partner.getBuff().get(index[i]).setSp(partner.getBuff().get(index[i]).getMax_sp());
                    }
                }
                this.spPotions[1].remove(0);
                break;
            case "sp엘릭서" :
                for(int i = 0; i < index.length; i++) {
                    if (cobuff == "co") {
                        partner.getCoskill().get(index[i]).setSp(partner.getCoskill().get(index[i]).getMax_sp());
                    } else if (cobuff == "buff") {
                        partner.getBuff().get(index[i]).setSp(partner.getBuff().get(index[i]).getMax_sp());
                    }
                }
                this.spPotions[2].remove(0);
                break;
            case "sp맥스" :
                for(int i = 0; i < index.length; i++) {
                    if (cobuff == "co") {
                        partner.getCoskill().get(index[i]).setSp(partner.getCoskill().get(index[i]).getMax_sp());
                    } else if (cobuff == "buff") {
                        partner.getBuff().get(index[i]).setSp(partner.getBuff().get(index[i]).getMax_sp());
                    }
                }
                this.spPotions[3].remove(0);
                break;
        }

    }
    public void useFruit(fruit fruit, partner partner){
        partner.setHungry(partner.getHungry() - fruit.getHungry());
        if(partner.getHungry() < 0) {
            partner.setHungry(0);
        }


        if(partner.getHp() + fruit.getHp() >= partner.getMax_hp()) {
            partner.setHp(partner.getMax_hp());
        }
        else{
            partner.setHp(partner.getHp() + fruit.getHp());
        }
        switch(fruit.getName()){
            case "나무열매" :
                this.fruits[0].remove(0);
                break;
            case "빨간열매" :
                this.fruits[1].remove(0);
                break;
            case "과사열매" :
                this.fruits[2].remove(0);
                break;
            case "황금열매" :
                this.fruits[3].remove(0);
                break;
        }
    }

}
