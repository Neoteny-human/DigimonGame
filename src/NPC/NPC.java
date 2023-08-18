package NPC;

import character.*;

public class NPC extends character{
    private String line;
    public NPC(String name, String line){
        super(name);
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}

