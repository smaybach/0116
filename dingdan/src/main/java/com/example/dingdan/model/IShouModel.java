package com.example.dingdan.model;

import com.example.dingdan.bean.ShouBean;

import java.util.List;


public interface IShouModel {

    void showHome(Home home);
    interface Home{
        void comolate(List<ShouBean.TuijianBean.ListBean> listBeen);
    }
}
