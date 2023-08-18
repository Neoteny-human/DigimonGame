package action;

import NPC.chief;
import NPC.part;
import NPC.veter;
import character.buffskill;
import character.character;
import character.coskill;
import character.player;


import java.util.ArrayList;
import java.util.Scanner;

import static action.playtimeThread.playtime;
import static action.shopThread.*;
import static action.shopThread.fruit4;

public class trainingThread extends Thread {
    static player player;
    static huntingMap huntingMap;
    int train;
    Scanner sc = new Scanner(System.in);

    public trainingThread(huntingMap huntingMap, player player) {
        this.huntingMap = huntingMap;
        this.player = player;

    }

    public void run() {
        ArrayList<coskill> firstCo = new ArrayList<>();
        ArrayList<buffskill> firstBuff = new ArrayList<>();
        firstCo.add(new coskill("매지컬 파이어", 10, 1.5, 1));
        firstCo.add(new coskill("쁘띠 썬더", 10, 0.8, 2));
        firstBuff.add(new buffskill("웅크리기", 10, 0, 0, 100));
        chief firstChief = new chief("유아기 소장", "어서오세요. 강소장입니다.", 20, 1.2, firstCo, firstBuff);

        ArrayList<coskill> secondCo = new ArrayList<>();
        ArrayList<buffskill> secondBuff = new ArrayList<>();
        secondCo.add(new coskill("메가 블래스터", 20, 2.0, 1));
        secondCo.add(new coskill("파이어 플랩", 20, 1, 3));
        secondBuff.add(new buffskill("마하 글라이드", 20, 0, 30, 0));
        chief secondChief = new chief("성숙기 소장", "어서오세요. 이소장입니다.", 30, 1.4, secondCo, secondBuff);

        ArrayList<coskill> thirdCo = new ArrayList<>();
        ArrayList<buffskill> thirdBuff = new ArrayList<>();
        thirdCo.add(new coskill("섀도우 윙", 30, 3, 1));
        thirdCo.add(new coskill("페어리 바인", 30, 2, 2));
        thirdBuff.add(new buffskill("템프테이션", 30, 300, 0, 0));
        chief thirdChief = new chief("완전체 소장", "어서오세요. 박소장입니다.", 40, 1.6, thirdCo, thirdBuff);

        ArrayList<coskill> lastCo = new ArrayList<>();
        ArrayList<buffskill> lastBuff = new ArrayList<>();
        lastCo.add(new coskill("손 위프", 40, 6, 1));
        lastCo.add(new coskill("아크틱 블리자드", 40, 3, 2));
        lastBuff.add(new buffskill("버서크 하울", 40, 1000, 0, 0));
        chief lastChief = new chief("궁극체 소장", "어서오세요. 김소장입니다.", 50, 1.8, lastCo, lastBuff);

        //알바 생성!
        part part = new part("강 훈련사", "아 힘들어.", 10, 1.2, 50);

        //수의사 생성!
        veter veter = new veter("오박사", "어서 오너라.", 10);
        loop1:
        while (true) {
            action.huntingMap.ShowMap();
            String c = sc.next();
            switch (c) {
                case "w":
                    if(player.getY() == 1 && player.getX() == 0){
                        train = trainingAction.chiefEnter(sc, firstChief);
                        if (train == 0) {
                            continue loop1;
                        } else if (train == 2) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.skillUp(firstChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("유아기 디지몬이 아니야!");
                                continue loop1;
                            }
                        } else if (train == 1) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.LearnSkill(firstChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("유아기 디지몬이 아니야!");
                                continue loop1;
                            }
                        }
                        continue loop1;
                    }
                    if(player.getY() == 1 && player.getX() == 1) {
                        train = trainingAction.chiefEnter(sc, secondChief);
                        if (train == 0) {
                            continue loop1;
                        } else if (train == 2) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.skillUp(secondChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("성숙기 디지몬이 아니야!");
                                continue loop1;
                            }
                        } else if (train == 1) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.LearnSkill(secondChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("성숙기 디지몬이 아니야!");
                                continue loop1;
                            }
                        }
                        continue loop1;
                    }
                    if(player.getY() == 1 && player.getX() == 2) {
                        train = trainingAction.chiefEnter(sc, thirdChief);
                        if (train == 0) {
                            continue loop1;
                        } else if (train == 2) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.skillUp(thirdChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("완전체 디지몬이 아니야!");
                                continue loop1;
                            }
                        } else if (train == 1) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.LearnSkill(thirdChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("완전체 디지몬이 아니야!");
                                continue loop1;
                            }
                        }
                        continue loop1;
                    }


                    if(player.getY() == 1 && player.getX() == 3) {
                        train = trainingAction.chiefEnter(sc, lastChief);
                        if (train == 0) {
                            continue loop1;
                        } else if (train == 2) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.skillUp(lastChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("궁극체 디지몬이 아니야!");
                                continue loop1;
                            }
                        } else if (train == 1) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            if (player.getPartner().get(train - 1).getGeneration() == 1) {
                                trainingAction.LearnSkill(lastChief, player.getPartner().get(train - 1), player);
                                continue loop1;
                            } else {
                                System.out.println("궁극체 디지몬이 아니야!");
                                continue loop1;
                            }
                        }
                        continue loop1;
                    }
                    if(player.getY() == 1 && player.getX() == 4) {
                        loop3:
                        while (true) {
                            System.out.println("누구를 훈련시킬까?\n" +
                                    "0. 뒤로");
                            for (int i = 0; i < player.getPartner().size(); i++) {
                                System.out.println((i + 1) + ". " + player.getPartner().get(i).getName() +
                                        " 배고픔: " + player.getPartner().get(i).getHungry());
                            }
                            train = sc.nextInt();
                            if (train == 0) {
                                continue loop1;
                            }
                            else if(train <= player.getPartner().size()) {
                                switch (trainingAction.Train(part, player.getPartner().get(train - 1), player)) {
                                    case 0:
                                    case 2:
                                        continue loop1;
                                    case 1:
                                        continue loop3;
                                }
                            }
                            else{
                                continue loop3;
                            }
                        }
                    }

                    if(player.getY() != 1) {
                        huntingMap.setMapPoint(player.getY(), player.getX(), null);
                        player.setY(player.getY() - 1);
                    }
                    break;


                case "s":
                    if(player.getY() == huntingMap.getMapY()-1 && player.getX() == 3){
                        break loop1;
                    }
                    if(player.getY() != huntingMap.getMapY()-1) {
                        huntingMap.setMapPoint(player.getY(), player.getX(), null);
                        player.setY(player.getY() + 1);
                    }
                    break;
                case "a":
                    if(player.getX() != 0) {
                        huntingMap.setMapPoint(player.getY(), player.getX(), null);
                        player.setX(player.getX() - 1);
                    }
                    break;
                case "d":
                    if(player.getX() != huntingMap.getMapX()-1) {
                        huntingMap.setMapPoint(player.getY(), player.getX(), null);
                        player.setX(player.getX() + 1);
                    }
                    break;
                case "i":
                    showInfo();
                    break;
                case "end":
                    gameEnd();
                    continue loop1;
            }
            huntingMap.setMapPoint(player.getY(), player.getX(), player);


        }
    }

    public static int playerX() {
        return player.getX();
    }

    public static int playerY() {
        return player.getY();
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

