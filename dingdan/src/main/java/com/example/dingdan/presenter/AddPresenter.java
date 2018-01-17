package com.example.dingdan.presenter;

import com.example.dingdan.bean.DateBean;
import com.example.dingdan.model.AddModel;
import com.example.dingdan.model.IAddModel;
import com.example.dingdan.view.IAddCarView;

import java.lang.ref.SoftReference;

/**
 * Created by lenovo on 2018/1/16.
 */

public class AddPresenter implements IPresenter<IAddCarView>{
    IAddModel model;
    SoftReference<IAddCarView> reference;
    public AddPresenter(IAddCarView view){
        attach(view);
        model=new AddModel();

    }
    public void Addshow(){
        int pid = reference.get().pid();
        model.showAdd(pid, new IAddModel.Date() {
            @Override
            public void complate(DateBean bean) {
                reference.get().addsuccess(bean);
            }
        });
    }
    @Override
    public void attach(IAddCarView view) {
        reference=new SoftReference<IAddCarView>(view);
    }
    @Override
    public void detach() {
        reference.clear();
    }
}
