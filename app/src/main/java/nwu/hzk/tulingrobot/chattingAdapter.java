package nwu.hzk.tulingrobot;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HZK on 2017/7/25.
 * 这一适配器继承自ArrayAdapter,并将其泛型指定为chatting类
 */
//ViewHolder通常出现在适配器里，为的是listview滚动的时候快速设置值，而不必每次都重新创建很多对象，从而提升性能。
public class chattingAdapter extends RecyclerView.Adapter<chattingAdapter.ViewHolder> {

    private List<Msg>Msglist;

    public chattingAdapter(List<Msg> msgList) {
        Msglist=msgList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout left_linearlayout;
        LinearLayout right_linearlayout;
        TextView leftMsg;
        TextView rightMsg;
        ImageView leftimag;
        ImageView rightimag;
        public ViewHolder(View itemView) {
            super(itemView);
            left_linearlayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            right_linearlayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            leftMsg = (TextView) itemView.findViewById(R.id.tvLeft);
            rightMsg = (TextView) itemView.findViewById(R.id.tvRight);
            leftimag = (ImageView) itemView.findViewById(R.id.ivLeft);
            rightimag = (ImageView) itemView.findViewById(R.id.ivRight);
        }
    }
    @Override
    public chattingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(chattingAdapter.ViewHolder holder, int position) {
         Msg msg = Msglist.get(position);
        //若为右边则为右边布局。
        if(msg.getType()==Msg.Type_Sned){
            holder.left_linearlayout.setVisibility(View.GONE);
            holder.right_linearlayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }else if(msg.getType()==Msg.Type_Received){
            //若为左边则为左边布局。
            holder.right_linearlayout.setVisibility(View.GONE);
            holder.left_linearlayout.setVisibility(View.VISIBLE);
            holder.leftMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return Msglist.size();
    }

}
