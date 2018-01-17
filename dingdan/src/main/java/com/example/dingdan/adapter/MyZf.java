package com.example.dingdan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dingdan.R;
import com.example.dingdan.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mx丶 on 2018/1/17.
 */

public class MyZf extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<OrderBean.DataBean> list;
    private List<String> stringList;


    public MyZf(Context context, List<OrderBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yizhifu,parent, false);;
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        stringList = new ArrayList<>();
        if (list.get(position).getStatus()==1){

//            stringList.add(list.get(position).getTitle());
//
//            System.out.println("****"+stringList.size());



            ((MyViewHolder)holder).ll.setVisibility(View.VISIBLE);
            ((MyViewHolder)holder).mTitleText.setText(list.get(position).getTitle());
            ((MyViewHolder)holder).mPriceText.setText("价格: "+list.get(position).getPrice());
            ((MyViewHolder)holder).mTameText.setText(list.get(position).getCreatetime());
            ((MyViewHolder) holder).mDaizhifuText.setText("已支付");
            ((MyViewHolder) holder).mDaizhifuText.setTextColor(Color.BLACK);
            ((MyViewHolder) holder).mBtnText.setText("查看订单");
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleText;
        TextView mDaizhifuText;
        TextView mPriceText;
        TextView mTameText;
        TextView mBtnText;
        LinearLayout ll;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.text_title);
            mDaizhifuText = (TextView) itemView.findViewById(R.id.text_daizhifu);
            mPriceText = (TextView) itemView.findViewById(R.id.text_price);
            mTameText = (TextView) itemView.findViewById(R.id.text_tame);
            mBtnText = (TextView) itemView.findViewById(R.id.text_btn);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
