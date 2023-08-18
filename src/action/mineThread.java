package action;

import character.character;
import character.player;

import javax.swing.*;
import java.util.Scanner;

import static action.playtimeThread.playtime;

//광산 쓰레드
public class mineThread extends JFrame implements Runnable {
    private JPanel panel;
    private player player;
    private huntingMap huntingMap;
    private mineTimer mineTimer;
    private Thread threadNum;

    Scanner sc = new Scanner(System.in);

    public mineThread(huntingMap huntingMap, player player) {
        this.player = player;
        this.huntingMap = huntingMap;


    }

    public void run(){
        panel = new JPanel();
        panel.setLayout(null);
        int second = 20;


        mineTimer = new mineTimer(second);
        threadNum = new Thread(mineTimer);
        threadNum.start();
        panel.add(mineTimer);

        add(panel);
        setTitle("타이머");
        setSize(470, 300);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loop1:
        while (true) {
            if(mineTimer.mineSecond == 0){
                threadNum.interrupt();
                dispose();
                break loop1;
            }
            action.huntingMap.ShowMap();
            Scanner sc = new Scanner(System.in);
            String c = sc.next();
            switch (c) {
                case "w":
                    if(player.getY() == 3 && player.getX() == 2){
                        //광석캐기
                        dig();
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
                    if(player.getY() == 1 && player.getX() == 2){
                        //광석캐기
                        dig();
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
                    if(player.getY() == 2 && player.getX() == 3){
                        //광석캐기
                        dig();
                        continue loop1;
                    }
                    else if(player.getY() == 1 && player.getX() == 0){
                        threadNum.interrupt();
                        dispose();
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
                    if(player.getY() == 2 && player.getX() == 1){
                        //광석캐기
                        dig();
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
    public void dig(){
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
        if(timingThread.isSuccess()) {
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
            if(timingThread.isSuccess()) {
                System.out.println("빛나는 돌 1개 획득!");
                player.setShiningStone(player.getShiningStone() + 1);
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
            System.out.println("1. 파트너 상태창\n" + "2. 파트너 방생\n" + "0. 뒤로");
            itemOption = sc.nextInt();
            if (itemOption == 1) {
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

            } else if (itemOption == 2) {
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
