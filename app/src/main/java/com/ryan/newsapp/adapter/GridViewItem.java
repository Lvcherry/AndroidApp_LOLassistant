package com.ryan.newsapp.adapter;

import android.graphics.Bitmap;
import android.widget.GridView;

/*
    显示英雄列表用到的gridview控件的子item的设计
 */

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
