package com.example.lenovo.a0116;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 2018/1/16.
 */

public class PlueView extends LinearLayout {
    Context context;

    private Button revserse,tianjian;
    private TextView edittext;
    private int mcont=1;
    public PlueView(Context context) {
        super(context);
    }

    public PlueView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //加载布局
        View view = View.inflate(context, R.layout.layout_item, null);
        //控件
        revserse = view.findViewById(R.id.revserse);
        edittext = view.findViewById(R.id.edittext);
        tianjian = view.findViewById(R.id.tianjian);
        //初始化之后设置控件点击事件
        //减号的点击事件
        revserse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edittext.getText().toString().trim();
                Integer cont = Integer.valueOf(trim);
                if (cont>1){
                    mcont=cont-1;
                    edittext.setText(mcont+"");
                    if (listener!=null){
                        listener.click(mcont);
                    }
                }else {
                    Toast.makeText(context, "已经是最后一条数据了！", Toast.LENGTH_SHORT).show();

                }

            }
        });
        //文本输入框的点击事件
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //加号的点击事件
        tianjian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edittext.getText().toString().trim();//获取输入框的值去掉空格
                int coun = Integer.valueOf(trim)+1;//把从输入框的得到的值转换成整形
                mcont = coun;
                edittext.setText(coun+"");
                if (listener!=null){
                    listener.click(coun);
                }



            }
        });


    addView(view);


    }
    public PlueView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setEditText(int num){
        if(edittext != null){
            edittext.setText(num+"");
        }
    }
    public ClickListener listener;
    public void setListener(ClickListener listener ){
        this.listener=listener;
    }
    /**
     * 加减号的点击事件
     */
    public  interface  ClickListener{
        public void click(int coun);
    }
}
