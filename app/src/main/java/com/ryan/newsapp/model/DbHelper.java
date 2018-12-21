package com.ryan.newsapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;

public class DbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="LOL.DB";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table legend_table(legend_name varchar(20) primary key,legend_position varchar(20),legend_damage varchar(2),legend_point integer,legend_gold integer,legend_img BLOB )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        String sql="ALTER TABLE legend_table ADD COLUMN other STRING";
        db.execSQL(sql);
    }
}
