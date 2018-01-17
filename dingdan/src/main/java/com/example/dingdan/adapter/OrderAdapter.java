package com.example.dingdan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dingdan.R;
import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.presenter.OrderPresenter;
import com.example.dingdan.utils.RetrofitUtils;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by lenovo on 2018/1/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context context;
    List<OrderBean.DataBean> list;
    OrderPresenter presenter;


    public OrderAdapter(Context context, List<OrderBean.DataBean> list,OrderPresenter presenter) {
        this.context = context;
        this.list = list;
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.qb_item,parent, false);;
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).mTitleText.setText(list.get(position).getTitle());
        ((MyViewHolder)holder).mPriceText.setText("价格: "+list.get(position).getPrice());
        ((MyViewHolder)holder).mTameText.setText(list.get(position).getCreatetime());
        if (list.get(position).getStatus()==0){
            ((MyViewHolder) holder).mDaizhifuText.setText("待支付");
            ((MyViewHolder) holder).mDaizhifuText.setTextColor(Color.RED);
            ((MyViewHolder) holder).mBtnText.setText("取消订单");
            ((MyViewHolder) holder).mBtnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定要取消订单吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            retrofit2.Call<ResponseBody> call = RetrofitUtils.getInstance().update(71,2,list.get(position).getOrderid());
                            call.enqueue(new retrofit2.Callback<ResponseBody>() {
                                @Override
                                public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                                    presenter.DateOrder();
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();

                }
            });

        }else if (list.get(position).getStatus()==1){
            ((MyViewHolder) holder).mDaizhifuText.setText("已支付");
            ((MyViewHolder) holder).mDaizhifuText.setTextColor(Color.BLACK);
            ((MyViewHolder) holder).mBtnText.setText("查看订单");
        }else if (list.get(position).getStatus()==2){
            ((MyViewHolder) holder).mDaizhifuText.setText("已取消");
            ((MyViewHolder) holder).mDaizhifuText.setTextColor(Color.BLACK);
            ((MyViewHolder) holder).mBtnText.setText("查看订单");
            ((MyViewHolder) holder).mBtnText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定循环利用吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            retrofit2.Call<ResponseBody> call = RetrofitUtils.getInstance().update(71,0,list.get(position).getOrderid());
                            call.enqueue(new retrofit2.Callback<ResponseBody>() {
                                @Override
                                public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                                    presenter.DateOrder();
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (list!=null){
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
        public MyViewHolder(View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.text_title);
            mDaizhifuText = (TextView) itemView.findViewById(R.id.text_daizhifu);
            mPriceText = (TextView) itemView.findViewById(R.id.text_price);
            mTameText = (TextView) itemView.findViewById(R.id.text_tame);
            mBtnText = (TextView) itemView.findViewById(R.id.text_btn);
        }
    }


}
