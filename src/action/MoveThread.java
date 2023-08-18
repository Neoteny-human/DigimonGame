package action;

import character.character;

import java.util.Scanner;

public class MoveThread extends Thread {
    static character character;
    static huntingMap huntingMap;
    static boolean goback;//다음맵으로 가는지 이전 맵으로 가는지 알려주는 변수. (go = true or back = false)

    public MoveThread(huntingMap huntingMap, character character) {
        this.huntingMap = huntingMap;
        this.character = character;
    }

    public void run() {
        loop1:
        while (true) {
            Scanner sc = new Scanner(System.in);

            String c = sc.next();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
            switch (c) {
                case "w":

                    if(character.getY() == 0 && character.getX() == 1){
                        this.setGoback(false);
                        break loop1;
                    }
                    if(character.getY() != 0) {
                        huntingMap.setMapPoint(character.getY(), character.getX(), null);
                        character.setY(character.getY() - 1);
                    }
                    break;
                case "s":
                    if(character.getY() == huntingMap.getMapY()-1 && character.getX() == 3){
                        this.setGoback(true);
                        break loop1;
                    }
                    if(character.getY() != huntingMap.getMapY()-1) {
                        huntingMap.setMapPoint(character.getY(), character.getX(), null);
                        character.setY(character.getY() + 1);
                    }
                    break;
                case "a":
                    if(character.getX() != 0) {
                        huntingMap.setMapPoint(character.getY(), character.getX(), null);
                        character.setX(character.getX() - 1);
                    }
                    break;
                case "d":
                    if(character.getX() != huntingMap.getMapX()-1) {
                        huntingMap.setMapPoint(character.getY(), character.getX(), null);
                        character.setX(character.getX() + 1);
                    }
                    break;
            }
            huntingMap.setMapPoint(character.getY(), character.getX(), character);


        }
    }

    public static int playerX() {
        return character.getX();
    }

    public static int playerY() {
        return character.getY();
    }

    public static boolean isGoback() {
        return goback;
    }

    public static void setGoback(boolean goback) {
        MoveThread.goback = goback;
    }
}


