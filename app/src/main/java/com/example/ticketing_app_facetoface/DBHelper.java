package com.example.ticketing_app_facetoface;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper  {
    public static final String DBNAME = "Usersjaaaaa.db";
    public DBHelper (Context context) {
        super(context ,"Usersjaaaaa.db",null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Usersjaaaaa(name TEXT ,contact TEXT ,dob TEXT,address TEXT,gmail TEXT,mobile TEXT,spinner TXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Usersjaaaaa");
    }

    public Boolean insertuserdata (String name, String contact, String dob,String address,String gmail,String mobile, String spinner) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("Contact", contact);
        contentValues.put("dob", dob);
        contentValues.put("address", address);
        contentValues.put("gmail", gmail);
        contentValues.put("mobile", mobile);
        contentValues.put("spinner", spinner);

        long result = DB.insert("Usersjaaaaa", null, contentValues);

        if (result == -1)
            return false;
         else {
            return true;
        }
    }
    public Boolean checkusername(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Usersjaaaaa where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String name, String contact){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Usersjaaaaa where name = ? and contact = ?", new String[] {name,contact});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getdata () {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from usersjaaaaa" , null);
        return cursor;
    }


}