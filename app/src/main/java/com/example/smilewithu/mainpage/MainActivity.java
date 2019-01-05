package com.example.smilewithu.mainpage;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity; // 注意这里我们导入的V4的包，不要导成app的包了
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * 主页面内容
 * Created by dm on 16-1-19.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    // 初始化顶部栏显示
    private ImageView titleLeftImv;
    private TextView titleTv;
    // 定义4个Fragment对象
    private frag1 fg1;
    private frag2 fg2;
    private frag3 fg3;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout thirdLayout;

    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;

    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;

    // 定义几个颜色
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡




    }


    /**
     * 初始化页面
     */
   public void initView() {
// 初始化页面标题栏
        /*
        titleLeftImv = (ImageView) findViewById(R.id.title_imv);

        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        */
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        titleTv = (TextView) findViewById(R.id.title_text_tv);
        titleTv.setText("轻 记 账");
// 初始化底部导航栏的控件
        firstImage = (ImageView) findViewById(R.id.first_image);
        secondImage = (ImageView) findViewById(R.id.second_image);
        thirdImage = (ImageView) findViewById(R.id.third_image);

        firstText = (TextView) findViewById(R.id.first_text);
        secondText = (TextView) findViewById(R.id.second_text);
        thirdText = (TextView) findViewById(R.id.third_text);

        firstLayout = (RelativeLayout) findViewById(R.id.first_layout);
        secondLayout = (RelativeLayout) findViewById(R.id.second_layout);
        thirdLayout = (RelativeLayout) findViewById(R.id.third_layout);

        firstLayout.setOnClickListener(MainActivity.this);
        secondLayout.setOnClickListener(MainActivity.this);
        thirdLayout.setOnClickListener(MainActivity.this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_layout:
                setChioceItem(0);
                break;
            case R.id.second_layout:
                setChioceItem(1);
                break;
            case R.id.third_layout:
                setChioceItem(2);
                break;
            default:
                break;
        }
    }
    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (index) {
            case 0:
// firstImage.setImageResource(R.drawable.XXXX); 需要的话自行修改
                clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
                hideFragments(fragmentTransaction);
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
// 如果fg1为空，则创建一个并添加到界面上
                if (fg1 == null) {
                    fg1 = new frag1();
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
// 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);
                }

                fg1.setOnButtonClick(new frag1.OnButtonClick() {

                    @Override
                    public void onClick(View view) {


                        Calendar calendar=Calendar.getInstance();
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fg1.initRecord(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

                    }
                });

                break;
            case 1:
// secondImage.setImageResource(R.drawable.XXXX);

                  Intent intent =new Intent(MainActivity.this,ChooseActivity.class);
                startActivity(intent);
                  finish();
                break;
            case 2:
// thirdImage.setImageResource(R.drawable.XXXX);
                clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
                hideFragments(fragmentTransaction);
                thirdText.setTextColor(dark);
                thirdLayout.setBackgroundColor(gray);
                if (fg3 == null) {
                    fg3 = new frag3();
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                fg3.setOnButtonClickf3(new frag3.OnButtonClickf3() {

                    @Override
                    public void onClickf3(View view) {
                        //Toast.makeText(MainActivity.this,"打开分析图表",Toast.LENGTH_SHORT).show();

                        Intent graphintent=new Intent(MainActivity.this,MyGraphActivity.class);
                        startActivity(graphintent);


                    }
                });
                break;

        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
// firstImage.setImageResource(R.drawable.XXX);
        firstText.setTextColor(gray);
        firstLayout.setBackgroundColor(whirt);
// secondImage.setImageResource(R.drawable.XXX);
        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(whirt);
// thirdImage.setImageResource(R.drawable.XXX);
        thirdText.setTextColor(gray);
        thirdLayout.setBackgroundColor(whirt);
// fourthImage.setImageResource(R.drawable.XXX);

    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }

    }

}
