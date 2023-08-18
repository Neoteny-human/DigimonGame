package action;

import NPC.chief;
import NPC.part;
import character.partner;
import character.player;

import java.util.Scanner;

public class trainingAction {

    Scanner sc = new Scanner(System.in);
    public static int timing;

    public static int enter(Scanner sc) {
        System.out.println("누구한테 가볼까?\n" +
                "1. 유아기 소장\n" +
                "2. 성숙기 소장\n" +
                "3. 완전체 소장\n" +
                "4. 궁극체 소장\n" +
                "5. 훈련사\n" +
                "0. 뒤로");
        return sc.nextInt();
    }

    public static int chiefEnter(Scanner sc, chief chief) {
        System.out.println(chief.getName() + ": " + chief.getLine());
        System.out.println("1. 스킬을 배운다.\n" +
                "2. 스킬계수를 강화한다.\n" +
                "0. 뒤로");
        return sc.nextInt();
    }

    public static void skillUp(chief chief, partner partner, player player) {
        if (player.getMoney() >= chief.getMoney()) {
            Scanner sc = new Scanner(System.in);
            int skill;
            int i;
            int j;
            System.out.println("어떤 스킬을 강화할래?");
            for (i = 0; i < partner.getCoskill().size(); i++) {
                System.out.println((i + 1) + ". " + partner.getCoskill().get(i).getName());
            }
            //1번부터 액티브스킬
            for (j = i; j < partner.getBuff().size() + i; j++) {
                System.out.println((j+1) + ". " + partner.getBuff().get(j - i).getName());
            }
            //액티브스킬 끝난 번호부터 패시브스킬
            skill = sc.nextInt();
            if (skill <= i) {
                partner.getCoskill().get(skill - 1).setCoefficient(partner.getCoskill().get(skill - 1).getCoefficient() * chief.getGrowth());
                player.setMoney(player.getMoney() - chief.getMoney());
                System.out.println("강화가 완료되었어!");
            } else if (skill <= j) {
                partner.getBuff().get(skill - i - 1).setShield(partner.getBuff().get(skill - i - 1).getShield() * chief.getGrowth());
                partner.getBuff().get(skill - i - 1).setPower_up(partner.getBuff().get(skill - i - 1).getPower_up() * chief.getGrowth());
                partner.getBuff().get(skill - i - 1).setHeal(partner.getBuff().get(skill - i - 1).getHeal() * chief.getGrowth());
                player.setMoney(player.getMoney() - chief.getMoney());
                System.out.println("강화가 완료되었어!");
            } else {
                System.out.println("그건 없는 스킬이야.");
            }

        } else {
            System.out.println("돈이 부족해!");
        }
    }

    public static void LearnSkill(chief chief, partner partner, player player) {
        if (player.getMoney() >= chief.getMoney()) {
            Scanner sc = new Scanner(System.in);
            int skill;
            int i;
            int j;
            boolean isHave = false;
            System.out.println("어떤 스킬을 배울래?");
            for (i = 0; i < chief.getCoskills().size(); i++) {
                System.out.println((i + 1) + ". " + chief.getCoskills().get(i).getName());
            }
            //1번부터 액티브스킬
            for (j = i; j < chief.getBuffskills().size() + i; j++) {
                System.out.println((j+1) + ". " + chief.getBuffskills().get(j - i).getName());
            }
            //액티브스킬 끝난 번호부터 패시브스킬
            skill = sc.nextInt();
            if (skill <= i) {
                for(int k = 0; k < partner.getCoskill().size(); k++){
                    if(partner.getCoskill().get(k).getName() == chief.getCoskills().get(skill-1).getName()){
                        isHave = true;
                    }
                }
                if(!isHave) {
                    partner.getCoskill().add(chief.getCoskills().get(skill - 1));
                    player.setMoney(player.getMoney() - chief.getMoney());
                    System.out.println("스킬을 배웠어!");
                }
                else{
                    System.out.println("스킬이 이미 있어!");
                }
            } else if (skill <= j) {
                for(int k = 0; k < partner.getBuff().size(); k++){
                    if(partner.getBuff().get(k).getName() == chief.getBuffskills().get(skill-i-1).getName()){
                        isHave = true;
                    }
                }
                if(!isHave) {
                    partner.getBuff().add(chief.getBuffskills().get(skill - i - 1));
                    player.setMoney(player.getMoney() - chief.getMoney());
                    System.out.println("스킬을 배웠어!");
                }
                else{
                    System.out.println("스킬이 이미 있어!");
                }
            } else {
                System.out.println("그건 존재하지 않는 스킬이야");
            }

        } else {
            System.out.println("돈이 부족해!");
        }
    }
    public static int Train(part part, partner partner, player player){
        if(player.getMoney()>= part.getMoney()){
            if(partner.getHungry()<=90) {

                //Timing Thread!!
                timingThread timingThread = new timingThread();
                enterThread enterThread = new enterThread();
                System.out.println("Enter↲ 키를 누르세요");
                enterThread.start();
                timingThread.start();
                while(true) {
                    if (!enterThread.isAlive()) {
                        timingThread.interrupt();
                        break;
                    }
                }
                try{timingThread.join();}
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //Timing Thread complete.
                if(!timingThread.isSuccess()) {
                    System.out.println("파워: " + (int) (partner.getPower()) +
                            "+" + (int) (partner.getPower()*part.getGrowth() - (partner.getPower())) +
                            "\nHP: " + (int) (partner.getMax_hp()) +
                            "+" + (int) (partner.getMax_hp()*part.getGrowth() - (partner.getMax_hp())) +
                            "\n경험치: +" + (int) (partner.getLevel() * part.getExp()));

                    partner.setMax_hp(partner.getMax_hp() * part.getGrowth());
                    partner.setHp(partner.getMax_hp());
                    partner.setPower(partner.getPower() * part.getGrowth());
                    partner.setHungry(partner.getHungry() + 10);
                    partner.setExp(partner.getLevel() * part.getExp());

                }
                else{
                    System.out.println("훈련의 효과가 굉장했다! (성장률 X1.2배)");
                    System.out.println("파워: " + (int) (partner.getPower()) +
                            "+" + (int) (partner.getPower()*part.getGrowth()*1.2 - (partner.getPower())) +
                            "\nHP: " + (int) (partner.getMax_hp()) +
                            "+" + (int) (partner.getMax_hp()*part.getGrowth()*1.2 - (partner.getMax_hp())) +
                            "\n경험치: +" + (int) (partner.getLevel() * part.getExp()*1.2));
                    partner.setMax_hp(partner.getMax_hp() * part.getGrowth()*1.2);
                    partner.setHp(partner.getMax_hp());
                    partner.setPower(partner.getPower() * part.getGrowth()*1.2);
                    partner.setHungry(partner.getHungry() + 10);
                    partner.setExp((int)(partner.getLevel()*part.getExp()*1.2));
                }

                for (int i = 0; i < 5; i++) {
                    partner.levelUp();
                    partner.upgrade();
                }
                //레벨업, 업그레이드 확인
                for(int i = 5; i > 0; i--){
                    try {
                        System.out.println(i+"초 후 돌아갑니다.");
                        Thread.sleep(1000);
                    }
                    catch (Exception e){}
                }
                return 0;
            }
            else{
                System.out.println("배고파서 훈련을 할 수 없어!");
                return 1;
            }
        }
        else{
            System.out.println("돈이 부족해!");
            return 2;
        }
    }

}
