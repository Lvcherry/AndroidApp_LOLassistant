package com.ryan.newsapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ryan.newsapp.R;
import com.ryan.newsapp.model.DbManager;

public class LegendActivity extends AppCompatActivity {


    private DbManager mgr;
    private ImageView imageView = findViewById(R.id.legend_img);

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        mgr = new DbManager(this);
        init_data(mgr);
        readImage();
    }

    private void init_data(DbManager mgr){
        mgr.saveImage(R.drawable.legend_akali,"akali","top","ap",3333,333,this);

    }

    private void readImage(){

        byte[] imgData = mgr.readImage();
        if(imgData!=null){
            Bitmap imagebitmap  = BitmapFactory.decodeByteArray(imgData,0,imgData.length);
            imageView.setImageBitmap(imagebitmap);
        }
        else{
            imageView.setBackgroundResource(R.drawable.legend_vyen);
        }
    }
}
