package com.example.smilewithu.mainpage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends FragmentActivity {
       private List<Fragment> fragmentList;
       private MyTextView income,outcome,cancel;
    private  FragAdapter adapter;
    private String kind=new String();
    private   ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());

        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
         vp = (ViewPager) findViewById(R.id.viewpager);
        income=(MyTextView) findViewById(R.id.textView) ;
        outcome=(MyTextView) findViewById(R.id.textView2) ;
        cancel=(MyTextView) findViewById(R.id.textView5) ;
        vp.setAdapter(adapter);
     }

     public void can(View v){
         Intent intent =new Intent(ChooseActivity.this,MainActivity.class);
         startActivity(intent);
         finish();
     }

    public void zhichu(View v){
       vp.setCurrentItem(0,true);
    }
    public void shouru(View v){
        vp.setCurrentItem(1,true);
    }
     public class FragAdapter extends FragmentPagerAdapter {
         public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
             super(fm);
             fragmentList = fragments;
         }@Override
         public Fragment getItem(int arg0) {
             return fragmentList.get(arg0);
         }
         @Override
         public int getCount() {
             return fragmentList.size();

         }
    }
/*
    @Override
    public void onClick(View v) {
        Toast.makeText(this,"选择类型",Toast.LENGTH_SHORT).show();

        switch (v.getId()) {
            case R.id.textView:

                break;
            case R.id.textView2:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,new Fragment2()).commit();
                break;
            case R.id.textView5:
                finish();
                break;

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.gouwu_layout:
                kind="购物";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.lianai_layout:
                kind="恋爱";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.lvyou_layout:
                kind="旅游";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.shuji_layout:
                kind="书籍";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.yiliao_layout:
                kind="医疗";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.lingshi_layout:
                kind="零食";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.yinpin_layout:
                kind="饮品";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.yifu_layout:
                kind="衣服";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.riyong_layout:
                kind="日用品";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.yule_layout:
                kind="娱乐";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.shuma_layout:
                kind="数码";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.meirong_layout:
                kind="美容";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.shuiguo_layout:
                kind="水果";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.kuaidi_layout:
                kind="快递";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.yanjiu_layout:
                kind="烟酒";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.shejiao_layout:
                kind="社交";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.tongxun_layout:
                kind="通讯";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.zhufang_layout:
                kind="住房";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.caipiao_layout:
                kind="彩票";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.fahongbao_layout:
                kind="发红包";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.xuefei_layout:
                kind="学费";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.salary_layout:
                kind="工资";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.jianzhi_layout:
                kind="兼职";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.licai_layout:
                kind="理财";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.hongbao_layout:
                kind="红包";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;
            case R.id.qita_layout:
                kind="其它";

                startActivity(intent);
                intent.putExtra("kind",kind.trim());
                break;

            default:
                break;
        }
    }
*/
}
