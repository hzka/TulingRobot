package nwu.hzk.tulingrobot;

/**
 * Created by HZK on 2017/7/25.
 * 定义是实体类，作为ListViw的适配类型
 */
public class chatting {
    private String chatting1;
    private int imageId;
    public chatting(String chatting1,int imageId){
        chatting1=this.chatting1;
        imageId=this.imageId;
    }
    public String getChatting1(){
        return chatting1;
    }
    public int getImageId(){
        return imageId;
    }
}
