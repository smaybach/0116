package com.example.dingdan.model;

import com.example.dingdan.bean.DateBean;

/**
 * Created by lenovo on 2018/1/16.
 */

public interface IAddModel {
    void showAdd(int pid,Date date);
    interface Date{
        void complate(DateBean bean);
    }

}
