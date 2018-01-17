package com.example.dingdan.inter;

import com.example.dingdan.bean.DateBean;
import com.example.dingdan.bean.GoodsBean;
import com.example.dingdan.bean.OrderBean;
import com.example.dingdan.bean.ShouBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/1/16.
 */

public interface Inters {
    //首页数据
    //https://www.zhaoapi.cn/ad/getAd
    @GET("ad/getAd")
    Observable<ShouBean> getShow();
    //添加购物车
    @GET("product/getProductDetail")
    Observable<DateBean> date(@Query("pid") int pid);

    @GET("product/addCart?uid=4357")
    Call<ResponseBody> add(@Query("pid") int pid);
    //购物车
    @GET("product/getCarts?uid=4357")
    Observable<GoodsBean> goods();


    @GET("product/deleteCart?uid=4357")
    Call<ResponseBody> delete(@Query("pid") int pid);
    //删除购物车
    //http://120.27.23.105/product/deleteCart?uid=72&pid=1

    //订单
    @GET("product/getOrders?uid=71")
    Observable<OrderBean> order();
    @GET("product/updateOrder")
    Call<ResponseBody> update(@Query("uid") int uid,@Query("status") int status,@Query("orderId") int orderId);




}
