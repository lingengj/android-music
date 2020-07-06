package com.example.apple.wyymusic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.wyymusic.model.Users;

import java.util.ArrayList;

/**
 * Created by ASUS on 2019/6/21.
 */

public class UserService {

    SQLiteDatabase db;
    dbHelper helper;

    public UserService(Context context) {
        helper=dbHelper.getInstance(context);
        db=helper.getReadableDatabase();
    }

    public boolean updateUser(Users user){
        ContentValues val=new ContentValues();
        val.put(dbHelper.COL_ID,user.getId());
        val.put(dbHelper.COL_USERNAME,user.getUserName());
        val.put(dbHelper.COL_PWD,user.getUserPwd());
        if(db.update(dbHelper.TB_NAME,val,dbHelper.COL_ID+"=?",new String[]{String.valueOf(user.getId())})>0)
            return true;
        return false;
    }

    public boolean deleteUser(int id){
        if(db.delete(dbHelper.TB_NAME,dbHelper.COL_ID+"=?",new String[]{String.valueOf(id)})>0)
            return true;
        return false;
    }

    public boolean addUser(Users user){
        ContentValues val=new ContentValues();
        val.put(dbHelper.COL_USERNAME,user.getUserName());
        val.put(dbHelper.COL_PWD,user.getUserPwd());
        if(db.insert(dbHelper.TB_NAME,null,val)>0)
            return true;
        return false;
    }

    public boolean getUser(Users user){
        Boolean flag=null;
        ContentValues val=new ContentValues();
        val.put(dbHelper.COL_ID,user.getId());
        val.put(dbHelper.COL_USERNAME,user.getUserName());
        val.put(dbHelper.COL_PWD,user.getUserPwd());
        try{
            Cursor cursor=null;
            cursor=db.query(dbHelper.TB_NAME,new String[]{dbHelper.COL_USERNAME,dbHelper.COL_PWD},dbHelper.COL_USERNAME+"=? and "+dbHelper.COL_PWD+"=?",new String[]{user.getUserName(),user.getUserPwd()},null,null,null);
            if(!cursor.equals(null)){
                flag=true;
                cursor.close();
            }
            else{
                flag=false;
            }
        }
        catch(Exception e){
            Log.v("Exception",e.getMessage());
        }
        return flag;
    }

    public ArrayList<Users> getUsers(){
        ArrayList<Users> list=new ArrayList<>();
        try{
            Cursor cursor=null;
            cursor=db.rawQuery("select * from "+dbHelper.TB_NAME,null);
            if(!cursor.equals(null)&&cursor.moveToFirst()){
                do{
                    Users user=new Users();
                    user.setId(cursor.getInt(cursor.getColumnIndex(dbHelper.COL_ID)));
                    user.setUserName(cursor.getString(cursor.getColumnIndex(dbHelper.COL_USERNAME)));
                    user.setUserPwd(cursor.getString(cursor.getColumnIndex(dbHelper.COL_PWD)));
                    list.add(user);
                }
                while (cursor.moveToNext());
                if(cursor!=null)
                    cursor.close();
            }
            else{
                Log.v("mydb","null");
            }
        }
        catch(Exception e){
            Log.v("Exception",e.getMessage());
        }
        return list;
    }

}
