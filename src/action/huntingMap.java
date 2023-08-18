package action;

import character.character;

import java.util.Random;

public class huntingMap {
    private static character[][] map;
    private static int mapNum; // 1번 필드, 2번 필드, 3: 마을, 4: 훈련소, 5: 병원, 6: 상점.
    private character character;
    private static int mapX;
    private static int mapY;






    public huntingMap(int mapX, int mapY, character character ,int mapNum){
        this.character = character;
        this.mapNum = mapNum;
        this.mapX = mapX;
        this.mapY = mapY;
        character[][] array1 = new character[mapY][mapX];
        Random rand = new Random();
        this.getCharacter().setY(rand.nextInt(mapY-4)+2);
        this.getCharacter().setX(rand.nextInt(mapX));
        array1[this.getCharacter().getY()][this.getCharacter().getX()] = this.getCharacter();
        setMap(array1);
    }
    public huntingMap(int mapX, int mapY ,int mapNum){
        this.mapNum = mapNum;
        this.mapX = mapX;
        this.mapY = mapY;
        character[][] array1 = new character[mapY][mapX];
        setMap(array1);
    }


    public static void ShowMap() {
        if (mapNum == 1) {
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*깊은 숲*\n");
            System.out.println("    마을로");
            System.out.println("     ↑↓  ");
            for (int i = 0; i < map[0].length - 1; i++) {
                if (i != 1) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        System.out.print("    ");
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                if (i != 3) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("**\n             ↑↓  ");
            System.out.println("            다음맵");
        }
        else if(mapNum == 2){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*더 깊은 숲*\n");
            System.out.println("    이전맵");
            System.out.println("     ↑↓  ");
            for (int i = 0; i < map[0].length - 1; i++) {
                if (i != 1) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        System.out.print("    ");
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                    System.out.print("****");
            }
            System.out.println("**\n");
//            System.out.println("**\n             ↑  ");
//            System.out.println("            빛나는 돌");
        }
        else if(mapNum == 3){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*마을*\n");
            System.out.println("    훈련소    병원");
            System.out.println("     ↑↓      ↑↓");
            for (int i = 0; i < map[0].length - 1; i++) {
                if (i != 1 && i != 3) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        if(x == 4 && y == 4){
                            System.out.print("관리인");
                        }
                        else {
                            System.out.print("    ");
                        }
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {}
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        if(y==1) {
                            System.out.println("  <=> 상점");
                        }
                        else if(y == 3){
                            System.out.println("  <=> 광산");
                        }
                        else{
                            System.out.println("**");
                        }
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                if (i != 1 && i != 3) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("**\n     ↑↓      ↑↓     ");
            System.out.println("    필드로    스토리     \n\n" +
                    "※ 상태창은 i를 누르세요.\n" +
                    "※ 게임 종료: end 입력");
        }


        else if(mapNum == 4){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*훈련소*\n");
            for (int i = 0; i < map[0].length - 1; i++) {
                    System.out.print("****");
            }
            System.out.print("**");
            System.out.println("****");
            System.out.println("유아  성숙 완전  궁극 훈련**");
            for (int y = 1; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        System.out.print("    ");
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                if (i != 3) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("**\n             ↑↓  ");
            System.out.println("            마을로 \n\n" +
                    "※ 상태창은 i를 누르세요.\n※ 게임 종료: end 입력");
        }
        else if(mapNum == 5){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*병원*\n");
            for (int i = 0; i < map[0].length - 1; i++) {
                System.out.print("****");
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        if( x==2 && y==1){
                            System.out.print("오박사");
                        }
                        else {
                            System.out.print("    ");
                        }
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                if (i != 1) {
                    System.out.print("****");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("**\n     ↑↓  ");
            System.out.println("    마을로" +
                    "\n\n※ 상태창은 i를 누르세요.\n※ 게임 종료: end 입력");
        }
        else if(mapNum == 6){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*상점*\n");
            for (int i = 0; i < map[0].length - 1; i++) {
                System.out.print("****");
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        if( x==0 && y==0){
                            System.out.print("열매상");
                        }
                        else if( x==4 && y==0 ){
                            System.out.print("포션상");
                        }
                        else if( x==4 && y==4 ){
                            System.out.print("sp상 ");
                        }
                        else if( x==0 && y==4 ){
                            System.out.print("매입상");
                        }
                        else if( x==0 && y==2){
                            System.out.print("_   ");
                        }
                        else if(x==0 && y==3){
                            System.out.print("_◀마을");
                        }
                        else {
                            System.out.print("    ");
                        }
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                System.out.print("****");
            }
            System.out.println("**");
            System.out.println("\n\n※ 상태창은 i를 누르세요.\n※ 게임 종료: end 입력");
        }
        else if(mapNum == 7){
            System.out.println("\n\n\n\n\n\n\n\n\n" +
                    "*광산*\n");
            for (int i = 0; i < map[0].length - 1; i++) {
                System.out.print("****");
            }
            System.out.print("**");
            System.out.println("****");
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    character c = map[y][x];
                    if (c == null) {
                        if( x==2 && y==2 ){
                            System.out.print(" 금광석");
                        }
                        else if( x==0 && y==0){
                            System.out.print("_   ");
                        }
                        else if(x==0 && y==1){
                            System.out.print("_◀마을");
                        }
                        else {
                            System.out.print("    ");
                        }
                    } else {
                        char[] A = new char[3];
                        try {
                            for (int i = 0; i < 3; i++) {
                                A[i] = c.getName().charAt(i);
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                        String B = String.valueOf(A);
                        System.out.print(B);
                    }
                    if (x == map[y].length - 1) {
                        System.out.println("**");
                    }
                }
            }
            for (int i = 0; i < map[0].length; i++) {
                System.out.print("****");
            }
            System.out.println("**");
            System.out.println("\n\n※ 상태창은 i를 누르세요.\n※ 게임 종료: end 입력");
        }
    }

    public character[][] getMap() {
        return map;
    }

    public void setMap(character[][] map) {
        this.map = map;
    }
    public static void setMapPoint(int i, int j, character character){
        map[i][j] = character;
    }

    public character getCharacter() {
        return character;
    }

    public void setCharacter(character character) {
        this.character = character;
    }

    public static int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public static int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }


    public static void setMapClear(int x, int y){
        huntingMap.map = new character[y][x];
    }
}
