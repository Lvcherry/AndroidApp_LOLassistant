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
import android.widget.ImageView;

import com.ryan.newsapp.R;
import com.ryan.newsapp.model.DbManager;

public class ReadFragment  extends Fragment {


    private DbManager mgr;
    private ImageView imageView ;


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


        imageView  = getView().findViewById(R.id.legend_img);
        mgr = new DbManager(getActivity());
        init_data(mgr);
        readImage();

    }

    private void init_data(DbManager mgr){
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
