package com.example.fenkayn;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "project_android.db";
    public static final String TABLE_NAME = "USER";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "FULLNAME";
    public static final String COL_5 = "GENDER";
    public static final String COL_6 = "BIRTHDATE";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255))", TABLE_NAME, COL_1, COL_2, COL_3, COL_4, COL_5, COL_6));


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  boolean addUser(String email,String password,String fullname,String birthday, String gender){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,fullname);
        contentValues.put(COL_5,gender);
        contentValues.put(COL_6,birthday);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return  false;

        }else{
            return true;
        }
    }

    public Cursor getAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from user",null);
        return res;
    }

    private boolean checkUser(String email, String password){

        // code here

        return true;
    }
}
