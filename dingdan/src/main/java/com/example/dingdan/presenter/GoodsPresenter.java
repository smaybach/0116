package com.example.dingdan.presenter;

import com.example.dingdan.bean.GoodsBean;
import com.example.dingdan.model.GoodsModel;
import com.example.dingdan.model.IGoodsModel;
import com.example.dingdan.view.IGoodsView;
import java.lang.ref.SoftReference;


public class GoodsPresenter implements IPresenter<IGoodsView> {
    IGoodsModel model;
    SoftReference<IGoodsView> softReference;

    public GoodsPresenter(IGoodsView view) {
        attach(view);
        model = new GoodsModel();
    }
    public void DateGoods(){

        model.showDate(new IGoodsModel.Goods() {
            @Override
            public void complate(GoodsBean bean) {
                softReference.get().showGoods(bean);
            }
        });
    }
    @Override
    public void attach(IGoodsView view) {
        softReference = new SoftReference<IGoodsView>(view);
    }

    @Override
    public void detach() {
        softReference.clear();
    }

}
