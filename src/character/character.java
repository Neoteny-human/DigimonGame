package character;

public class character {
    private static int chapter;
    private String name;
    private int X;
    private int Y;

    public character() {
    }
    public character(String name){
        this.name = name;
    }

    public static int getChapter() {
        return chapter;
    }

    public static void setChapter(int chapter) {
        character.chapter = chapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}

