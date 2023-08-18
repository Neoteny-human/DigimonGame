package character;

public class skill {
    private String name;
    private int max_sp;
    private int sp;

    public skill(String name, int max_sp){
        this.name = name;
        this.max_sp = max_sp;
        this.sp = max_sp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMax_sp() {
        return this.max_sp;
    }

    public void setMax_sp(int sp){
        this.max_sp = sp;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }
}
