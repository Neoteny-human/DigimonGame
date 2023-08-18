package action;

import NPC.merchant.fruitMerchant;
import NPC.merchant.hpMerchant;
import NPC.merchant.spMerchant;
import character.character;
import character.player;
import item.fruit;
import item.hpPotion;
import item.spPotion;

import java.util.Scanner;

import static action.playtimeThread.playtime;

public class shopThread extends Thread{
    private final player player;
    private final hpMerchant hpMerchant;
    private final spMerchant spMerchant;
    private final fruitMerchant fruitMerchant;
    private huntingMap huntingMap;


    private int shopOption;

    public static hpPotion hpPotion1 = new hpPotion("포션", 10, 20);
    public static hpPotion hpPotion2 = new hpPotion("슈퍼포션", 15, 50);
    public static hpPotion hpPotion3 = new hpPotion("하이퍼포션", 30, 200);
    public static hpPotion hpPotion4 = new hpPotion("맥스포션", 50, 1000);
    public static spPotion spPotion1 = new spPotion("sp에이드", 10, 1);
    public static spPotion spPotion2 = new spPotion("sp이더", 15, 2);
    public static spPotion spPotion3 = new spPotion("sp엘릭서", 30, 3);
    public static spPotion spPotion4 = new spPotion("sp맥스", 50, 4);
    public static fruit fruit1 = new fruit("나무열매", 10, 50, 10);
    public static fruit fruit2 = new fruit("빨간열매", 15, 50, 20);
    public static fruit fruit3 = new fruit("과사열매", 20, 50, 100);
    public static fruit fruit4 = new fruit("황금열매", 30, 100, 200);

    public static fruit fruit = new fruit("기적의 열매", 200, 100, 1000);

    Scanner sc = new Scanner(System.in);

