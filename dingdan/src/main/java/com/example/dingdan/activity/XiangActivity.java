package com.example.dingdan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dingdan.MainActivity;
import com.example.dingdan.R;
import com.example.dingdan.bean.DateBean;
import com.example.dingdan.bean.EventBean;
import com.example.dingdan.presenter.AddPresenter;
import com.example.dingdan.utils.DetaUtils;
import com.example.dingdan.utils.GlideImageLoader;
import com.example.dingdan.utils.MessageEvent;
import com.example.dingdan.view.IAddCarView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XiangActivity extends BaesActivity<AddPresenter> implements IAddCarView {

    @BindView(R.id.jiecao_Player)
    JCVideoPlayerStandard jiecaoPlayer;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.uprice)
    TextView uprice;
    String url = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    int pid;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.jia)
    Button jia;
    @BindView(R.id.gou)
    Button gou;
    private AddPresenter presenter;
    private String image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ButterKnife.bind(this);
        jiecaoPlayer.setUp(url, jiecaoPlayer.SCREEN_LAYOUT_NORMAL, "淘宝");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void createpresenter() {
        presenter = new AddPresenter(this);
    }
    @Subscribe(sticky = true)
    public void event(EventBean eventBean) {
        pid = eventBean.getPid();
        image1 = eventBean.getName();
        final String[] split = this.image1.split("\\|");
        List<String> bannerList = new ArrayList<>();
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        for (int i = 0; i < split.length; i++) {
            bannerList.add(split[i]);
        }
        banner.setImages(bannerList);
        banner.setImageLoader(new GlideImageLoader());
        banner.start();
        name.setText(eventBean.getImage());
        uprice.setText("￥" + eventBean.getPrice());
        presneter.Addshow();
    }
    @Override
    public void addsuccess(DateBean dateBean) {

    }
    @Override
    public int pid() {
        return pid;
    }
    @OnClick({R.id.jia, R.id.gou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jia:
                Call<ResponseBody> call = DetaUtils.getInstance().add(pid);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(XiangActivity.this, "加购成功", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(XiangActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(XiangActivity.this, "加购失败", Toast.LENGTH_LONG).show();
                    }
                });



                break;
            case R.id.gou:
                startActivity(new Intent(this, DingActivity.class));
                Toast.makeText(this, "欢迎来到订单页面！", Toast.LENGTH_SHORT).show();
                break;
        }
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.jia:
//                Call<ResponseBody> call = DetaUtils.getInstance().add(pid);
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Toast.makeText(XiangActivity.this, "加购成功", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(XiangActivity.this, MainActivity.class));
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(XiangActivity.this, "加购失败", Toast.LENGTH_LONG).show();
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }


}
