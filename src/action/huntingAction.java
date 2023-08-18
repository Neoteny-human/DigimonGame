package action;

import character.fieldmon;
import character.partner;
import character.player;
import item.fruit;
import item.hpPotion;
import item.spPotion;

import java.util.Random;
import java.util.Scanner;

import static action.MoveThread.character;
import static action.shopThread.*;


public class huntingAction {
public static int meet(fieldmon fieldmon, player player) {

        boolean canFight = false;

    System.out.println("야생의 \"" + fieldmon.getName() + "\" 이 나타났다!");
    System.out.println("체력: " + (int) fieldmon.getHp() + "\n파워: " + (int) fieldmon.getPower() +
            "\n스킬: ");
    for (int i = 0; i < fieldmon.getCoskill().size(); i++) {
        System.out.println((i + 1) + ". " + fieldmon.getCoskill().get(i).getName() +
                " (" + fieldmon.getCoskill().get(i).getCoefficient() + ")" + "X" + fieldmon.getCoskill().get(i).getTimes() +
                "번 공격");
    }
    System.out.println("버프스킬: ");
    for (int i = 0; i < fieldmon.getBuff().size(); i++) {
        System.out.println((i + 1) + ". " + fieldmon.getBuff().get(i).getName() + " (힐: " + fieldmon.getBuff().get(i).getHeal() +
                ", 파워업: " + fieldmon.getBuff().get(i).getPower_up() + ", 쉴드: " + fieldmon.getBuff().get(i).getShield() + ") ");
    }
    System.out.print("세대: ");
    switch (character.getChapter()) {
        case 1:
            System.out.println("성장기");
            break;
        case 2:
            System.out.println("성숙기");
            break;
        case 3:
            System.out.println("완전체");
            break;
        case 4:
            System.out.println("궁극체");
            break;
    }
    System.out.println("타입: " + fieldmon.getType() + "\n@@@\n@@@");
    loop2:
    while (true) {
        for (int i = 0; i < player.getPartner().size(); i++) {
            if (player.getPartner().get(i).getHp() > 0 && player.getPartner().get(i).getHungry() <= 90) {
                canFight = true;
            }
        }
        if (canFight == true) {
            System.out.println("내보낼 디지몬을 선택하세요!");
            for (int i = 0; i < player.getPartner().size(); i++) {
                if (player.getPartner().get(i).getHp() > 0 && player.getPartner().get(i).getHungry() <= 90) {
                    System.out.println((i + 1) + ". " + player.getPartner().get(i).getName() +
                            " (배고픔 수치: " + player.getPartner().get(i).getHungry() + ")");
                }
            }
        } else {
            System.out.println("더 이상 싸울 수 있는 디지몬이 없어!");
            for(int i = 5; i > 0; i--){
                try {
                    System.out.println(i+"초 후 돌아갑니다.");
                    Thread.sleep(1000);
                }
                catch (Exception e){}
            }
            return 0;
        }
        Scanner sc = new Scanner(System.in);
        int fightOption = sc.nextInt();

        // 전투에 돌입한 디지몬은 배고픔수치가 10증가.
        player.getPartner().get(fightOption - 1).setHungry(player.getPartner().get(fightOption - 1).getHungry() + 10);
        canFight = false;


        int hp = huntingAction.fight(player, player.getPartner().get(fightOption - 1), fieldmon);
        if (hp == 0) {
            System.out.println("패배하였습니다!");
            continue loop2;
        } else if (hp == 2) {
            for(int i = 5; i > 0; i--){
                try {
                    System.out.println(i+"초 후 돌아갑니다.");
                    Thread.sleep(1000);
                }
                catch (Exception e){}
            }
            return 2;
        } else {
            System.out.println("이겼다!");
            player.setMoney(player.getMoney() + fieldmon.getMoney());
            player.getPartner().get(fightOption - 1).setExp(player.getPartner().get(fightOption - 1).getExp() +
                    fieldmon.getExp());
            //경험치와 돈을 얻는다.
            for (int i = 0; i <= 10; i++) {
                player.getPartner().get(fightOption - 1).levelUp();
                player.getPartner().get(fightOption - 1).upgrade();
            }
            //경험치 얻을 때마다 진화조건에 도달했는지 체크.

            // 3까지 랜덤을 돌린 후 나온 아이템 종류 한 개를 가져간다.(챕터마다 드랍 아이템이 발전)
            Random rand = new Random();
            int ran = rand.nextInt(3);
            int ranNum = rand.nextInt(character.getChapter()) + 1;
            switch (ran) {
                case 0:
                    if (character.getChapter() == 1) {
                        player.GetItem(hpPotion1, ranNum);
                    } else if (character.getChapter() == 2) {
                        player.GetItem(hpPotion2, ranNum);
                    } else if (character.getChapter() == 3) {
                        player.GetItem(hpPotion3, ranNum);
                    } else if (character.getChapter() == 4) {
                        player.GetItem(hpPotion4, ranNum);
                    }
                    break;
                case 1:
                    if (character.getChapter() == 1) {
                        player.GetItem(spPotion1, ranNum);
                    } else if (character.getChapter() == 2) {
                        player.GetItem(spPotion2, ranNum);
                    } else if (character.getChapter() == 3) {
                        player.GetItem(spPotion3, ranNum);
                    } else if (character.getChapter() == 4) {
                        player.GetItem(spPotion4, ranNum);
                    }
                    break;
                case 2:
                    if (character.getChapter() == 1) {
                        player.GetItem(fruit1, ranNum);
                    } else if (character.getChapter() == 2) {
                        player.GetItem(fruit2, ranNum);
                    } else if (character.getChapter() == 3) {
                        player.GetItem(fruit3, ranNum);
                    } else if (character.getChapter() == 4) {
                        player.GetItem(fruit4, ranNum);
                    }
                    break;
            }
            for(int i = 5; i > 0; i--){
                try {
                    System.out.println(i+"초 후 돌아갑니다.");
                    Thread.sleep(1000);
                }
                catch (Exception e){}
            }
            return 1;
        }
    }
}


