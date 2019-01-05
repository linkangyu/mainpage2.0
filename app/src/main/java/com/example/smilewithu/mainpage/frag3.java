package com.example.smilewithu.mainpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by dm on 16-3-29.
 * 第一个页面
 */
public class frag3 extends Fragment {
    SQLiteDAOImpl sqlieDao;
   MyTextView daka;
   MyTextView daka_continue;
   MyTextView recordcount;
    private OnButtonClickf3 onButtonClickf3;

    private LinearLayout lookgraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);
        lookgraph=(LinearLayout)view.findViewById(R.id.lookgraph_layout);

        sqlieDao=new SQLiteDAOImpl(view.getContext());

        daka=(MyTextView) view.findViewById(R.id.showdaka);
        daka_continue=(MyTextView) view.findViewById(R.id.showdaka_continue);
        recordcount=(MyTextView) view.findViewById(R.id.showrecordcount);
        lookgraph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(onButtonClickf3!=null){
                    onButtonClickf3.onClickf3(lookgraph);
                }
            }
        });
      daka.setText(sqlieDao.get_DakaDays());
     daka_continue.setText(sqlieDao.getDaka_continue());
      recordcount.setText(sqlieDao.getRecordCount());
        return view;
    }

    public OnButtonClickf3 getOnButtonClickf3() {
        return onButtonClickf3;
    }
    public void setOnButtonClickf3(OnButtonClickf3 onButtonClick) {
        this.onButtonClickf3 = onButtonClick;
    }
    public interface OnButtonClickf3{
        public void onClickf3(View view);
    }
}