package com.example.dingdan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.dingdan.presenter.IPresenter;

public abstract class BaesFragmentActivity<T extends IPresenter> extends Fragment {
    T presneter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createpresenter();
    }
    protected abstract void createpresenter();
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presneter != null){
            presneter.detach();
        }
    }
}
