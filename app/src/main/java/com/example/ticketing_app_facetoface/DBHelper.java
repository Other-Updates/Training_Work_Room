package com.example.ticketing_app_facetoface;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper  {
    public static final String DBNAME = "Usersjaaaa.db";
    public DBHelper (Context context) {
        super(context ,"Usersjaaaa.db",null,8);
    }
// ,gmail TEXT ,mobile TEXT
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Usersjaaaa(name TEXT ,contact TEXT ,dob TEXT,address TEXT,gmail TEXT,mobile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Usersjaaaa");
    }

    public Boolean insertuserdata (String name, String contact, String dob,String address,String gmail,String mobile) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("Contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("address", address);
        contentValues.put("gmail", gmail);
        contentValues.put("mobile", mobile);

        long result = DB.insert("Usersjaaaa", null, contentValues);

        if (result == -1)
            return false;
         else {
            return true;
        }
    }
    public Boolean checkusername(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Usersjaaaa where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String name, String contact){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Usersjaaaa where name = ? and contact = ?", new String[] {name,contact});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getdata () {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from usersjaaaa" , null);
        return cursor;
    }


}