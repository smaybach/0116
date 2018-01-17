package com.example.dingdan.bean;

/**
 * Created by lenovo on 2018/1/16.
 */

public class EventBean {
    private int pid;
    private String image;
    private String name;
    private String price;

    public EventBean(int pid, String image, String name, String price) {
        this.pid = pid;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "EventBus{" +
                "pid='" + pid + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
