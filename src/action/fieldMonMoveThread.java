package action;

import character.character;

public class fieldMonMoveThread extends Thread {
    character character;
    huntingMap huntingMap;

    public fieldMonMoveThread(huntingMap huntingMap, character character) {
        this.character = character;
        this.huntingMap = huntingMap;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                huntingMap.ShowMap();
                huntingMap.setMapPoint(character.getY(), character.getX(), null);
                if(character.getY()>MoveThread.playerY()) {
                    character.setY(character.getY() - 1);
                }
                else if(character.getY()<MoveThread.playerY()){
                    character.setY(character.getY() + 1);
                }
                else{
                    if(character.getX() > MoveThread.playerX()){
                        character.setX(character.getX()-1);
                    }
                    else if(character.getX() < MoveThread.playerX()){
                        character.setX(character.getX()+1);
                    }
                    else{
                        break;
                    }
                }
                huntingMap.setMapPoint(character.getY(), character.getX(), character);

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
