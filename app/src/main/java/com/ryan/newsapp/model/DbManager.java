package com.ryan.newsapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.mob.tools.utils.SQLiteHelper;
import com.ryan.newsapp.R.*;

import java.io.ByteArrayOutputStream;

public class DbManager {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;



    public DbManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public void saveImage(int legend_img_id,String legend_name,String legend_position,String legend_damage,int legend_point,int legend_gold,Context context){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),legend_img_id );
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,os);
        cv.put("legend_img",os.toByteArray());
        cv.put("legend_name",legend_name);
        cv.put("legend_position",legend_position);
        cv.put("legend_damage",legend_damage);
        cv.put("legend_point",legend_point);
        cv.put("legend_gold",legend_gold);
        db.insert("legend_table",null,cv);
        db.close();
    }


    public byte[] readImage() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        byte[] imgData = null;
        Cursor cur = null;
        try{
            cur = db.query("legend_table", new String[]{"legend_name", "legend_position", "legend_damage", "legend_point", "legend_gold", "legend_img"}, null, null, null, null, null);
            if(cur.moveToFirst()){
                imgData = cur.getBlob(cur.getColumnIndex("legend_img"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cur!=null){
                cur.close();
            }
        }
        return imgData;

    }
}




