package com.example.smilewithu.mainpage;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(context);

    }

    /**

     * 初始化字体

     * @param context

     */
    private void init(Context context) {

        //设置字体样式

        setTypeface(FontCustom.setFont(context));

    }

}

