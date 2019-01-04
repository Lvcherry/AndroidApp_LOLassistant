package com.ryan.newsapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.ryan.newsapp.ui.LoginActivity;

import com.ryan.newsapp.R;

import org.w3c.dom.Text;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class AccountFragment extends Fragment implements View.OnClickListener {

    ImageView iv_head;
    TextView accountName;
    TextView accountExit;
    RoundedBitmapDrawable initIcon = null;
    String text = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    private void setupViews(View view) {
        iv_head = (ImageView) view.findViewById(R.id.account_head);
        accountName = (TextView) view.findViewById(R.id.account_username);
        accountExit = (TextView) view.findViewById(R.id.account_exit);
        iv_head.setOnClickListener(this);
        accountName.setOnClickListener(this);
        accountExit.setOnClickListener(this);

        if (initIcon != null) {
            iv_head.setImageDrawable(initIcon);
        }
        if(text!=null){
            accountName.setText(text);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.account_head:{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
            }break;
            case R.id.account_username:{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
            }
            case R.id.account_exit:{
                Platform platform=ShareSDK.getPlatform(QQ.NAME);
                if (platform != null){
                    platform.removeAccount();
                }
            }break;
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            String headUrl = data.getStringExtra("icon");
            final String name = data.getStringExtra("username");

            //生成圆形图片
            Glide.with(this).load(headUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_head) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    initIcon = circularBitmapDrawable;
                    text = name;
                    iv_head.setImageDrawable(circularBitmapDrawable);
                }
            });
            accountName.setText(name);
        }


    }
}
