package nwu.hzk.tulingrobot;

/**
 * Created by hzk on 2018/3/30.
 */

public class Msg {
    public static final int Type_Received = 0;
    public static final int Type_Sned=1;
    private String content;
    private int type;
    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
