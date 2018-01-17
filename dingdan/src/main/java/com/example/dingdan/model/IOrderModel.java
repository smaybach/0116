package com.example.dingdan.model;


import com.example.dingdan.bean.OrderBean;

import java.util.List;



public interface IOrderModel {
    void showDate(Order order);
    interface Order{
        void complate(List<OrderBean.DataBean> list);
    }
}
