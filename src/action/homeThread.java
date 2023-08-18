package action;

import character.player;

import java.util.Scanner;

public class homeThread extends Thread {
    private static player player;
    private static huntingMap huntingMap;
    private static int choice;//어느 맵으로 갔는지 알려주는 변수.
    Scanner sc = new Scanner(System.in);

    public homeThread(huntingMap huntingMap, player player) {
        this.huntingMap = huntingMap;
        this.player = player;
    }

    public void run() {
        loop1:
        while (true) {
            action.huntingMap.ShowMap();
            String c = sc.next();
            switch (c) {
                case "w":
                    if(player.getY() == 0 && player.getX() == 1){
                        this.setChoice(2);
                        break loop1;
                    }
                    else if(player.getY() == 0 && player.getX() == 3){
                        this.setChoice(6);
                        break loop1;
                    }
                    else {
                        if (player.getY() != 0) {
                            huntingMap.setMapPoint(player.getY(), player.getX(), null);
                            player.setY(player.getY() - 1);
                        }
                    }
                    break;
                case "s":
                    if(player.getY() == huntingMap.getMapY()-1 && player.getX() == 1){
                        this.setChoice(3);
                        break loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY()-1 && player.getX() == 3){
                        this.setChoice(4);
                        break loop1;
                    }
                    else if(player.getY() == huntingMap.getMapY()-2 && player.getX() == huntingMap.getMapX()-1){
                        buyTicket();
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
                    if(player.getX() != 0) {
                        huntingMap.setMapPoint(player.getY(), player.getX(), null);
                        player.setX(player.getX() - 1);
                    }
                    break;
                case "d":
                    if(player.getY() == 1 && player.getX() == huntingMap.getMapX()-1){
                        this.setChoice(1);
                        break loop1;
                    }
                    else if(player.getY() == 3 && player.getX() == huntingMap.getMapX()-1){
                        if(player.getTicket()>0) {
                            player.setTicket(player.getTicket()-1);
                            this.setChoice(7);
                            break loop1;
                        }
                        else{
                            System.out.println("입장권이 없어!");
                            continue loop1;
                        }
                    }
                    else if(player.getY() == huntingMap.getMapY()-1 && player.getX() == huntingMap.getMapX()-2){
                        buyTicket();
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
                    this.setChoice(5);
                    break loop1;
                case "end":
                    this.setChoice(0);
                    break loop1;
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

    public static int getChoice() {
        return choice;
    }

    public static void setChoice(int choice) {
        homeThread.choice = choice;
    }

    public void buyTicket() {
        loop:
        while(true) {
            System.out.println("광산 입장권을 구매하시겠습니까?\n" +
                    "1. 예\n" +
                    "2. 아니요\n");
            int count = sc.nextInt();
            if(count == 1){
                System.out.println("몇 장 구입하시겠습니까?\n" +
                        "판매가격: 50(골드/장)");
                count = sc.nextInt();
                if(count*50<=player.getMoney()){
                    player.setMoney(player.getMoney()-(count*50));
                    player.setTicket(player.getTicket()+count);
                    System.out.println("입장권 "+count+"장 구매완료!");
                    break loop;
                }
                else{
                    System.out.println("돈이 부족합니다!");
                    continue loop;
                }
            }
            else{
                break loop;
            }
        }
    }
}


