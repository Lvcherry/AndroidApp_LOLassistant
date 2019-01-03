package com.ryan.newsapp.adapter;

import android.graphics.Bitmap;

public class ListViewItem {

    private int strategyTitle;
    private String strategyBitmap;

    public ListViewItem(){

    }
    public ListViewItem(int strategyTitle,String strategyBitmap){
        this.strategyTitle = strategyTitle;
        this.strategyBitmap = strategyBitmap;
    }

    public int  getListTitle(){
        return strategyTitle;
    }
    public String getBitmap(){
        return strategyBitmap;
    }



}
