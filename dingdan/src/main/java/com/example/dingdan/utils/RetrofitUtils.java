package com.example.dingdan.utils;

import com.example.dingdan.inter.Inters;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    private static Inters service  = null ;
    public static Inters getInstance(){
        if(service == null){
            synchronized (RetrofitUtils.class){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://120.27.23.105/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                service = retrofit.create(Inters.class);
            }
        }
        return service;
    }

}
