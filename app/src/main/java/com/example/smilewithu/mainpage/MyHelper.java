package com.example.smilewithu.mainpage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "Mo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE record(date VARCHAR(20)  " +
                ", kind VARCHAR(20),money VARCHAR(20),message VARCHAR(30))");
        db.execSQL("CREATE TABLE daka(daka_date VARCHAR(20)  " +
                ",daka_days INT(10),daka_continue INT(10))");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
