package nwu.hzk.tulingrobot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements HttpDataInter{

    private ListView lv1;
    private EditText ed1;
    private TextView tvLeft,tvRight;
    private Button sendbtn1;
    private String url,content1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url="http://www.tuling123.com/openapi/api?key=be464f4e51fc4f9a8c962c60c0a6bfe1&info=";

        ed1= (EditText) findViewById(R.id.et1);
        sendbtn1= (Button) findViewById(R.id.sendBtn);
        tvLeft= (TextView) findViewById(R.id.tvLeft);
        tvRight= (TextView) findViewById(R.id.tvRight);
        sendbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed1!=null) {
                    String content1 = ed1.getText().toString();
                    url += content1;
                    HttpData httpData1= (HttpData) new HttpData(url,MainActivity.this).execute();
                    tvRight.setText(content1);
                    url="http://www.tuling123.com/openapi/api?key=a31b7b76732c4416a282fbb5dace44a5&info=";
                    ed1.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this,"Please input something", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void getUrl(String data) {//复写抽象方法
        JasonResolve jr1 =new JasonResolve(data);
        tvLeft.setText(jr1.jasonReturn());
    }

}
