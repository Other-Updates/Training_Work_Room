package com.example.ticketing_app_facetoface;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper  {
    public static final String DBNAME = "usersjaaaaa.db";
    public DBHelper (Context context) {
        super(context ,"Usersjaaaaa.db",null,10);
    }
// ,gmail TEXT ,mobile TEXT
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Usersjaaaaa(name TEXT ,contact TEXT primary key ,dob TEXT,address TEXT,gmail TEXT,mobile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Usersjaaaaa");
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

        long result = DB.insert("Usersjaaaaa", null, contentValues);

        if (result == -1)
            return false;
         else
            return true;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Usersjaaaaa where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Usersjaaaaa where username = ? and password = ?", new String[] {username,password});
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











/*

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
   */
/* public DBHelper (Context context) {
        super(context ,"Userdata.db",null,5);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Userdetails(name TEXT primary key,contact TEXT ,dob TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata (String name , String contact , String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("Contact", contact);
        contentValues.put("dob", dob);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean updateuserdata (String name , String contact , String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Contact", contact);
        contentValues.put("dob", dob);

        Cursor cursor = DB.rawQuery("Select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}
        else
        {
            return false;
        }
    }
    public Boolean deleteuserdata (String name ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}
        else
        {
            return false;
        }
    }
    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from userdetails" , null);
        return cursor;
    }*//*
}
*/

















































/*


public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table users(Username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists users");

    }

    public Boolean insertuserData(String name, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", name);
        values.put("userpassword", password);

        long result = DB.insert("users", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }}


    public Boolean updateuserdata (String name, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userpassword", password);

        Cursor cursor = DB.rawQuery("Select * from users where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("users", values, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}
        else
        {
            return false;
        }
    }

    public Boolean deleteuserdata (String name ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("users", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }}
        else
        {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users" , null);
        return cursor;
    }



    }





    public Boolean checkusername (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkuserpassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}


*/
