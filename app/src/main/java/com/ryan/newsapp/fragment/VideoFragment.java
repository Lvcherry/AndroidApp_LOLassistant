package com.ryan.newsapp.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.newsapp.adapter.NewsViewPagerAdapter;
import com.ryan.newsapp.adapter.VideoItemAdapter;
import com.ryan.newsapp.entity.VideoEntity;
import com.ryan.newsapp.news_interface.VideoInterface;
import com.ryan.newsapp.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.ryan.newsapp.R;
import com.ryan.newsapp.utils.ManagerApi;

public class VideoFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private final String COMMON = "clientvideo";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<String> channelName;
    private List<String> channelTypeId;

    private List<Fragment> fragmentList;

    private NewsViewPagerAdapter adpater;

    //记录上次viewpager的滑动位置
    private int lastPostion;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        channelName = new ArrayList<>();

        channelTypeId = new ArrayList<>();

        channelName.add("精选");
        channelTypeId.add("JX");

        fragmentList = new ArrayList<>();

        adpater = new NewsViewPagerAdapter(getContext(), getChildFragmentManager(), fragmentList, channelName);

        //获取视频种类的信息(异步进行,所以fragment初始化在Retrofit请求完进行)
        getVideoChannelData();
    }

    private void initVideoItemFragment() {

        for (int i = 0; i < channelName.size(); i++) {
            Fragment fragment;
            if (i == 0) {
                fragment = new VideoJXItemFragment();
            } else {
                fragment = new VideoOtherItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.VIDEO_CHANNELID, channelTypeId.get(i));
                fragment.setArguments(bundle);
            }
            fragmentList.add(fragment);
        }
    }

    private void getVideoChannelData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ManagerApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoInterface videoInterface = retrofit.create(VideoInterface.class);
        Call<List<VideoEntity>> call = videoInterface.getVideoEntity(1);
        call.enqueue(new Callback<List<VideoEntity>>() {
            @Override
            public void onResponse(Call<List<VideoEntity>> call, Response<List<VideoEntity>> response) {
                if (response.body() != null) {
                    List<VideoEntity> testList = response.body();
                    List<VideoEntity.TypesEntity> test = testList.get(0).getTypes();
                    for (int i = 0; i < test.size(); i++) {
                        if (test.get(i).getId().contains(COMMON)) {
                            channelName.add(test.get(i).getName());
                            channelTypeId.add(test.get(i).getId());
                        }

                    }

                    initVideoItemFragment();
                    adpater.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<VideoEntity>> call, Throwable t) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
        setupViews(view);
    }

    private void setupViews(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.video_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.video_viewpager);


        viewPager.setAdapter(adpater);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {

        //处理视频模块切换，是否有视频正在播放。
        if (lastPostion == 0) {
            VideoJXItemFragment fragment = (VideoJXItemFragment) fragmentList.get(lastPostion);
            VideoItemAdapter itemAdapter = fragment.adapter;
            MediaPlayer mediaPlayer = itemAdapter.getMediaPlayer();
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
            }
            itemAdapter.setCurrentPlayerPosition(-1);
            itemAdapter.notifyDataSetChanged();

        } else {
            VideoOtherItemFragment fragment = (VideoOtherItemFragment) fragmentList.get(lastPostion);
            VideoItemAdapter itemAdapter = fragment.adapter;
            MediaPlayer mediaPlayer = itemAdapter.getMediaPlayer();

            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
            }
            itemAdapter.setCurrentPlayerPosition(-1);
            itemAdapter.notifyDataSetChanged();
        }

        lastPostion = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //处理tabhost切换到其他界面-->视频也要停止
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //处理视频模块切换，是否有视频正在播放。

        if (lastPostion == 0&&fragmentList.size()>0) {
            VideoJXItemFragment fragment = (VideoJXItemFragment) fragmentList.get(lastPostion);
            VideoItemAdapter itemAdapter = fragment.adapter;
            MediaPlayer mediaPlayer = itemAdapter.getMediaPlayer();
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
            }
            itemAdapter.setCurrentPlayerPosition(-1);


        } else if(lastPostion !=0&&fragmentList.size()>0){
            VideoOtherItemFragment fragment = (VideoOtherItemFragment) fragmentList.get(lastPostion);
            VideoItemAdapter itemAdapter = fragment.adapter;
            MediaPlayer mediaPlayer = itemAdapter.getMediaPlayer();
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.stop();
            }
            itemAdapter.setCurrentPlayerPosition(-1);

        }

        lastPostion = 0;
    }




    //最好在这里处理按home键和tabhost切换fragment，停止视频播放(可是为什么按了home就不会播放？)
    @Override
    public void onStop() {
        super.onStop();
//        LogUtils.MyLog("onStop  momo");

    }
}