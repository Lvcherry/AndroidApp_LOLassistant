package com.ryan.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.newsapp.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class LegendAdapter extends BaseAdapter
{
    private List<HashMap<String,GridViewItem>> list;
    private GridViewItem tempGridViewItem;
    private LayoutInflater layoutInflater;
    public LegendAdapter(Context context, List<HashMap<String,GridViewItem>> list){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);

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
    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if(layoutInflater!=null){
            view  = layoutInflater.inflate(R.layout.grid_item,null);


            ImageView imageView = view.findViewById(R.id.grid_item_image);
            TextView textView = view.findViewById(R.id.grid_item_text);

            tempGridViewItem = list.get(position).get("item");
            imageView.setImageBitmap(tempGridViewItem.bitmap);
            textView.setText(tempGridViewItem.title);
        }
        return view;
    }
}
