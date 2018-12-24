package com.ryan.newsapp.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.ryan.newsapp.R;
import com.ryan.newsapp.model.DbManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFragment  extends Fragment {


    private DbManager mgr;
    private ImageView imageView ;
    private GridView gridView;
    private List<Map<String,Object>> datalist;
    private int [] icon = {R.drawable.legend_akali,R.drawable.legend_vyen,R.drawable.legend_yaduokesi,R.drawable.legend_xindela};
    private String [] iconName = {"阿卡丽","薇恩","亚多克斯","辛德拉"};
    private SimpleAdapter simpleAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        gridView = (GridView)getView().findViewById(R.id.legend_grid);
        datalist = new ArrayList<Map<String,Object>>();
        simpleAdapter = new SimpleAdapter(getActivity(),datalist,R.layout.grid_item,new String[] {"image","text"},new int[] {R.id.grid_item_image,R.id.grid_item_text});
        gridView.setAdapter(simpleAdapter);
        imageView  = getView().findViewById(R.id.legend_img);
        mgr = new DbManager(getActivity());
        init_data(mgr);
        readImage();

    }

    private void init_data(DbManager mgr){
        for(int i = 0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            datalist.add(map);
        }

        mgr.saveImage(R.drawable.legend_akali,"akali","top","ap",3333,333,getContext());

    }

    private void readImage(){
        byte[] imgData = mgr.readImage();
        if(imgData!=null){
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imgData,0,imgData.length);
            imageView.setImageBitmap(imageBitmap);
        }
        else{
            imageView.setBackgroundResource(R.drawable.legend_vyen);
        }
    }


}
