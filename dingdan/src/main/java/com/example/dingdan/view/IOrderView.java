package com.example.dingdan.view;


import com.example.dingdan.bean.OrderBean;

import java.util.List;

/**
 * Created by Mx丶 on 2018/1/16.
 */

public interface IOrderView {
    void showOrder(List<OrderBean.DataBean> list);
}
