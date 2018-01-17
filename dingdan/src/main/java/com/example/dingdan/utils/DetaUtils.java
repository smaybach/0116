package com.example.dingdan.utils;

import com.example.dingdan.inter.Inters;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetaUtils {
    private static Inters service  = null ;
    public static Inters getInstance(){
        if(service == null){
            synchronized (DetaUtils.class){

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://120.27.23.105/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(OkHttpUtils.getInstance())
                        .build();
                service = retrofit.create(Inters.class);
            }
        }
        return service;
    }
}
