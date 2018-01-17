package com.example.dingdan.model;


import com.example.dingdan.bean.GoodsBean;


public interface IGoodsModel {
    void showDate(Goods goods);
    interface Goods{
        void complate(GoodsBean bean);
    }
}
