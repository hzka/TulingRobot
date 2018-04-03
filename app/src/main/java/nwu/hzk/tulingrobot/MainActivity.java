package nwu.hzk.tulingrobot;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpDataInter{

    private ListView lv1;
    private EditText ed1;
//    private TextView tvLeft,tvRight;
    private Button sendbtn1;
    private String url,content1;
    private List<Msg>msgList=new ArrayList<>();
    private chattingAdapter chattingAdapter01;
    private RecyclerView msgrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url="http://www.tuling123.com/openapi/api?key=be464f4e51fc4f9a8c962c60c0a6bfe1&info=";

        ed1= (EditText) findViewById(R.id.edit_text01);
        sendbtn1= (Button) findViewById(R.id.send_Btn01);
        msgrecyclerView = (RecyclerView) findViewById(R.id.recycler_view01);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgrecyclerView.setLayoutManager(layoutManager);
        chattingAdapter01 = new chattingAdapter(msgList);
        msgrecyclerView.setAdapter(chattingAdapter01);

//        tvLeft= (TextView) findViewById(R.id.tvLeft);
//        tvRight= (TextView) findViewById(R.id.tvRight);
        sendbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content1 = ed1.getText().toString();
                if(!content1.equals("")) {
                    boolean NetState = isNetWorkVoliable(MainActivity.this);
                    if(!NetState){
                        Toast.makeText(MainActivity.this,"小主没网啦，检查网络可否？",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        //Android检测网络状态，判断当前网络是否可用。
                        Msg msg = new Msg(content1, Msg.Type_Sned);
                        msgList.add(msg);
                        chattingAdapter01.notifyItemInserted(msgList.size()-1);
                        //有新消息时，刷新Listview的显示。
                        msgrecyclerView.scrollToPosition(msgList.size()-1);
                        //将ListView刷新至最后一行。
                        url += content1;
                        HttpData httpData1= (HttpData) new HttpData(url,MainActivity.this).execute();
//                    tvRight.setText(content1);
                        url="http://www.tuling123.com/openapi/api?key=a31b7b76732c4416a282fbb5dace44a5&info=";
                        ed1.setText("");
                    }

                }
                else{
                    Toast.makeText(MainActivity.this,"小主,你想说什么~~~？", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isNetWorkVoliable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //初始化连接管理器
        if(connectivityManager!=null){
            //初始化连接管理器
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //对network进行判断初始化。
            if(networkInfo!=null&&networkInfo.isConnected()){
                //判断当前网络是连接的。
                if(networkInfo.getState()== NetworkInfo.State.CONNECTED) {
                    //判断当前网络是可用的。
                    return true;
                }
            }

        }
        return false;

    }

    @Override
    public void getUrl(String data) {//复写抽象方法
        JasonResolve jr1 =new JasonResolve(data);

        String content2 = jr1.jasonReturn();
        Msg msg = new Msg(content2, Msg.Type_Received);
        msgList.add(msg);
        chattingAdapter01.notifyItemInserted(msgList.size()-1);
        //有新消息时，刷新Listview的显示。
        msgrecyclerView.scrollToPosition(msgList.size()-1);

    }

}
