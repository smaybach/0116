package com.example.dingdan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dingdan.R;
import com.example.dingdan.activity.BaesFragmentActivity;
import com.example.dingdan.activity.DingActivity;
import com.example.dingdan.adapter.RecyAdapter;
import com.example.dingdan.bean.GoodsBean;
import com.example.dingdan.presenter.GoodsPresenter;
import com.example.dingdan.utils.MessageBus;
import com.example.dingdan.utils.MessageEvent;
import com.example.dingdan.view.IGoodsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/16.
 */

public class FragmentTwo extends BaesFragmentActivity<GoodsPresenter> implements IGoodsView {
    @BindView(R.id.recycler_View)
    RecyclerView recyclerView;
    @BindView(R.id.quanxuan)
    CheckBox mQuanxuan;
    @BindView(R.id.total_price)
    TextView mPriceTotal;
    @BindView(R.id.total_num)
    TextView mNumTotal;
    Unbinder unbinder;
    RecyAdapter recyAdapter;
    @BindView(R.id.jiesuan)
    TextView jiesuan;
    private GoodsPresenter goodsPresenter;
    private GoodsPresenter presenter;
    int pid, uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_two, null);
        unbinder = ButterKnife.bind(this, view);
        initview();
        presenter.DateGoods();
        EventBus.getDefault().register(this);
        return view;
    }

    private void initview() {

        mQuanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //new出适配器
        recyAdapter = new RecyAdapter(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyAdapter);

        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        recyAdapter.setUpdateListener(new RecyAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                mNumTotal.setText("共" + num + "件商品");//总数量
                mPriceTotal.setText("总价 :¥" + total + "元");//总价
                if (allCheck) {
                    mQuanxuan.setTag(2);
                    mQuanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                } else {
                    mQuanxuan.setTag(1);
                    mQuanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                mQuanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        mQuanxuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) mQuanxuan.getTag();
                if (tag == 1) {
                    mQuanxuan.setTag(2);
                    mQuanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                } else {
                    mQuanxuan.setTag(1);
                    mQuanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                recyAdapter.quanXuan(mQuanxuan.isChecked());
            }
        });


    }

    @Override
    protected void createpresenter() {
        presenter = new GoodsPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showGoods(GoodsBean bean) {
        recyAdapter.add(bean);
    }


    @Subscribe(sticky = true)
    public void onBus(MessageBus bus) {
        uid = bus.getUid();

    }

    @Subscribe(sticky = true)
    public void onEvent(MessageEvent event) {
        pid = event.getPid();

    }

//进入订单页面
    @OnClick(R.id.jiesuan)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), DingActivity.class));
        Toast.makeText(getActivity(), "欢迎来到订单页面！", Toast.LENGTH_SHORT).show();



    }
}
