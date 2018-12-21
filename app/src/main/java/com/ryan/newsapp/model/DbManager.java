package com.ryan.newsapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.mob.tools.utils.SQLiteHelper;
import com.ryan.newsapp.R;

import java.io.ByteArrayOutputStream;

public class DbManager {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public DbManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public void saveImage(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.add_sub_btn_press);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,os);
        cv.put("legend_img",os.toByteArray());
        cv.put("legend_name","aioulia");
        cv.put("legend_position","pdddd");
        cv.put("legend_damage","ap");
        cv.put("legend_point","6300");
        cv.put("legend_gold","6300");
        db.insert("legend_table",null,cv);
        db.close();
    }


    public byte[] readImage() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cur = db.query("legend_table", new String[]{"legend_name", "legend_position", "legend_damage", "legend_point", "legend_gold", "legend_img"}, null, null, null, null, null);
        byte[] imgData =null;
        if(cur.moveToNext()){
            imgData = cur.getBlob(cur.getColumnIndex("legend_img"));

        }
        return imgData;
    }
}




