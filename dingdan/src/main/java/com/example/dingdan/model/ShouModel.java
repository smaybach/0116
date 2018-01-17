package com.example.dingdan.model;

import com.example.dingdan.bean.ShouBean;
import com.example.dingdan.utils.RetrofitUtils;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ShouModel implements IShouModel {
    @Override
    public void showHome(final Home home) {
        RetrofitUtils.getInstance().getShow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShouBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShouBean homeBean) {
                        List<ShouBean.TuijianBean.ListBean> list = homeBean.getTuijian().getList();
                        home.comolate(list);
                    }
                });


    }
}
