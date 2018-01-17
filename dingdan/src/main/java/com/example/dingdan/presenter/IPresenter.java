package com.example.dingdan.presenter;



public interface IPresenter<T> {
    void attach(T view);
    void detach();

}
