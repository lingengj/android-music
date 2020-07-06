package com.example.apple.wyymusic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 2019/6/21.
 */

public class dbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="myDb";
    public static final int DB_VERSION=1;
    public static final String TB_NAME="Users";
    public static final String COL_ID="id";
    public static final String COL_USERNAME="userName";
    public static final String COL_PWD="pwd";
    public static final String SQL="create table "+TB_NAME+"("+COL_ID+" integer primary key autoincrement,"+COL_USERNAME+" text,"+COL_PWD+" text)";
    private static dbHelper helper;


    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static synchronized dbHelper getInstance(Context context){
        if(helper==null)
            helper=new dbHelper(context);
        return helper;
    }

}
