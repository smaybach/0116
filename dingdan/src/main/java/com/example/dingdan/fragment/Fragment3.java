package com.example.dingdan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dingdan.R;
import com.example.dingdan.activity.BaesFragmentActivity;
import com.example.dingdan.adapter.MyQx;
import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.presenter.OrderPresenter;
import com.example.dingdan.view.IOrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/16.
 */

public class Fragment3 extends BaesFragmentActivity<OrderPresenter> implements IOrderView {

    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private OrderPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_4, null);
        presenter.DateOrder();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showOrder(List<OrderBean.DataBean> list) {
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyQx myQx = new MyQx(getActivity(), list, presenter);
        rv.setAdapter(myQx);

    }

    @Override
    protected void createpresenter() {
        presenter = new OrderPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
