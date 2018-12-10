package com.ryan.newsapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment{

    private List<String> channelName;
    private List<String> channelTypeId;

    private List<Fragment> fragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        channelName = new ArrayList<>();

        channelTypeId = new ArrayList<>();

        channelName.add("精选");
        channelTypeId.add("JX");

        fragmentList = new ArrayList<>();

        initVideoItemFragment();
    }

    private void initVideoItemFragment() {

        for (int i = 0; i < channelName.size(); i++) {
            Fragment fragment;
            if (i == 0) {
                fragment = new VideoJXItemFragment();
            } else {
                fragment = new VideoOtherItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("video_channelId", channelTypeId.get(i));
                fragment.setArguments(bundle);
            }
            fragmentList.add(fragment);
        }
    }
}
