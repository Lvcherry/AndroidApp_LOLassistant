package com.ryan.newsapp.news_interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.ryan.newsapp.entity.VideoEntity;
import com.ryan.newsapp.utils.ManagerApi;

public interface VideoInterface {

    @GET(ManagerApi.VIDEO_JX)
    Call<List<VideoEntity>> getVideoEntity(@Query("page")int page);


    @GET(ManagerApi.VIDEO_OTHER)
    Call<List<VideoEntity>> getVideoOtherEntity(@Query("page")int page
            ,@Query("typeid")String typeid);
}
