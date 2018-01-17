package com.example.dingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dingdan.R;
import com.example.dingdan.activity.XiangActivity;
import com.example.dingdan.bean.EventBean;
import com.example.dingdan.bean.ShouBean;
import com.example.dingdan.utils.MessageEvent;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lenovo on 2017/12/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<ShouBean.TuijianBean.ListBean> list;
//    private OnItemClickListener mOnItemClickListener;
    public MyAdapter(Context context, List<ShouBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //拿到我们自己定义的布局
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.sprv_item, parent, false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        //加载文字
        holder.tv.setText(list.get(position).getTitle());
        holder.jq.setText(list.get(position).getPrice()+"");
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(split[0]))
                .build();
        holder.simpleDraweeView.setController(controller);
        //判断图片点击
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent(list.get(position).getPid()));
                EventBus.getDefault().postSticky(new EventBean(list.get(position).getPid(),
                            list.get(position).getTitle(),
                            list.get(position).getImages(),
                            list.get(position).getPrice()+""));
                    Intent intent=new Intent(context, XiangActivity.class);
                    context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView tv;
        TextView jq;
        public MyHolder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            jq = itemView.findViewById(R.id.jq);
        }
    }
}
