package com.example.dingdan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dingdan.R;
import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.presenter.OrderPresenter;
import com.example.dingdan.utils.RetrofitUtils;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by Mx丶 on 2018/1/17.
 */

public class MyFrag extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<OrderBean.DataBean> list;
    OrderPresenter presenter;
    public MyFrag(Context context, List<OrderBean.DataBean> list, OrderPresenter presenter) {
        this.context = context;
        this.list = list;
        this.presenter = presenter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.daizhifu,parent, false);;
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (list.get(position).getStatus()==0){
            ((MyViewHolder)holder).ll.setVisibility(View.VISIBLE);
            ((MyViewHolder)holder).mTitleText.setText(list.get(position).getTitle());
            ((MyViewHolder)holder).mPriceText.setText("价格: "+list.get(position).getPrice());
            ((MyViewHolder)holder).mTameText.setText(list.get(position).getCreatetime());
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
