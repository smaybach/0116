package com.example.dingdan.presenter;

import com.example.dingdan.bean.ShouBean;
import com.example.dingdan.model.IShouModel;
import com.example.dingdan.model.ShouModel;
import com.example.dingdan.view.IShouView;

import java.lang.ref.SoftReference;
import java.util.List;

public class ShouPresenter implements IPresenter<IShouView>{
    SoftReference<IShouView> reference;
    IShouModel model;

    public ShouPresenter(IShouView view) {
        model=new ShouModel();
        attach(view);
    }

    @Override
    public void attach(IShouView view) {
        reference=new SoftReference<IShouView>(view);
    }

    @Override
    public void detach() {
        reference.clear();

    }
    public void shouData(){
        model.showHome(new IShouModel.Home() {
            @Override
            public void comolate(List<ShouBean.TuijianBean.ListBean> listBeen) {
                reference.get().shouSuccee(listBeen);
            }
        });
    }






}
