package com.example.smilewithu.mainpage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

   private String kind =new String();
    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement1, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // final User user ;

        final RelativeLayout yinshi=(RelativeLayout) getActivity().findViewById(R.id.eat_layout);
        final RelativeLayout jiaotong=(RelativeLayout) getActivity().findViewById(R.id.jiaotong_layout);
        final RelativeLayout gouwu=(RelativeLayout) getActivity().findViewById(R.id.gouwu_layout);
        final RelativeLayout lianai=(RelativeLayout) getActivity().findViewById(R.id.lianai_layout);
        final RelativeLayout lvyou=(RelativeLayout) getActivity().findViewById(R.id.lvyou_layout);
        final RelativeLayout shuji=(RelativeLayout) getActivity().findViewById(R.id.shuji_layout);
        final RelativeLayout yiliao=(RelativeLayout) getActivity().findViewById(R.id.yiliao_layout);
        final RelativeLayout lingshi=(RelativeLayout) getActivity().findViewById(R.id.lingshi_layout);
        final RelativeLayout yinpin=(RelativeLayout) getActivity().findViewById(R.id.yinpin_layout);
        final RelativeLayout yifu=(RelativeLayout) getActivity().findViewById(R.id.yifu_layout);
        final RelativeLayout riyong=(RelativeLayout) getActivity().findViewById(R.id.riyong_layout);
        final RelativeLayout yule=(RelativeLayout) getActivity().findViewById(R.id.yule_layout);
        final RelativeLayout shuma=(RelativeLayout) getActivity().findViewById(R.id.shuma_layout);
        final RelativeLayout meirong=(RelativeLayout) getActivity().findViewById(R.id.meirong_layout);
        final RelativeLayout shuiguo=(RelativeLayout) getActivity().findViewById(R.id.shuiguo_layout);
        final RelativeLayout kuaidi=(RelativeLayout) getActivity().findViewById(R.id.kuaidi_layout);
        final RelativeLayout yanjiu=(RelativeLayout) getActivity().findViewById(R.id.yanjiu_layout);
        final RelativeLayout shejiao=(RelativeLayout) getActivity().findViewById(R.id.shejiao_layout);
        final RelativeLayout tongxun=(RelativeLayout) getActivity().findViewById(R.id.tongxun_layout);
        final RelativeLayout zhufang=(RelativeLayout) getActivity().findViewById(R.id.zhufang_layout);
        final RelativeLayout caipiao=(RelativeLayout) getActivity().findViewById(R.id.caipiao_layout);
        final RelativeLayout fahongbao=(RelativeLayout) getActivity().findViewById(R.id.fahongbao_layout);
        final RelativeLayout xuefei=(RelativeLayout) getActivity().findViewById(R.id.xuefei_layout);
        final RelativeLayout liwu=(RelativeLayout) getActivity().findViewById(R.id.liwu_layout);
        final RelativeLayout yundong=(RelativeLayout) getActivity().findViewById(R.id.yundong_layout);
        final RelativeLayout chongwu=(RelativeLayout) getActivity().findViewById(R.id.chongwu_layout);
        final RelativeLayout qita=(RelativeLayout) getActivity().findViewById(R.id.qita_layout);
            yinshi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kind="饮食";

                    passDate();
                }
            });
       jiaotong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="交通";
                passDate();
            }
        });
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="购物";
                passDate();
            }
        });
       lianai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="恋爱";
                passDate();
            }
        });
        lvyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="旅游";
                passDate();
            }
        });
        shuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="书籍";
                passDate();
            }
        });
        yiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="医疗";
                passDate();
            }
        });
       lingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="零食";
                passDate();
            }
        });
       yinpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="饮品";
                passDate();
            }
        });
       yifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="衣服";
                passDate();
            }
        });
        riyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="日用品";
                passDate();
            }
        });
       yule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="娱乐";
                passDate();
            }
        });
        shuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="数码";
                passDate();
            }
        });
        meirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="美容";
                passDate();
            }
        });
        shuiguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="水果";
                passDate();
            }
        });
        kuaidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="快递";
                passDate();
            }
        });
        yanjiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="烟酒";
                passDate();
            }
        });
        shejiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="社交";
                passDate();
            }
        });
        tongxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="通讯";
                passDate();
            }
        });
        zhufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="住房";
                passDate();
            }
        });
        caipiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="彩票";
                passDate();
            }
        });
       fahongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="发红包";
                passDate();
            }
        });
        xuefei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="学费";
                passDate();
            }
        });
       liwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="礼物";
                passDate();
            }
        });
        yundong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="运动";
                passDate();
            }
        });
        chongwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="宠物";
                passDate();
            }
        });
       qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="其它支出";
                passDate();
            }
        });


    }
    public void passDate(){
        Intent intent =new Intent(getActivity(),AddActivity.class);
        intent.putExtra("kind",kind.trim());
        intent.putExtra("how","-");
        startActivity(intent);
        getActivity().finish();
    }

}
