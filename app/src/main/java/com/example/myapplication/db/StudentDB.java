package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.InputType;
import androidx.annotation.Nullable;
import com.example.myapplication.bean.studentbean;

public class StudentDB extends SQLiteOpenHelper {

    private static final String name = "student.db";
    private static final int version = 1;
    private static SQLiteOpenHelper mInstance;


    public StudentDB(@Nullable Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table student(" +
                "_id Integer primary key autoincrement ," +
                "name text," +
                "password Integer," +
                "className varchar(10)," +
                "xID Integer," +
                "age Integer," +
                "phone Integer," +
                "address varchar(100)," +
                "Math Integer," +
                "computer Integer," +
                "English Integer," +
                "Allsore Integer" +
                ")";
        String sql2 = "Create table teacher(" +
                "_id Integer primary key autoincrement," +
                "account Integer," +
                "password Integer" +
                ")";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
