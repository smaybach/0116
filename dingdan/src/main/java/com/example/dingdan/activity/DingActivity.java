package com.example.dingdan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dingdan.R;
import com.example.dingdan.fragment.Fragment1;
import com.example.dingdan.fragment.Fragment2;
import com.example.dingdan.fragment.Fragment3;
import com.example.dingdan.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DingActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.image_btn)
    ImageView imageBtn;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.button_1)
    RadioButton button1;
    @BindView(R.id.button_2)
    RadioButton button2;
    @BindView(R.id.button_3)
    RadioButton button3;
    @BindView(R.id.button_4)
    RadioButton button4;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.framelayout)
    FrameLayout frameLayout;
    private Fragment1 fragment_1;
    private Fragment2 fragment_2;
    private Fragment3 fragment_3;
    private Fragment4 fragment_4;
    private List<Fragment> list;
    private View item_popup;
    private PopupWindow popupWindow;
    private View view;
    private TextView btn_daizhi;
    private TextView btn_yizhi;
    private TextView btn_yiqu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding);
        ButterKnife.bind(this);
        initView();





    }

    private void initView() {
        //创建Fragment对象及集合
        fragment_1 = new Fragment1();
        fragment_2 = new Fragment2();
        fragment_3 = new Fragment3();
        fragment_4 = new Fragment4();
        //将Fragment对象添加到list中
        list = new ArrayList<>();
        list.add(fragment_1);
        list.add(fragment_2);
        list.add(fragment_3);
        list.add(fragment_4);
        //设置RadioGroup开始时设置的按钮，设置第一个按钮为默认值
        radioGroup.check(R.id.button_1);
        //设置按钮点击监听
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        //初始时向容器中添加第一个Fragment对象
        addFragment(fragment_1);
        imageBtn.setOnClickListener(this);
        item_popup = View.inflate(this, R.layout.item_popup, null);
        view = View.inflate(this, R.layout.activity_main, null);
        popupWindow = new PopupWindow(item_popup, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);


        btn_daizhi = item_popup.findViewById(R.id.btn_daizhi);
        btn_yizhi = item_popup.findViewById(R.id.btn_yizhi);
        btn_yiqu = item_popup.findViewById(R.id.btn_yiqu);
        btn_daizhi.setOnClickListener(this);
        btn_yizhi.setOnClickListener(this);
        btn_yiqu.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
//我们根据参数的id区别不同按钮
        //不同按钮对应着不同的Fragment对象页面
        switch (v.getId()) {
            case R.id.button_1:
                addFragment(fragment_1);
                break;
            case R.id.button_2:
                addFragment(fragment_2);
                break;
            case R.id.button_3:
                addFragment(fragment_3);
                break;
            case R.id.button_4:
                addFragment(fragment_4);
                break;
            case R.id.image_btn:
                popupWindow.showAsDropDown(imageBtn);
                break;
            case R.id.btn_daizhi:
                addFragment(fragment_2);
                popupWindow.dismiss();
                break;
            case R.id.btn_yizhi:
                addFragment(fragment_4);
                popupWindow.dismiss();
                break;
            case R.id.btn_yiqu:
                addFragment(fragment_3);
                popupWindow.dismiss();
                break;
            default:
                break;
        }




    }
    //向Activity中添加Fragment的方法
    public void addFragment(Fragment fragment) {

        //获得Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //使用事务替换Fragment容器中Fragment对象
        fragmentTransaction.replace(R.id.framelayout, fragment);
        //提交事务，否则事务不生效
        fragmentTransaction.commit();
    }
}
