package com.example.dingdan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dingdan.fragment.FragmentOne;
import com.example.dingdan.fragment.FragmentThree;
import com.example.dingdan.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.sp)
    RadioButton sp;
    @BindView(R.id.gwc)
    RadioButton gwc;
    @BindView(R.id.my)
    RadioButton my;
    @BindView(R.id.rg)
    RadioGroup rg;
    private List<Fragment> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        initData();
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.sp);
                        break;
                    case 1:
                        rg.check(R.id.gwc);
                        break;
                    case 2:
                        rg.check(R.id.my);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.sp:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.gwc:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.my:
                        vp.setCurrentItem(2);
                        break;

                }
            }
        });
    }

    private void initData() {
        list.add(new FragmentOne());
        list.add(new FragmentTwo());
        list.add(new FragmentThree());
    }

    class MyAdapter extends FragmentPagerAdapter{


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
