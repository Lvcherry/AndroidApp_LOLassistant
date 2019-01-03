package com.ryan.newsapp.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.newsapp.R;

import java.util.HashMap;
import java.util.List;

public class LegendNewsAdapter extends BaseAdapter{

    private List<ListViewItem> list;
    private ListViewItem tempListViewItem;
    private LayoutInflater layoutInflater;
    public LegendNewsAdapter(){

    }
    public LegendNewsAdapter(Context context, List<ListViewItem> list){
        this.list=list;
        layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){


        View view =null;
        if(layoutInflater!=null){
            view = layoutInflater.inflate(R.layout.list_item,null);

            TextView textView = view.findViewById(R.id.legend_news_title);
            ImageView imageView = view.findViewById(R.id.legend_news_img);

            tempListViewItem = list.get(position);
            textView.setText(tempListViewItem.getBitmap());
            imageView.setImageResource(tempListViewItem.getListTitle());
        }
        return view;
    }


}