    public static int fight(player player, partner partner, fieldmon fieldmon) {
        hpPotion hpPotion1 = new hpPotion("포션", 10, 20);
        hpPotion hpPotion2 = new hpPotion("슈퍼포션", 15, 50);
        hpPotion hpPotion3 = new hpPotion("하이퍼포션", 30, 200);
        hpPotion hpPotion4 = new hpPotion("맥스포션", 50, 1000);
        spPotion spPotion1 = new spPotion("sp에이드", 10, 1);
        spPotion spPotion2 = new spPotion("sp이더", 15, 2);
        spPotion spPotion3 = new spPotion("sp엘릭서", 30, 3);
        spPotion spPotion4 = new spPotion("sp맥스", 50, 4);
        item.fruit fruit1 = new fruit("나무열매", 10, 50, 10);
        fruit fruit2 = new fruit("빨간열매", 15, 50, 20);
        fruit fruit3 = new fruit("과사열매", 20, 50, 100);
        fruit fruit4 = new fruit("황금열매", 30, 100, 200);
        fruit fruit = new fruit("기적의 열매", 200, 100,1000);
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int attackOption;
        int itemOption;
        int skillOption;
        double plusPower = 0;
        double plusPower2 = 0;
        loop1:
        while(true) {
            System.out.println("0.도망가기");
            System.out.println("1.attack");
            System.out.println("2.아이템");
            System.out.println("3.버프스킬");


                    for(int i = 0; i < partner.getCoskill().size(); i++) {
                        System.out.println((i+4)+"." + partner.getCoskill().get(i).getName() +
                                " (" + partner.getCoskill().get(i).getCoefficient() + ")" +
                                "X" + partner.getCoskill().get(i).getTimes() + "번 공격   *남은 SP: " +
                                partner.getCoskill().get(i).getSp() + "/" +
                                partner.getCoskill().get(i).getMax_sp());
                    }
                    attackOption = sc.nextInt();
                    switch (attackOption){
                        case 0 :
                            int random = rand.nextInt(2);
                            if (random == 0){
                                System.out.println("도망에 실패했다!");
                            }
                            else{
                                System.out.println("무사히 도망쳤다!");
                                partner.setShield(0);
                                fieldmon.setShield(0);
                                partner.setPower(partner.getPower()-plusPower);
                                fieldmon.setPower(partner.getPower()-plusPower2);
                                fieldmon.setHp(fieldmon.getMax_hp());
                                return 2;
                            }
                            break;
                        case 1 :
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
                            //Timing Thread complete.
                            try{timingThread.join();}
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if(!timingThread.isSuccess()) {
                                if (partner.Attack(fieldmon) > fieldmon.getShield()) {
                                    fieldmon.setHp(fieldmon.getHp() + fieldmon.getShield() - partner.Attack(fieldmon));
                                } else {
                                    fieldmon.setShield(fieldmon.getShield() - partner.Attack(fieldmon));
                                }
                                System.out.println((int) partner.Attack(fieldmon) + "의 데미지를 입혔다!");
                            }
                            else{
                                System.out.println("공격의 효과가 굉장했다!! (데미지 X1.2배)");
                                if (partner.Attack(fieldmon)*1.2 > fieldmon.getShield()) {
                                    fieldmon.setHp(fieldmon.getHp() + fieldmon.getShield() - (partner.Attack(fieldmon)*1.2));
                                } else {
                                    fieldmon.setShield(fieldmon.getShield() - (partner.Attack(fieldmon)*1.2));
                                }
                                System.out.println((int)(partner.Attack(fieldmon)*1.2) + "의 데미지를 입혔다!");
                            }
                            break;
                        case 2 :
                            System.out.println("어떤 아이템을 사용할까요?\n1. 포션\n2. 에이드\n 3.열매");
                            itemOption = sc.nextInt();
                            switch(itemOption) {
                                case 1:
                                    System.out.println("어떤 포션을 사용할까요?");
                                    System.out.println("1. 포션 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"hp "+hpPotion1.getHp()+"회복)");
                                    System.out.println("2. 슈퍼포션 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"hp "+hpPotion2.getHp()+"회복)");
                                    System.out.println("3. 하이퍼포션 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"hp "+hpPotion3.getHp()+"회복)");
                                    System.out.println("4. 맥스포션 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"hp "+hpPotion4.getHp()+"회복)");
                                    itemOption = sc.nextInt();
                                    switch (itemOption) {
                                        case 1:
                                            if (!player.getHpPotions()[0].isEmpty()) {
                                                player.useHpPotion(player.getHpPotions()[0].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 2:
                                            if (!player.getHpPotions()[1].isEmpty()) {
                                                player.useHpPotion(player.getHpPotions()[1].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 3:
                                            if (!player.getHpPotions()[2].isEmpty()) {
                                                player.useHpPotion(player.getHpPotions()[2].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 4:
                                            if (!player.getHpPotions()[3].isEmpty()) {
                                                player.useHpPotion(player.getHpPotions()[3].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                    }
                                    break;
                                case 2:
                                    System.out.println("어떤 에이드를 사용할까요?");
                                    System.out.println("1. sp에이드 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"스킬 "+spPotion1.getNumber()+"개의 sp를 회복)");
                                    System.out.println("2. sp이더 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"스킬 "+spPotion2.getNumber()+"개의 sp를 회복)");
                                    System.out.println("3. sp엘릭서 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"스킬 "+spPotion3.getNumber()+"개의 sp를 회복)");
                                    System.out.println("4. sp맥스 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"스킬 "+spPotion4.getNumber()+"개의 sp를 회복)");
                                    itemOption = sc.nextInt();
                                    switch (itemOption) {
                                        case 1:
                                            if (!player.getSpPotions()[0].isEmpty()) {
                                                System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                                itemOption = sc.nextInt();
                                                if(itemOption == 1) {
                                                    player.useSpPotion(player.getSpPotions()[0].get(0), "co", partner);
                                                }
                                                else{
                                                    player.useSpPotion(player.getSpPotions()[0].get(0), "buff", partner);
                                                }
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 2:
                                            if (!player.getSpPotions()[1].isEmpty()) {
                                                System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                                itemOption = sc.nextInt();
                                                if(itemOption == 1) {
                                                    player.useSpPotion(player.getSpPotions()[1].get(0), "co", partner);
                                                }
                                                else{
                                                    player.useSpPotion(player.getSpPotions()[1].get(0), "buff", partner);
                                                }
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 3:
                                            if (!player.getSpPotions()[2].isEmpty()) {
                                                System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                                itemOption = sc.nextInt();
                                                if(itemOption == 1) {
                                                    player.useSpPotion(player.getSpPotions()[2].get(0), "co", partner);
                                                }
                                                else{
                                                    player.useSpPotion(player.getSpPotions()[2].get(0), "buff", partner);
                                                }
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 4:
                                            if (!player.getSpPotions()[3].isEmpty()) {
                                                System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                                itemOption = sc.nextInt();
                                                if(itemOption == 1) {
                                                    player.useSpPotion(player.getSpPotions()[3].get(0), "co", partner);
                                                }
                                                else{
                                                    player.useSpPotion(player.getSpPotions()[3].get(0), "buff", partner);
                                                }
                                            }
                                            else{
                                                System.out.println("사용할 포션이 없어!");
                                                continue loop1;
                                            }
                                            break;
                                    }
                                    break;
                                case 3:
                                    System.out.println("어떤 열매를 사용할까요?");
                                    System.out.println("1. 나무열매 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"배고픔 "+fruit1+"회복, hp "+fruit1+"회복");
                                    System.out.println("2. 빨간열매 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"배고픔 "+fruit2+"회복, hp "+fruit2+"회복");
                                    System.out.println("3. 과사열매 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"배고픔 "+fruit3+"회복, hp "+fruit3+"회복");
                                    System.out.println("4. 황금열매 " + player.getHpPotions()[0].size() + "개 "+
                                            "("+"배고픔 "+fruit4+"회복, hp "+fruit4+"회복");

                                    itemOption = sc.nextInt();
                                    switch (itemOption) {
                                        case 1:
                                            if (!player.getFruits()[0].isEmpty()) {
                                                player.useFruit(player.getFruits()[0].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 열매가 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 2:
                                            if (!player.getFruits()[1].isEmpty()) {
                                                player.useFruit(player.getFruits()[1].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 열매가 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 3:
                                            if (!player.getFruits()[2].isEmpty()) {
                                                player.useFruit(player.getFruits()[2].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 열매가 없어!");
                                                continue loop1;
                                            }
                                            break;
                                        case 4:
                                            if (!player.getFruits()[3].isEmpty()) {
                                                player.useFruit(player.getFruits()[3].get(0), partner);
                                            }
                                            else{
                                                System.out.println("사용할 열매가 없어!");
                                                continue loop1;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("어떤 버프를 쓸까?\n" +
                                    "0. 뒤로");
                            for(int i = 0; i < partner.getBuff().size(); i++) {
                                System.out.println((i + 1) +". " + partner.getBuff().get(i).getName() + " (힐: " + partner.getBuff().get(i).getHeal() +
                                        ", 파워업: " + partner.getBuff().get(i).getPower_up() + ", 쉴드: " + partner.getBuff().get(i).getShield() + ") " +
                                        "*남은 SP: "+partner.getBuff().get(i).getSp()+"/"+partner.getBuff().get(i).getMax_sp());
                            }
                            skillOption = sc.nextInt();
                            if(skillOption == 0){
                                continue loop1;
                            }
                            else {
                                if (partner.getBuff().get(skillOption - 1).getSp() > 0) {
                                    partner.useBuff(partner.getBuff().get(skillOption - 1));
                                    plusPower += partner.getBuff().get(skillOption - 1).getPower_up();
                                    partner.getBuff().get(skillOption - 1).setSp(partner.getBuff().get(skillOption - 1).getSp() - 1);
                                    break;
                                } else {
                                    System.out.println("sp포인트가 부족해!");
                                    continue loop1;
                                }
                            }


                        default:
                            if(partner.getCoskill().get(attackOption-4).getSp()>0) {
                                //Timing Thread!!
                                timingThread timingThread1 = new timingThread();
                                enterThread enterThread1 = new enterThread();
                                System.out.println("Enter↲ 키를 누르세요");
                                enterThread1.start();
                                timingThread1.start();
                                while(true) {
                                    if (!enterThread1.isAlive()) {
                                        timingThread1.interrupt();
                                        break;
                                    }
                                }
                                //Timing Thread complete.
                                try{timingThread1.join();}
                                catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if(!timingThread1.isSuccess()) {
                                    if (partner.coskillAttack(fieldmon, attackOption - 4) > fieldmon.getShield()) {
                                        fieldmon.setHp(fieldmon.getHp() + fieldmon.getShield() - partner.coskillAttack(fieldmon, attackOption - 4));
                                    } else {
                                        fieldmon.setShield(fieldmon.getShield() - partner.coskillAttack(fieldmon, attackOption - 4));
                                    }
                                    System.out.println((int) partner.coskillAttack(fieldmon, attackOption - 4) + "의 데미지를 입혔다!");
                                }
                                else{
                                    System.out.println(partner.getCoskill().get(attackOption-4).getName()+"의 효과가 굉장했다!!");
                                    if (partner.coskillAttack(fieldmon, attackOption - 4) * 1.2 > fieldmon.getShield()) {
                                        fieldmon.setHp(fieldmon.getHp() + fieldmon.getShield() - (partner.coskillAttack(fieldmon, attackOption - 4)*1.2));
                                    } else {
                                        fieldmon.setShield(fieldmon.getShield() - (partner.coskillAttack(fieldmon, attackOption - 4) *1.2));
                                    }
                                    System.out.println((int)(partner.coskillAttack(fieldmon, attackOption - 4)*1.2) + "의 데미지를 입혔다!");
                                }


                                //sp 마이너스 1
                                partner.getCoskill().get(attackOption-4).setSp(partner.getCoskill().get(attackOption-4).getSp() - 1);
                                break;
                            }
                            else{
                                System.out.println("sp포인트가 부족해!");
                                continue loop1;
                            }
                    }
            System.out.println("내 체력: " + (int)partner.getHp() + "(" + (int)partner.getShield()+")");
            System.out.println("내 파워: " + (int)partner.getPower());
            System.out.println("적 체력: " + (int)fieldmon.getHp() + "(" + (int)fieldmon.getShield()+")");
            System.out.println("적 파워: " + (int)fieldmon.getPower());

            

            if (fieldmon.getHp() <= 0) {
                partner.setShield(0);
                fieldmon.setShield(0);
                partner.setPower(partner.getPower()-plusPower);
                fieldmon.setPower(partner.getPower()-plusPower2);
                fieldmon.setHp(fieldmon.getMax_hp());
                return 1;
            }
            int ran = rand.nextInt(100);
            ran %= (fieldmon.getCoskill().size() + 2);
            // 스킬개수+2로 나눈 나머지가 스킬개수로 나오면 그냥 공격, 나머지가 최대(스킬개수+1)로 나오면 랜덤 버프, 숫자가 나오면 그 번째의 액티브스킬로 공격.
            if (ran == fieldmon.getCoskill().size()) {
                if(partner.getShield()>fieldmon.Attack(partner)){
                    partner.setShield(partner.getShield()-fieldmon.Attack(partner));
                }
                //쉴드보다 데미지가 낮으면 쉴드만 까인다.
                else {
                    partner.setHp(partner.getHp()+partner.getShield()
                            - fieldmon.Attack(partner));
                }
                System.out.println("적이 공격해 " +(int)fieldmon.Attack(partner) + "의 데미지를 입었다!");

            }
            else if(ran == fieldmon.getCoskill().size()+1){
                int ra = rand.nextInt(fieldmon.getBuff().size());
                fieldmon.useBuff(fieldmon.getBuff().get(ra));
                //랜덤으로 버프사용.
                plusPower2 += fieldmon.getBuff().get(ra).getPower_up();
            }
            else {
                partner.setHp(partner.getHp()
                        - fieldmon.coskillAttack(partner, ran));
                System.out.println("적에게 " + fieldmon.getCoskill().get(ran).getName()+"(으)로 " +(int)fieldmon.coskillAttack(partner, ran) +
                        "의 데미지를 입었다!");
            }
            System.out.println("내 체력: " + (int)partner.getHp() + "(" + (int)partner.getShield()+")");
            System.out.println("내 파워: " + (int)partner.getPower());
            System.out.println("적 체력: " + (int)fieldmon.getHp() + "(" + (int)fieldmon.getShield()+")");
            System.out.println("적 파워: " + (int)fieldmon.getPower());
            if (partner.getHp() <= 0) {
                partner.setShield(0);
                fieldmon.setShield(0);
                partner.setPower(partner.getPower()-plusPower);
                fieldmon.setPower(partner.getPower()-plusPower2);
                fieldmon.setHp(fieldmon.getMax_hp());
                return 0;
            }
        }
    }
}
