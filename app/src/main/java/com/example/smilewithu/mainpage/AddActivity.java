package com.example.smilewithu.mainpage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.widget.DatePicker;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private Button cl,can,getdate,conf,n1,n2,n3,n4,n5,n6,n7,n8,n9,n0,ad,mi,mu,di,dot2,eq;
    private TextView output;
    private Drawable buttonback;
    DecimalFormat df=new DecimalFormat("#.00");
    CharSequence cs;
    private double a=0,b=0,result=0;
    private int xiaoshu=0;
    private  int method=0,ifdot=0;
    private boolean ifa=true,ifresult=false;
    Calendar calendar;
    private String date=new String();
    private String kind=new String();

    private String comment=new String();
    private String how=new String();
    SQLiteDAOImpl record;
     private Record r=new Record();
    private EditText com;
    private MyTextView showkind;
    private String stateChar[] = {"饮食","交通","购物","恋爱","旅游","书籍","医疗","零食","饮品","衣服"
            ,"日用品","娱乐","数码","美容","水果","快递","烟酒","社交","通讯","住房","彩票","发红包","学费"
            ,"礼物","运动","宠物" ,"其它支出","工资","兼职","理财","红包","其它收入"};   //0-26表示支出，27-32表示收入
    private int ImageId[]={R.drawable.yinshi,R.drawable.jiaotongditiedongchegaotiexianxing,R.drawable.gouwu,
            R.drawable.lianai,R.drawable.lvyou,R.drawable.shuben,R.drawable.yiliao,R.drawable.lingshi,
            R.drawable.yinliao,R.drawable.yifu,R.drawable.riyongpin,R.drawable.yule,R.drawable.shuma,
            R.drawable.meirong,R.drawable.shuiguo,R.drawable.kuaidi,R.drawable.yanjiu,R.drawable.shejiao,
            R.drawable.tongxun,R.drawable.zhufang,R.drawable.caipiao,R.drawable.fahongbao,R.drawable.xuefei,
            R.drawable.liwu,R.drawable.yundong,R.drawable.pet,R.drawable.zhichuqita,R.drawable.salary,
            R.drawable.jianzhi,R.drawable.licai,R.drawable.hongbao,R.drawable.shouru};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        record=new SQLiteDAOImpl(this);
        r=new Record();

        output=(TextView)findViewById(R.id.out);
        cl=(Button)findViewById(R.id.clear);
        can=(Button)findViewById(R.id.cancel);
        getdate=(Button)findViewById(R.id.choosedate);
        conf=(Button)findViewById(R.id.confirm);
        n1=(Button)findViewById(R.id.num1);
        n2=(Button)findViewById(R.id.num2);
        n3=(Button)findViewById(R.id.num3);
        n4=(Button)findViewById(R.id.num4);
        n5=(Button)findViewById(R.id.num5);
        n6=(Button)findViewById(R.id.num6);
        n7=(Button)findViewById(R.id.num7);
        n8=(Button)findViewById(R.id.num8);
        n9=(Button)findViewById(R.id.num9);
        n0=(Button)findViewById(R.id.num0);
        ad=(Button)findViewById(R.id.add);
         mi=(Button)findViewById(R.id.minus);
         mu=(Button)findViewById(R.id.multiply);
         di=(Button)findViewById(R.id.divided);
         dot2=(Button)findViewById(R.id.dot);
         eq=(Button)findViewById(R.id.equal);

         com=(EditText)findViewById(R.id.comment);
         showkind=(MyTextView) findViewById(R.id.showkind) ;


        Intent intent =getIntent();
        kind=intent.getStringExtra("kind");
        how=intent.getStringExtra("how");
        if(kind.isEmpty()){
            Toast.makeText(this,"请选择类型",Toast.LENGTH_SHORT).show();
            finish();
        }


        showkind.setText(kind);

       calendar=Calendar.getInstance();
        date=String.format("%d-%d-%d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));

         buttonback=n0.getBackground();



    }

    public void can(View v){
        Intent intent =new Intent(AddActivity.this,ChooseActivity.class);
        startActivity(intent);
        finish();
    }
    public void setdate(View v){

        calendar =Calendar.getInstance();

        new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               date=String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void con(View v){
      String money=new String();

        money=""+how+output.getText();
        comment=""+com.getText().toString();
        r.setDate(date);
        r.setKind(kind);
        r.setMessage(comment);
        r.setMoney(money);
        for(int i=0;i<stateChar.length;i++){
             if(stateChar[i].equals(r.getKind())){
                 r.setID(i);
                 break;
             }
        }
        record.insert(r);
        /*
      Toast.makeText(this,r.getImageId()+" "+r.getKind()+" "+r.getDate()+" "+r.getMoney()+" "+r.getMessage(),Toast.LENGTH_SHORT).show();

        List<Record> recordList = new ArrayList<Record>();
        recordList=record.findAll(r.getDate());
       output.setText(String.valueOf(recordList.size()));

*/
        Intent intent=new Intent(AddActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void clearall(View v){
        mu.getBackground().setAlpha(buttonback.getAlpha());
        ad.getBackground().setAlpha(buttonback.getAlpha());
        mi.getBackground().setAlpha(buttonback.getAlpha());
        di.getBackground().setAlpha(buttonback.getAlpha());
         ifdot=0;
          a=0;
          b=0;
          ifa=true;
          method=0;
            result=0;
            ifresult=false;
            xiaoshu=0;
        CharSequence cs=String.valueOf(result);
        output.setText("0");

    }

    public void div(View v){

        if(method==3)
            if(b<0.00000000001)return;
            else {
                equ(eq);
                ifresult=false;
                return;
            }
        output.append("÷");
        mu.getBackground().setAlpha(buttonback.getAlpha());
        mi.getBackground().setAlpha(buttonback.getAlpha());
        ad.getBackground().setAlpha(buttonback.getAlpha());
        di.getBackground().setAlpha(100);

     method=3;
     xiaoshu=0;
        ifdot=0;
     ifresult=false;
        if(ifa) {

            ifa = false;
                    }

        else{
           equ(eq);
        }
    }
    public void n0(View v){
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
       if(ifresult) {clearall(cl);}
           if(ifa){

               if(xiaoshu>0)
               {
                   output.append("0");
                   xiaoshu*=10;
               }
               else {
                   output.append("0");
                   a = a * 10;

               }
           }
           else {
               if(xiaoshu>0) {
                   output.append("0");
                   xiaoshu *= 10;
               }
               else{
                   output.append("0");
                   b=b*10;
               }
           }
    }
    public void n1(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
          output.append("1");
        if(ifa){
            if(xiaoshu>0)
            {

                a=a+1.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+1;
        }
        else {
            if(xiaoshu>0) {

                b=b+1.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+1;
        }
    }
    public void n2(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("2");
        if(ifa){
            if(xiaoshu>0)
            {

                a=a+2.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+2;
        }
        else {
            if(xiaoshu>0) {

                b=b+2.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+2;
        }
    }
    public void n3(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("3");
        if(ifa){
            if(xiaoshu>0)
            {

                a=a+3.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+3;
        }
        else {
            if(xiaoshu>0) {

                b=b+3.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+3;
        }
    }
    public void n4(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("4");
        if(ifa){
            if(xiaoshu>0)
            {
                 a=a+4.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+4;
        }
        else {
            if(xiaoshu>0) {
               b=b+4.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+4;
        }
    }
    public void n5(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("5");
        if(ifa){
            if(xiaoshu>0)
            {
                a=a+(5.0/xiaoshu);
                xiaoshu*=10;
            }
            else
                a=a*10+5;
        }
        else {
            if(xiaoshu>0) {
                  b=b+5.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+5;
        }
    }
    public void n6(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("6");
        if(ifa){
            if(xiaoshu>0)
            {
                 a=a+6.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+6;
        }
        else {
            if(xiaoshu>0) {
                b=b+6.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+6;
        }
    }
    public void n7(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("7");
        if(ifa){
            if(xiaoshu>0)
            {
               a=a+7.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+7;
        }
        else {
            if(xiaoshu>0) {
               b=b+7.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+7;
        }
    }
    public void n8(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
                output.setText("");
        }
        output.append("8");
        if(ifa){
            if(xiaoshu>0)
            {
                a=a+8.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+8;
        }
        else {
            if(xiaoshu>0) {
                 b=b+8.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+8;
        }
    }
    public void n9(View v){
        if(ifresult) {clearall(cl);}
        if(a==0&&ifa==true||b==0&&ifa==false)
        {
            if(!output.getText().toString().contains("."))
              output.setText("");
        }
        output.append("9");
        if(ifa){
            if(xiaoshu>0)
            {
                a=a+9.0/xiaoshu;
                xiaoshu*=10;
            }
            else
                a=a*10+9;
        }
        else {
            if(xiaoshu>0) {
                b=b+9.0/xiaoshu;
                xiaoshu *= 10;
            }
            else
                b=b*10+9;
        }
    }
    public void mul(View v){
        if(method==4)
            if(b<0.00000000001)return;
        else {
            equ(eq);
            ifresult=false;
            return;
        }
        output.append("×");


        di.getBackground().setAlpha(buttonback.getAlpha());
        mi.getBackground().setAlpha(buttonback.getAlpha());
        ad.getBackground().setAlpha(buttonback.getAlpha());
        mu.getBackground().setAlpha(100);
        method=4;
        ifdot=0;
        xiaoshu=0;
        xiaoshu=0;
        if(ifa) {
            ifa = false;
        }

        else{
            eq.performClick();
        }
    }
    public void minu(View v){
        if(method==5)
            if(b<0.00000000001)return;
            else {
                equ(eq);
                ifresult=false;
                return;
            }
        output.append("-");
        mu.getBackground().setAlpha(buttonback.getAlpha());
        di.getBackground().setAlpha(buttonback.getAlpha());
        ad.getBackground().setAlpha(buttonback.getAlpha());
        mi.getBackground().setAlpha(100);
        method=5;
        xiaoshu=0;
        ifdot=0;
        if(ifa) {
            ifa = false;
        }

        else{
            eq.performClick();
        }
    }
    public void ad(View v){
        if(method==6)
            if(b<0.00000000001)return;
            else {
                equ(eq);
                ifresult=false;
                return;
            }
        output.append("+");
        mu.getBackground().setAlpha(buttonback.getAlpha());
        mi.getBackground().setAlpha(buttonback.getAlpha());
       di.getBackground().setAlpha(buttonback.getAlpha());
        ad.getBackground().setAlpha(100);
         method=6;
        ifdot=0;
        xiaoshu=0;
        if(ifa) {
            ifa = false;
        }

        else{
            eq.performClick();
        }
    }
    public void dott(View v){

        if(ifdot==1)return;
     ifdot=1;
     output.append(".");
        xiaoshu=10;

    }
    public void equ(View v){

     switch(method){
         case 0:break;
         case 1:break;
         case 2:break;
          case 3:{
             if(b==0){output.setText("错误");
             clearall(cl);
             break;
             }

             a=1.0*a/b;
             b=0;
             result=a;

              CharSequence cs=String.valueOf(df.format(result));
              if(result<1)
              output.setText("0"+cs);
              else   output.setText(cs);
              break;
         }
         case 4:{

             a=a*b;
             b=0;
             result=a;

             CharSequence cs=String.valueOf(df.format(result));
             if(result<1)
                 output.setText("0"+cs);
             else   output.setText(cs);
             break;
         }
         case 5:{
             a=a-b;
             b=0;
             result=a;

             CharSequence cs=String.valueOf(df.format(result));
             if(result<1)
                 output.setText("0"+cs);
             else   output.setText(cs);
             break;
         }
         case 6:
         {

             a=a+b;
             b=0;
             result=a;

             CharSequence cs=String.valueOf(df.format(result));
             if(result<1)
                 output.setText("0"+cs);
             else   output.setText(cs);
             break;
         }
         case 7:break;
         default:{
             output.setText("错误");
             clearall(cl);
             break;
         }
        }
        ifresult=true;
       method=0;
       xiaoshu=0;
    }


}
