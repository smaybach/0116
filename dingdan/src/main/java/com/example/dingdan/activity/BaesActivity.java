package com.example.dingdan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.dingdan.presenter.IPresenter;


public abstract class BaesActivity<T extends IPresenter> extends Activity {
    T presneter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createpresenter();
    }
    protected abstract void createpresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presneter != null){
            presneter.detach();
        }
    }
}
