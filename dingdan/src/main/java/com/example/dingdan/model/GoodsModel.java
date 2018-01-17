package com.example.dingdan.model;

import android.util.Log;

import com.example.dingdan.bean.GoodsBean;
import com.example.dingdan.utils.DetaUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GoodsModel implements IGoodsModel {

    @Override
    public void showDate( final Goods goods) {
        DetaUtils.getInstance().goods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("goods", "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(GoodsBean bean) {
                        goods.complate(bean);
                    }
                });
    }
}