    public shopThread(huntingMap huntingMap, player player, hpMerchant hpMerchant, spMerchant spMerchant, fruitMerchant fruitMerchant){
        this.huntingMap = huntingMap;
        this.player = player;
        this.hpMerchant = hpMerchant;
        this.spMerchant = spMerchant;
        this.fruitMerchant = fruitMerchant;
    }
    public void run(){
        loop1:
        while (true) {
            action.huntingMap.ShowMap();
            Scanner sc = new Scanner(System.in);
            String c = sc.next();
            switch (c) {
                case "w":
                    if(player.getY() == 1 && player.getX() == 0){
                        buyFruit(player, fruitMerchant);
                        continue loop1;
                    }
                    else if(player.getY() == 1 && player.getX() == huntingMap.getMapX()-1){
                        buyHp(player, hpMerchant);
                        continue loop1;
                    }
                    else {
                        if (player.getY() != 0) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setY(player.getY() - 1);
                        }
                    }
                    break;
                case "s":
                    if(player.getY() == huntingMap.getMapY()-2 && player.getX() == 0){
                        sellItem();
                        continue loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY()-2 && player.getX() == huntingMap.getMapX()-1){
                        buySp(player, spMerchant);
                        continue loop1;
                    }
                    else {
                        if (player.getY() != huntingMap.getMapY() - 1) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setY(player.getY() + 1);
                        }
                    }
                    break;
                case "a":
                    if(player.getY() == 0 && player.getX() == 1){
                        buyFruit(player,fruitMerchant);
                        continue loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY() - 1 && player.getX() == 1){
                        sellItem();
                        continue loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY()-2 && player.getX() == 0){
                        break loop1;
                    }
                    else {
                        if (player.getX() != 0) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setX(player.getX() - 1);
                        }
                    }
                    break;
                case "d":
                    if(player.getY() == 0 && player.getX() == huntingMap.getMapX()-2){
                        buyHp(player, hpMerchant);
                        continue loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY()-1 && player.getX() == huntingMap.getMapX()-2){
                        buySp(player, spMerchant);
                        continue loop1;
                    }
                    else {
                        if (player.getX() != huntingMap.getMapX() - 1) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setX(player.getX() + 1);
                        }
                    }
                    break;
                case "i":
                    showInfo();
                    continue loop1;

                case "end":
                    gameEnd();
                    continue loop1;
            }
            huntingMap.setMapPoint(player.getY(), player.getX(), player);


        }
    }
    public void sellItem(){
        loop3:
        while(true) {
            int count;
            System.out.println("어떤 아이템을 판매하시겠습니까?\n" +
                    "1. 포션\n" +
                    "2. 에이드\n" +
                    "3. 열매\n" +
                    "4. 빛나는 돌\n" +
                    "0. 뒤로");
            shopOption = sc.nextInt();
            switch (shopOption) {
                case 0:
                    break loop3;
                case 1:
                    for (int l = 0; l < 4; l++) {
                        if (!player.getHpPotions()[l].isEmpty()) {
                            System.out.println((l + 1) + ". " + player.getHpPotions()[l].get(0).getName() +
                                    " ("+player.getHpPotions()[l].size()+"개)"+
                                    " 판매가격: " + (player.getHpPotions()[l].get(0).getPrice() / 2));
                        }
                    }
                    System.out.println("0. 뒤로");
                    shopOption = sc.nextInt();
                    if(shopOption == 0){
                        continue loop3;
                    }
                    System.out.println("몇 개를 판매하시겠습니까?");
                    count = sc.nextInt();
                    if (player.getHpPotions()[shopOption-1].size() >= count) {
                        player.setMoney(player.getHpPotions()[shopOption-1].get(0).getPrice() * count / 2 + player.getMoney());
                        for (int l = 0; l < count; l++) {
                            player.getHpPotions()[shopOption-1].remove(0);
                        }
                        break loop3;
                    } else {
                        System.out.println("보유 개수가 부족합니다!");
                        continue loop3;
                    }
                case 2:
                    for (int l = 0; l < 4; l++) {
                        if (!player.getSpPotions()[l].isEmpty()) {
                            System.out.println((l + 1) + ". " + player.getSpPotions()[l].get(0).getName() +
                                    " ("+player.getSpPotions()[l].size()+"개)"+
                                    " 판매가격: " + (player.getSpPotions()[l].get(0).getPrice() / 2));
                        }
                    }
                    System.out.println("0. 뒤로");
                    shopOption = sc.nextInt();
                    if(shopOption == 0){
                        continue loop3;
                    }
                    System.out.println("몇 개를 판매하시겠습니까?");
                    count = sc.nextInt();
                    if (player.getSpPotions()[shopOption-1].size() >= count) {
                        player.setMoney(player.getSpPotions()[shopOption-1].get(0).getPrice() * count / 2 + player.getMoney());
                        for (int l = 0; l < count; l++) {
                            player.getSpPotions()[shopOption-1].remove(0);
                        }
                        break loop3;
                    } else {
                        System.out.println("보유 개수가 부족합니다!");
                        continue loop3;
                    }
                case 3:
                    for (int l = 0; l < 4; l++) {
                        if (!player.getFruits()[l].isEmpty()) {
                            System.out.println((l + 1) + ". " + player.getFruits()[l].get(0).getName() +
                                    " ("+player.getFruits()[l].size()+"개)"+
                                    " 판매가격: " + (player.getFruits()[l].get(0).getPrice() / 2));
                        }
                    }
                    System.out.println("0. 뒤로");
                    shopOption = sc.nextInt();
                    if(shopOption == 0){
                        continue loop3;
                    }
                    System.out.println("몇 개를 판매하시겠습니까?");
                    count = sc.nextInt();
                    if (player.getFruits()[shopOption-1].size() >= count) {
                        player.setMoney(player.getFruits()[shopOption-1].get(0).getPrice() * count / 2 + player.getMoney());
                        for (int l = 0; l < count; l++) {
                            player.getFruits()[shopOption-1].remove(0);
                        }
                        break loop3;
                    } else {
                        System.out.println("보유 개수가 부족합니다!");
                        continue loop3;
                    }
                case 4:
                    loop:
                    while(true) {
                        System.out.println("보유한 빛나는 돌 개수: " + player.getShiningStone() + "개\n판매가격: 50(골드/개)");
                        System.out.println("판매할 개수를 입력해주세요.\n" +
                                "0.뒤로");
                        int number = sc.nextInt();
                        if (number == 0){
                            continue loop3;
                        }
                        else if (number > player.getShiningStone()) {
                            System.out.println("개수가 부족해!");
                            continue loop;
                        } else {
                            player.setShiningStone(player.getShiningStone() - number);
                            player.setMoney(number * 50 + player.getMoney());
                            System.out.println("빛나는 돌" + number + "개 판매완료!");
                            continue loop3;
                        }
                    }
            }
        }
    }




    public void buyHp(player player, hpMerchant hpMerchant){
        loop2:
        while(true) {
            System.out.println(hpMerchant.getName() + ": " + hpMerchant.getLine());
            System.out.println("어떤 포션을 구입하시겠습니까?");
            hpMerchant.Show();
            shopOption = sc.nextInt();
            if (shopOption > character.getChapter()) {
                System.out.println("아직 없는 아이템 입니다.");
                continue loop2;
            } else {
                if (shopOption != 0) {
                    System.out.println("몇 개 구입하시겠습니까?");
                    int count = sc.nextInt();
                    switch (shopOption) {
                        case 1:
                            player.BuyItem(hpMerchant.getHpPotions()[4].get(0), count);
                            break;
                        case 2:
                            player.BuyItem(hpMerchant.getHpPotions()[4].get(1), count);
                            break;
                        case 3:
                            player.BuyItem(hpMerchant.getHpPotions()[4].get(2), count);
                            break;
                        case 4:
                            player.BuyItem(hpMerchant.getHpPotions()[4].get(3), count);
                            break;
                    }
                }
                break loop2;
            }
        }
    }

    public void buySp(player player, spMerchant spMerchant){
        loop2:
        while(true) {
            System.out.println(spMerchant.getName() + ": " + spMerchant.getLine());
            System.out.println("어떤 에이드를 구입하시겠습니까?");
            spMerchant.Show();
            shopOption = sc.nextInt();
            if (shopOption > character.getChapter()) {
                System.out.println("아직 없는 아이템 입니다.");
                continue loop2;
            } else {
                if (shopOption != 0) {
                    System.out.println("몇 개 구입하시겠습니까?");
                    int count = sc.nextInt();
                    switch (shopOption) {
                        case 1:
                            player.BuyItem(spMerchant.getSpPotions()[4].get(0), count);
                            break;
                        case 2:
                            player.BuyItem(spMerchant.getSpPotions()[4].get(1), count);
                            break;
                        case 3:
                            player.BuyItem(spMerchant.getSpPotions()[4].get(2), count);
                            break;
                        case 4:
                            player.BuyItem(spMerchant.getSpPotions()[4].get(3), count);
                            break;
                    }
                }
                break loop2;
            }
        }
    }

    public void buyFruit(player player, fruitMerchant fruitMerchant) {
        loop2:
        while (true) {
            System.out.println(fruitMerchant.getName() + ": " + fruitMerchant.getLine());
            System.out.println("어떤 열매를 구입하시겠습니까?");
            fruitMerchant.Show();
            shopOption = sc.nextInt();
            if (shopOption > character.getChapter()) {
                System.out.println("아직 없는 아이템 입니다.");
                continue loop2;
            } else {
                if (shopOption != 0) {
                    System.out.println("몇 개 구입하시겠습니까?");
                    int count = sc.nextInt();
                    switch (shopOption) {
                        case 1:
                            player.BuyItem(fruitMerchant.getFruits()[4].get(0), count);
                            break;
                        case 2:
                            player.BuyItem(fruitMerchant.getFruits()[4].get(1), count);
                            break;
                        case 3:
                            player.BuyItem(fruitMerchant.getFruits()[4].get(2), count);
                            break;
                        case 4:
                            player.BuyItem(fruitMerchant.getFruits()[4].get(3), count);
                            break;
                    }
                }
                break loop2;
            }
        }
    }

    public void showInfo(){
        int itemOption;
        int partnerOption;
        loop3:
        while (true) {
            switch (character.getChapter()) {
                case 1:
                    System.out.println("1챕터: 파일섬 편");
                    break;
                case 2:
                    System.out.println("2챕터: 서버대륙 편");
                    break;
                case 3:
                    System.out.println("3챕터: 현실세계 편");
                    break;
                case 4:
                    System.out.println("4챕터: 어둠의 사천왕 편");
                    break;
            }
            System.out.println("이름: " + player.getName() + "\n금화: " + player.getMoney() + "개");
            System.out.println("인벤토리 :");
            player.showItems();
            System.out.println("1. 아이템 사용\n" + "2. 파트너 상태창\n" + "3. 파트너 방생\n" + "0. 뒤로");
            itemOption = sc.nextInt();
            if (itemOption == 1) {
                System.out.println("어떤 아이템을 사용할까요?\n1. 포션\n2. 에이드\n3. 열매\n0. 뒤로");
                itemOption = sc.nextInt();
                switch (itemOption) {
                    case 1:
                        System.out.println("어떤 포션을 사용할까요?");
                        System.out.println("1. 포션 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "hp " + hpPotion1.getHp() + "회복)");
                        System.out.println("2. 슈퍼포션 " + player.getHpPotions()[1].size() + "개 " +
                                "(" + "hp " + hpPotion2.getHp() + "회복)");
                        System.out.println("3. 하이퍼포션 " + player.getHpPotions()[2].size() + "개 " +
                                "(" + "hp " + hpPotion3.getHp() + "회복)");
                        System.out.println("4. 맥스포션 " + player.getHpPotions()[3].size() + "개 " +
                                "(" + "hp " + hpPotion4.getHp() + "회복)");
                        itemOption = sc.nextInt();
                        switch (itemOption) {
                            case 1:
                                if (!player.getHpPotions()[0].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useHpPotion(player.getHpPotions()[0].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 2:
                                if (!player.getHpPotions()[1].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useHpPotion(player.getHpPotions()[1].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 3:
                                if (!player.getHpPotions()[2].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useHpPotion(player.getHpPotions()[2].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 4:
                                if (!player.getHpPotions()[3].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useHpPotion(player.getHpPotions()[3].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("어떤 에이드를 사용할까요?");
                        System.out.println("1. sp에이드 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "스킬 " + spPotion1.getNumber() + "개의 sp를 회복)");
                        System.out.println("2. sp이더 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "스킬 " + spPotion2.getNumber() + "개의 sp를 회복)");
                        System.out.println("3. sp엘릭서 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "스킬 " + spPotion3.getNumber() + "개의 sp를 회복)");
                        System.out.println("4. sp맥스 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "스킬 " + spPotion4.getNumber() + "개의 sp를 회복)");
                        itemOption = sc.nextInt();

                        switch (itemOption) {
                            case 1:
                                if (!player.getSpPotions()[0].isEmpty()) {
                                    System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                    itemOption = sc.nextInt();
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();

                                    if (itemOption == 1) {
                                        player.useSpPotion(player.getSpPotions()[0].get(0), "co", player.getPartner().get(partnerOption - 1));
                                    } else {
                                        player.useSpPotion(player.getSpPotions()[0].get(0), "buff", player.getPartner().get(partnerOption - 1));
                                    }
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 2:
                                if (!player.getSpPotions()[1].isEmpty()) {
                                    System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                    itemOption = sc.nextInt();
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();

                                    if (itemOption == 1) {
                                        player.useSpPotion(player.getSpPotions()[1].get(0), "co", player.getPartner().get(partnerOption - 1));
                                    } else {
                                        player.useSpPotion(player.getSpPotions()[1].get(0), "buff", player.getPartner().get(partnerOption - 1));
                                    }
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 3:
                                if (!player.getSpPotions()[2].isEmpty()) {
                                    System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                    itemOption = sc.nextInt();
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    if (itemOption == 1) {
                                        player.useSpPotion(player.getSpPotions()[2].get(0), "co", player.getPartner().get(partnerOption - 1));
                                    } else {
                                        player.useSpPotion(player.getSpPotions()[2].get(0), "buff", player.getPartner().get(partnerOption - 1));
                                    }
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                            case 4:
                                if (!player.getSpPotions()[3].isEmpty()) {
                                    System.out.println("어느 스킬에 사용할까? 1. 액티브, 2 버프");
                                    itemOption = sc.nextInt();
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    if (itemOption == 1) {
                                        player.useSpPotion(player.getSpPotions()[3].get(0), "co", player.getPartner().get(partnerOption - 1));
                                    } else {
                                        player.useSpPotion(player.getSpPotions()[3].get(0), "buff", player.getPartner().get(partnerOption - 1));
                                    }
                                } else {
                                    System.out.println("사용할 포션이 없어!");
                                    continue loop3;
                                }
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("어떤 열매를 사용할까요?");
                        System.out.println("1. 나무열매 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "배고픔 " + fruit1 + "회복, hp " + fruit1 + "회복");
                        System.out.println("2. 빨간열매 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "배고픔 " + fruit2 + "회복, hp " + fruit2 + "회복");
                        System.out.println("3. 과사열매 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "배고픔 " + fruit3 + "회복, hp " + fruit3 + "회복");
                        System.out.println("4. 황금열매 " + player.getHpPotions()[0].size() + "개 " +
                                "(" + "배고픔 " + fruit4 + "회복, hp " + fruit4 + "회복");
                        itemOption = sc.nextInt();
                        switch (itemOption) {
                            case 1:
                                if (!player.getFruits()[0].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useFruit(player.getFruits()[0].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 열매가 없어!");
                                    continue loop3;
                                }
                                break;
                            case 2:
                                if (!player.getFruits()[1].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useFruit(player.getFruits()[1].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 열매가 없어!");
                                    continue loop3;
                                }
                                break;
                            case 3:
                                if (!player.getFruits()[2].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useFruit(player.getFruits()[2].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 열매가 없어!");
                                    continue loop3;
                                }
                                break;
                            case 4:
                                if (!player.getFruits()[3].isEmpty()) {
                                    System.out.println("어느 디지몬에게 사용할까?");
                                    for (int i = 0; i < player.getPartner().size(); i++) {
                                        if (player.getPartner().get(i).getHp() > 0) {
                                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                                        }
                                    }
                                    partnerOption = sc.nextInt();
                                    player.useFruit(player.getFruits()[3].get(0), player.getPartner().get(partnerOption - 1));
                                } else {
                                    System.out.println("사용할 열매가 없어!");
                                    continue loop3;
                                }
                                break;
                        }
                        break;
                    default:
                        continue loop3;
                }

            } else if (itemOption == 2) {
                for (int i = 0; i < player.getPartner().size(); i++) {
                    if (player.getPartner().get(i).getHp() > 0) {
                        System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                    }
                }
                System.out.println("0. 뒤로");
                partnerOption = sc.nextInt();
                if(partnerOption <=0 || partnerOption > player.getPartner().size()){
                    continue loop3;
                }
                else {
                    int i;
                    System.out.println(player.getPartner().get(partnerOption - 1).getName() + "\n체력: " + (int) player.getPartner().get(partnerOption - 1).getHp() +
                            "/" + (int) player.getPartner().get(partnerOption - 1).getMax_hp() +
                            "\n파워: " + (int) player.getPartner().get(partnerOption - 1).getPower() + "\n배고픔: " +
                            player.getPartner().get(partnerOption - 1).getHungry() + "\n스킬: ");
                    for (i = 0; i < player.getPartner().get(partnerOption - 1).getCoskill().size(); i++) {
                        System.out.println((i + 1) + ". " + player.getPartner().get(partnerOption - 1).getCoskill().get(i).getName());
                    }
                    System.out.println("버프: ");
                    for (int j = i; j < player.getPartner().get(partnerOption - 1).getBuff().size() + i; j++) {
                        System.out.println((i + 1) + ". " + player.getPartner().get(partnerOption - 1).getBuff().get(j - i).getName());
                    }
                    System.out.print("세대: ");
                    switch (player.getPartner().get(partnerOption - 1).getGeneration()) {
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
                    System.out.println("타입: " + player.getPartner().get(partnerOption - 1).getType());
                    System.out.println("0.뒤로");
                    itemOption = sc.nextInt();
                    if (itemOption == 0) {
                        continue loop3;
                    } else if (itemOption <= i) {
                        System.out.println("스킬이름: " + player.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getName()
                                + " 스킬계수: " + player.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getCoefficient() +
                                " 공격횟수: " + player.getPartner().get(partnerOption - 1).getCoskill().get(itemOption - 1).getTimes() + "번");
                    } else
                        System.out.println("버프이름: " + player.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getName() +
                                " 힐: " + player.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getHeal() +
                                " 파워업: " + (int) player.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getPower_up() +
                                " 쉴드: " + player.getPartner().get(partnerOption - 1).getBuff().get(itemOption - i - 1).getShield());
                }

            } else if (itemOption == 3) {
                if (player.getPartner().size() > 1) {
                    System.out.println("방생할 디지몬을 선택하세요.");
                    for (int i = 0; i < player.getPartner().size(); i++) {
                        if (player.getPartner().get(i).getHp() > 0) {
                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                        }
                    }
                    itemOption = sc.nextInt();
                    player.getPartner().remove(itemOption - 1);
                } else {
                    System.out.println("파트너가 하나밖에 없어!");
                }
            } else {
                break;
            }
        }
    }

    public void gameEnd(){
        int end;
        System.out.println("정말 게임을 종료하시겠습니까?\n" +
                "1. 예\n2. 아니요");
        end = sc.nextInt();
        if (end == 1) {
            System.out.println("플레이타임: "+playtime/3600+ "시간 "+playtime/60+"분 " +playtime%60+"초");
            System.out.println("진행 챕터: 챕터 "+character.getChapter());
            System.exit(0);
        }
    }
}
