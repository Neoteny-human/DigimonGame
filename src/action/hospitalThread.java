package action;

import NPC.veter;
import character.character;
import character.partner;
import character.player;

import java.util.Scanner;

import static action.playtimeThread.playtime;
import static action.shopThread.*;

public class hospitalThread extends Thread {
    private player player;
    private veter veter;
    Scanner sc = new Scanner(System.in);
    partner[] hosPartners = new partner[3];


    public hospitalThread(player player, veter veter, partner[] hosPartners) {
        this.player = player;
        this.veter = veter;
        this.hosPartners = hosPartners;
    }

    public void run() {
        loop1:
        while (true) {
            action.huntingMap.ShowMap();
            Scanner sc = new Scanner(System.in);
            String c = sc.next();
            switch (c) {
                case "w":
                    if (player.getY() == 2 && player.getX() == 2) {
                        heal(player, veter);
                        continue loop1;
                    } else {
                        if (player.getY() != 0) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setY(player.getY() - 1);
                        }
                    }
                    break;
                case "s":
                    if (player.getY() == 0 && player.getX() == 2) {
                        heal(player, veter);
                        continue loop1;
                    } else if (player.getY() == huntingMap.getMapY() - 1 && player.getX() == 1) {
                        break loop1;
                    } else {
                        if (player.getY() != huntingMap.getMapY() - 1) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setY(player.getY() + 1);
                        }
                    }
                    break;
                case "a":
                    if (player.getY() == 1 && player.getX() == 3) {
                        heal(player, veter);
                        continue loop1;
                    } else {
                        if (player.getX() != 0) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setX(player.getX() - 1);
                        }
                    }
                    break;
                case "d":
                    if (player.getY() == 1 && player.getX() == 1) {
                        heal(player, veter);
                        continue loop1;
                    } else {
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

    public void heal(player player, veter veter) {

        loop:
        while (true) {
            System.out.println(veter.getName() + ": " + veter.getLine());
            System.out.println("1. 디지몬을 데리러 왔다.\n" +
                    "2. 디지몬을 회복시킨다.\n" +
                    "0. 뒤로.");
            int partnerOption = sc.nextInt();
            if(partnerOption == 2) {
                if (player.getMoney() >= veter.getMoney()) {
                    if (hosPartners[0] == null || hosPartners[1] == null || hosPartners[2] == null) {
                        System.out.println("누구를 회복시킬까?\n" +
                                "0. 뒤로");
                        for (int i = 0; i < player.getPartner().size(); i++) {
                            System.out.println((i + 1) + ". " + player.getPartner().get(i).getName() +
                                    " (HP: " + (int) player.getPartner().get(i).getHp() + "/" + (int) player.getPartner().get(i).getMax_hp() +
                                    ", 배고픔: " + player.getPartner().get(i).getHungry() + ")");
                        }
                        partnerOption = sc.nextInt();
                        if (partnerOption == 0) {
                            continue loop;
                        } else {
                            player.getPartner().get(partnerOption - 1).setHp(player.getPartner().get(partnerOption - 1).getMax_hp());
                            //풀피로
                            player.getPartner().get(partnerOption - 1).setHungry(0);
                            //배고픔 0으로
                            for (int i = 0; i < player.getPartner().get(partnerOption - 1).getCoskill().size(); i++) {
                                player.getPartner().get(partnerOption - 1).getCoskill().get(i).setSp(player.getPartner().get(partnerOption - 1).getCoskill().get(i).getMax_sp());
                            }
                            //공격스킬 SP회복
                            for (int i = 0; i < player.getPartner().get(partnerOption - 1).getBuff().size(); i++) {
                                player.getPartner().get(partnerOption - 1).getBuff().get(i).setSp(player.getPartner().get(partnerOption - 1).getBuff().get(i).getMax_sp());
                            }
                            //패시브스킬 SP회복
                            System.out.println("회복 완");

                            //맡겨지기.
                            if (hosPartners[0] == null) {
                                hosPartners[0] = player.getPartner().get(partnerOption - 1);
                                player.getPartner().remove(partnerOption - 1);
                                new timerThread(30).start();

                            } else if (hosPartners[1] == null) {
                                hosPartners[1] = player.getPartner().get(partnerOption - 1);
                                player.getPartner().remove(partnerOption - 1);
                                new timerThread1(30).start();
                            } else {
                                hosPartners[2] = player.getPartner().get(partnerOption - 1);
                                player.getPartner().remove(partnerOption - 1);
                                new timerThread2(30).start();
                            }
                            break loop;
                        }
                    } else {
                        System.out.println("현재 빈 회복실이 없어!");
                        continue loop;
                    }
                } else {
                    System.out.println("돈이 부족해!");
                    continue loop;
                }
            }
            else if (partnerOption == 1) {
                System.out.println("어떤 디지몬을 찾을 거야?");
                for (int i = 0; i < 3; i++) {
                    if (hosPartners[i] != null) {
                        System.out.println((i + 1) + ". " + hosPartners[i].getName());
                    }
                }
                System.out.println("0. 뒤로");

                partnerOption = sc.nextInt();
                switch (partnerOption) {

                    case 1:
                        if (timerThread.getSecond() == 0) {
                            System.out.println(hosPartners[0].getName() + "을 찾았어");
                            player.getPartner().add(hosPartners[0]);
                            hosPartners[0] = null;
                        } else {
                            System.out.println(hosPartners[0].getName() + "은 아직 회복이 " + timerThread.getSecond() + "초 남았어.");
                        }
                        break;
                    case 2:
                        if (timerThread1.getSecond() == 0) {
                            System.out.println(hosPartners[1].getName() + "을 찾았어");
                            player.getPartner().add(hosPartners[1]);
                            hosPartners[1] = null;
                        } else {
                            System.out.println(hosPartners[1].getName() + "은 아직 회복이 " + timerThread1.getSecond() + "초 남았어.");
                        }
                        break;
                    case 3:
                        if (timerThread2.getSecond() == 0) {
                            System.out.println(hosPartners[2].getName() + "을 찾았어");
                            player.getPartner().add(hosPartners[2]);
                            hosPartners[2] = null;
                        } else {
                            System.out.println(hosPartners[2].getName() + "은 아직 회복이 " + timerThread2.getSecond() + "초 남았어.");
                        }
                        break;
                    default:
                        continue loop;

                }

            }
            else{
                break loop;
            }
        }
    }


    public void showInfo() {
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
                if (partnerOption <= 0 || partnerOption > player.getPartner().size()) {
                    continue loop3;
                } else {
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

    public void gameEnd() {
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
