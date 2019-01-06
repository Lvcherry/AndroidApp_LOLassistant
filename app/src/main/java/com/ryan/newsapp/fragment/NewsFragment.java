package com.ryan.newsapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.ryan.newsapp.R;
import com.ryan.newsapp.adapter.LegendNewsAdapter;
import com.ryan.newsapp.adapter.ListViewItem;
import com.ryan.newsapp.adapter.NewsAdapter;
import com.ryan.newsapp.adapter.NewsViewPagerAdapter;
import com.ryan.newsapp.fragment.NewsItemFragment;
import com.ryan.newsapp.fragment.NewsAnnounceItemFragment;
import com.ryan.newsapp.model.News;
import com.ryan.newsapp.ui.BrowseNewsActivity;
import com.ryan.newsapp.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/*
    资讯页面fragment
 */
public class NewsFragment extends Fragment implements View.OnClickListener{

    private List<String> titlesList ;
    private List<ListViewItem> newList;
    private ListView newListView;

    private BaseAdapter baseAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    private NewsViewPagerAdapter adapter;

    private ImageView iv_live,iv_search,iv_additem;

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


        // viewPager.setAdapter(adapter);
        // tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titlesList = new ArrayList();
        titlesList.add("头条");
        titlesList.add("S8");
        titlesList.add("公告");
        titlesList.add("攻略");
        titlesList.add("社区");


        init();
        //initNewsItemFragment();
        //adapter = new NewsViewPagerAdapter(getContext(),getChildFragmentManager(),fragmentList,titlesList);

        newListView = (ListView) getActivity().findViewById(R.id.lvNews);
        final LegendNewsAdapter  legendNewsAdapter = new LegendNewsAdapter(getContext(),newList);
        newListView.setAdapter(legendNewsAdapter);

        setListener();


    }


    private void setListener(){
         //final WebView webView = getView().findViewById(R.id.webView);

        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                switch (position){
                    case 0:{
                        Uri uri = Uri.parse("https://lol.qq.com/news/detail.shtml?docid=605138577224278138");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                        }
                        break;
                    case 1:{
                        Uri uri = Uri.parse("https://lol.qq.com/v/v2/detail.shtml?docid=510055603129601417");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                    case 2:{
                        Uri uri = Uri.parse("https://lol.qq.com/news/detail.shtml?docid=11562116428336738179");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                    case 3:{
                        Uri uri = Uri.parse("http://bbs.lol.qq.com/forum.php?mod=viewthread&tid=4643029");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                    case 4:{
                        Uri uri = Uri.parse("https://lol.qq.com/news/detail.shtml?docid=3713754086687503507");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                    case 5:{
                        Uri uri = Uri.parse("https://lol.qq.com/news/detail.shtml?docid=8080885356997070226");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                    case 6:{
                        Uri uri = Uri.parse("https://lpl.qq.com/es/act/a20181126transfer/index.html");
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);



                    }

                }
            }
        });
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


    //初始化控件
    private void setupViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.news_tablayout);
        //viewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
        iv_live = (ImageView) view.findViewById(R.id.news_live);
        iv_search = (ImageView) view.findViewById(R.id.news_search);
        iv_additem = (ImageView) view.findViewById(R.id.news_addmodule_iv);



        iv_live.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        iv_additem.setOnClickListener(this);

    }

    private void init(){
        newList = new ArrayList<ListViewItem>();
        int[] resImags = {R.drawable.news_match,R.drawable.news_video,R.drawable.news_teach,R.drawable.news_port,
                R.drawable.news_announce, R.drawable.news_joy,R.drawable.news_news};
        String[] names = getResources().getStringArray(R.array.legend_news);
        for(int i=0;i<resImags.length;i++){
            ListViewItem listViewItem = new ListViewItem(resImags[i],names[i]);
            newList.add(listViewItem);
        }
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
