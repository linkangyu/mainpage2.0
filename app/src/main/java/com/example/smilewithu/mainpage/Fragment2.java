package com.example.smilewithu.mainpage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    private String kind =new String();
    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // final User user ;
        final RelativeLayout gongzi=(RelativeLayout) getActivity().findViewById(R.id.salary_layout);
        final RelativeLayout jianzhi=(RelativeLayout) getActivity().findViewById(R.id.jianzhi_layout);
        final RelativeLayout licai=(RelativeLayout) getActivity().findViewById(R.id.licai_layout);
        final RelativeLayout hongbao=(RelativeLayout) getActivity().findViewById(R.id.hongbao_layout);
        final RelativeLayout qita=(RelativeLayout) getActivity().findViewById(R.id.qita_layout);
      gongzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="工资";

                passDate();
            }
        });
           jianzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="兼职";

                passDate();
            }
        });
        licai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="理财";

                passDate();
            }
        });
        hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="红包";

                passDate();
            }
        });
        qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind="其它收入";

                passDate();
            }
        });
    }
    public void passDate(){
        Intent intent =new Intent(getActivity(),AddActivity.class);
        intent.putExtra("kind",kind.trim());
        intent.putExtra("how","+");
        startActivity(intent);
        getActivity().finish();
    }
}
