package com.example.dingdan.model;

import android.util.Log;

import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.utils.RetrofitUtils;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderModel implements IOrderModel {

    @Override
    public void showDate(final Order order) {
        RetrofitUtils.getInstance().order()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("=========", "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(OrderBean orderBean) {
                        List<OrderBean.DataBean> list = orderBean.getData();
                        order.complate(list);
                    }
                });
    }
}
