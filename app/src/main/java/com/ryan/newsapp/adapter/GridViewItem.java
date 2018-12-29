package com.ryan.newsapp.adapter;

import android.graphics.Bitmap;
import android.widget.GridView;

public class GridViewItem {

    public Bitmap bitmap;
    public String title;

    public GridViewItem(){

    }
    public GridViewItem(Bitmap bitmap,String title){
        super();
        this.bitmap=bitmap;
        this.title=title;
    }
}
