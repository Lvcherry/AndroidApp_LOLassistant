package com.ryan.newsapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.ryan.newsapp.R;
import com.ryan.newsapp.adapter.NewsViewPagerAdapter;
import com.ryan.newsapp.fragment.NewsItemFragment;
import com.ryan.newsapp.fragment.NewsAnnounceItemFragment;

public class NewsFragment extends Fragment implements View.OnClickListener{

    private List<String> titlesList ;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    private NewsViewPagerAdapter adapter;

    private ImageView iv_live,iv_search,iv_additem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titlesList = new ArrayList();
        titlesList.add("头条");
        titlesList.add("S8");
        titlesList.add("公告");
        titlesList.add("攻略");
        titlesList.add("社区");

        initNewsItemFragment();
        adapter = new NewsViewPagerAdapter(getContext(),getChildFragmentManager(),fragmentList,titlesList);
    }

    private void initNewsItemFragment() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < titlesList.size(); i++) {
            Fragment fragment;
            if(i%2==0) {
                fragment = new NewsItemFragment();
            }else{
                fragment = new NewsAnnounceItemFragment();
            }
            fragmentList.add(fragment);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //初始化控件
    private void setupViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.news_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
        iv_live = (ImageView) view.findViewById(R.id.news_live);
        iv_search = (ImageView) view.findViewById(R.id.news_search);
        iv_additem = (ImageView) view.findViewById(R.id.news_addmodule_iv);

        iv_live.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        iv_additem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.news_live:{
                //直播界面
                //Intent intent = new Intent(getActivity(), LiveActivity.class);
                //startActivity(intent);
            }break;
            //搜索界面
            case R.id.news_search:{
                //Intent intent = new Intent(getActivity(), SearchActivity.class);
                //startActivity(intent);
            }break;
            //加tablayout item
            case R.id.news_addmodule_iv:{
                //模拟添加tablayout item以及对应的viewpager的fragment,并跳到最后添加的fragment
                titlesList.add("RealMo");
                //fragmentList.add(new NewsSportItemFragment());
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(titlesList.size()-1);
            }break;
        }

    }
}
