package com.example.dingdan.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.dingdan.R;
import com.example.dingdan.activity.BaesFragmentActivity;
import com.example.dingdan.adapter.MyAdapter;
import com.example.dingdan.bean.ShouBean;
import com.example.dingdan.presenter.ShouPresenter;
import com.example.dingdan.view.IShouView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/16.
 */

public class FragmentOne extends BaesFragmentActivity<ShouPresenter> implements IShouView {
    @BindView(R.id.sp_rv)
    RecyclerView spRv;
    Unbinder unbinder;
    private ShouPresenter shouPresenter;
    public Window window;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_one, null);
        unbinder = ButterKnife.bind(this, view);

        shouPresenter.shouData();
        //设置沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window = getActivity().getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS

            );

        }
        return view;
    }



    @Override
    public void shouSuccee(List<ShouBean.TuijianBean.ListBean> bean) {
        GridLayoutManager mgs = new GridLayoutManager(getActivity(),2);
        spRv.setLayoutManager(mgs);
        MyAdapter myAdapter = new MyAdapter(getActivity(), bean);
        spRv.setAdapter(myAdapter);

    }
    @Override
    protected void createpresenter() {
        shouPresenter = new ShouPresenter(this);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
