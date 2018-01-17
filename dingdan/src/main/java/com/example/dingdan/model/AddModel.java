package com.example.dingdan.model;

import android.util.Log;

import com.example.dingdan.bean.DateBean;
import com.example.dingdan.utils.DetaUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddModel implements IAddModel {
    @Override
    public void showAdd(int pid, final Date date) {
        DetaUtils.getInstance().date(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("////", "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(DateBean bean) {
                        date.complate(bean);
                    }
                });
    }
}
