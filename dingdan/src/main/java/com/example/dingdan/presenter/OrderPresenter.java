package com.example.dingdan.presenter;

import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.model.IOrderModel;
import com.example.dingdan.model.OrderModel;
import com.example.dingdan.view.IOrderView;

import java.lang.ref.SoftReference;
import java.util.List;


public class OrderPresenter implements IPresenter<IOrderView>{
    IOrderModel model;
    SoftReference<IOrderView> softReference;

    public OrderPresenter(IOrderView view) {
        attach(view);
        model = new OrderModel();
    }
    public void DateOrder(){
        model.showDate(new IOrderModel.Order() {
            @Override
            public void complate(List<OrderBean.DataBean> list) {
                softReference.get().showOrder(list);
            }
        });
    }
    @Override
    public void attach(IOrderView view) {
        softReference = new SoftReference<IOrderView>(view);
    }

    @Override
    public void detach() {
        softReference.clear();
    }

}
