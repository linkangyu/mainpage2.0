package com.example.smilewithu.mainpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SQLiteDAOImpl {
    private MyHelper myhelper;
    private int month_days[]={31,28,31,30,31,30,31,31,30,31,30,31}; //十二个月的天数
    public SQLiteDAOImpl(Context context) {
        this.myhelper = new MyHelper(context);
    }

    public void insert(Record myrecord)         //插入
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();//取得数据库
        db.execSQL("insert into record (date,kind,money,message) values(?,?,?,?)",
                new String[]{myrecord.getDate(), myrecord.getKind(), myrecord.getMoney(),
                        myrecord.getMessage()});
        db.close();// 记得关闭数据库操作
    }

    public void delete(Record myrecord) //
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();//取得数据库
        db.execSQL("delete from record where date='" + myrecord.getDate() + "' and kind='" + myrecord.getKind() +
                "' and money='" + myrecord.getMoney() + "'" + "and message='" + myrecord.getMessage() + "'");
        db.close();// 记得关闭数据库操作
    }

    public List<Record> findAll(String date)      //根据日期查找收支纪录
    {
        List<Record>lists=new ArrayList<Record>();     //记录集数组
        SQLiteDatabase db = myhelper.getReadableDatabase();//取得数据库
        Record myrecord=null;
        Cursor cursor = db.rawQuery("select * from record where date=?", new String[]{date});
        while (cursor.moveToNext()) {
            myrecord= new Record();
            myrecord.setDate(cursor.getString(cursor.getColumnIndex("date")));
            myrecord.setKind(cursor.getString(cursor.getColumnIndex("kind")));
            myrecord.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            myrecord.setMessage(cursor.getString(cursor.getColumnIndex("message")));
            lists.add(myrecord);
        }
        db.close();// 记得关闭数据库操作
        return lists;
    }
    public String get_DakaDays()
    {
        int daka_Days=0;
        SQLiteDatabase db = myhelper.getReadableDatabase();//取得数据库
        Cursor cursor=db.query("daka",null,null,null,null,
                null,null);
        if(cursor.getCount()==0)          //没有记录
        {
          int daka_continue=1;
             String date=getToday();
             daka_Days=1;
            db.execSQL("insert into daka (daka_date,daka_days,daka_continue) values(?,?,?)",
                    new Object[]{date,daka_Days,daka_continue});
        }
        else
        {
            cursor.moveToFirst();
            String NewDade=getToday();
           String OldDate=cursor.getString(cursor.getColumnIndex("daka_date"));
           if(!NewDade.equals(OldDate))        //日期不一样
           {
               daka_Days=cursor.getInt(cursor.getColumnIndex("daka_days"))+1;
               ContentValues value = new ContentValues();
               value.put("daka_date",NewDade);
               value.put("daka_days",daka_Days);
               db.update("daka",value,"daka_date=?",new String[]{OldDate});
           }
           else {            //日期一样，打卡数不变
               daka_Days=cursor.getInt(cursor.getColumnIndex("daka_days"));
           }
        }
        db.close();
        cursor.close();
        return String.format("%d",daka_Days);
    }
    public String getDaka_continue()
    {
        int Newdaka,Olddaka;
        String date;
        Calendar calendar = Calendar.getInstance();//获取系统的日期
        int Newyear = calendar.get(Calendar.YEAR);
        int Newmonth = calendar.get(Calendar.MONTH)+1;
        int Newday = calendar.get(Calendar.DAY_OF_MONTH);
        if ((Newyear % 4 == 0 && Newyear % 100 != 0) || (Newyear % 400 == 0))     //闰年
        {
            month_days[1] = 29;
        }
        SQLiteDatabase db = myhelper.getReadableDatabase();//取得数据库
        Cursor cursor=db.query("daka",null,null,null,null,
                null,null);
        if(cursor.getCount()==0){
            return "0";
        }
        else
        {
            cursor.moveToFirst();
            date=cursor.getString(cursor.getColumnIndex("daka_date"));
            Olddaka=cursor.getInt(cursor.getColumnIndex("daka_continue"));
            String []temp=date.split("-");
            int Oldyear=Integer.parseInt(temp[0]);
            int Oldmonth=Integer.parseInt(temp[1]);
            int Oldday=Integer.parseInt(temp[2]);

            if(Newday==1&&Newmonth==1&&Oldmonth==12&&Oldday==31&&(Newyear-Oldyear==1))
            {
                Newdaka=Olddaka+1;
            }

            else if(Newyear==Oldyear&&(Newmonth-Oldmonth)==1&&Newday==1&&Oldday==month_days[Oldmonth-1])
            {
                Newdaka=Olddaka+1;
            }
            else if(Newyear==Oldyear&&Newmonth==Oldmonth&&(Newday-Oldday==1))
            {
                Newdaka=Olddaka+1;
            }
            else if(Newyear==Oldyear&&Newmonth==Oldmonth&&Newday==Oldday)
            {
              Newdaka=Olddaka;
            }
            else
            {
                Newdaka=-1;
            }
        }
        ContentValues value = new ContentValues();
        value.put("daka_continue",Newdaka);
        db.update("daka",value,"daka_date=?",new String[]{date});
        db.close();
        cursor.close();
        return String.format("%d",Newdaka);
    }
    public String getRecordCount()
    {
        SQLiteDatabase db = myhelper.getReadableDatabase();//取得数据库
        Record myrecord=null;
        Cursor cursor = db.query("record",null,null,null,null,
                null,null);
        int count=cursor.getCount();
        db.close();
        cursor.close();
        return String.format("%d",count);
    }
 public String getToday()
 {
     Calendar calendar = Calendar.getInstance();//获取系统的日期
     int year = calendar.get(Calendar.YEAR);
     int month = calendar.get(Calendar.MONTH)+1;
     int day = calendar.get(Calendar.DAY_OF_MONTH);
    return  String.format("%d-%d-%d",year,month,day);

 }
}
