package nwu.hzk.tulingrobot;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ResponseDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by HZK on 2017/7/20.
 * AsyncTask实现异步通信,采用HttpPost发送请求；
 *  doInBackground(Params...), 将在onPreExecute 方法执行后马上执行，该方法运行在后台线程中。
 *  这里将主要负责执行那些很耗时的后台计算工作。可以调用 publishProgress方法来更新实时的任务进度。
 *  该方法是抽象方法，子类必须实现。
 */
public class HttpData extends AsyncTask<String,Void,String> {
    private DefaultHttpClient hc1;
    private HttpGet hg1;
    private String url;
    private HttpResponse hr1;
    private HttpEntity he1;
    private InputStream is1;
    private  HttpDataInter hdi1;//实现接口

    public HttpData(String url,HttpDataInter hdi1){
        this.url=url;
        this.hdi1=hdi1;
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            hc1 = new DefaultHttpClient();//实例化客户端
            hg1= new HttpGet(url);//新建Get请求方式。
            hr1=hc1.execute(hg1);//有返回值，不一定会成功，因而需要try..catch..
            he1=hr1.getEntity();//通过response对象获取实体。
            is1=he1.getContent();//将数据对象实体转为流文件。
            BufferedReader br1=new BufferedReader(new InputStreamReader(is1));
            String line=null;
            StringBuffer sb1=new StringBuffer();//储存一个StringBuffer来储存所有数据
            while((line=br1.readLine())!=null){
                    sb1.append(line);
            }
            return sb1.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * onPostExecute(Result), 在doInBackground 执行完成后，
    * onPostExecute 方法将被UI thread调用，后台的计算结果将通过该方法传递到UI thread.
    * */

    @Override
    protected void onPostExecute(String s) {
        hdi1.getUrl(s);
        super.onPostExecute(s);

    }

}
