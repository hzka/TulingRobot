package nwu.hzk.tulingrobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HZK on 2017/7/25.
 * 这一适配器继承自ArrayAdapter,并将其泛型指定为chatting类
 */
public class chattingAdapter extends ArrayAdapter<chatting> {
    private int imageid;

    public chattingAdapter(Context context, int resource, List<chatting> objects) {
        super(context, resource, objects);
        imageid=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        chatting chatting1=getItem(position);//获取当前的Fruit实例
        View view= LayoutInflater.from(getContext()).inflate(imageid,parent,false);

        return super.getView(position, convertView, parent);
    }
}
