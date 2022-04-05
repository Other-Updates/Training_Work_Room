package com.example.ticketing_app_facetoface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyAddDBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Userdetailsss.db";
    public MyAddDBHelper (Context context) { super(context ,"Userdetailsss.db",null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table Userdetailsss(addticket TEXT ,discrip TEXT )");
    }



    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Userdetailsss");
    }
    public Boolean insertuserdata (String addticket, String discrip) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("addticket", addticket);
        contentValues.put("discrip", discrip);

        long result = MyDB.insert("Userdetailsss", null, contentValues);

        if (result == -1)
            return false;
        else {
            return true;
        }
    }
    public Boolean checkusername(String addticket) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Userdetailsss where addticket = ?", new String[]{addticket});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String addticket, String discrip){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Userdetailsss where addticket = ? and discrip = ?", new String[] {addticket,discrip});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getdata () {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Userdetailsss" , null);
        return cursor;
    }


}
