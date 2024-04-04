package UI.Show.four;

public class Msg {
    public static final int me = 0;
    public static final int other = 1;
    private  String meassage;
    private int type;

    public Msg(String meassage, int type) {
        this.meassage = meassage;
        this.type = type;
    }

    public String getMeassage() {
        return meassage;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
