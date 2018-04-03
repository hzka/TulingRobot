package nwu.hzk.tulingrobot;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.jar.JarInputStream;

/**
 * Created by HZK on 2017/7/21.
 */

public class JasonResolve {
    private String jason1;
    public String keyResult="";
    private TextView tvRight;
    public JasonResolve(String jason1){
       this.jason1=jason1;
    }

    public String jasonReturn(){
        try {
            JSONObject jo1=new JSONObject(jason1);//创建json对象。
            keyResult=jo1.getString("text");//输出其中的text段，getString("text");
            System.out.println(keyResult);
        } catch (JSONException e) {
            e.printStackTrace();
    }
        return keyResult;
    }
}
