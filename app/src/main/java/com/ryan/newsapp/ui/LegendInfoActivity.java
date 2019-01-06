package com.ryan.newsapp.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.newsapp.R;
import com.ryan.newsapp.adapter.LegendInfo;
import com.ryan.newsapp.adapter.LegendPics;
import com.ryan.newsapp.fragment.ReadFragment;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
    英雄详细信息界面页
    连接MySQL数据库
    执行相关查询操作
    刷新用户界面
 */
public class LegendInfoActivity extends AppCompatActivity {



    final Handler myHandler = new Handler();
    private LegendInfo legendInfo = null;
    private LegendPics legendPics = null;
    private TextView legend_name ;
    private TextView legend_true_name ;
    private String ability_q_more ;
    private String ability_w_more ;
    private String ability_e_more ;
    private String ability_r_more ;
    private String ability_talent_more;
    private TextView ability_more;
    private TextView legend_ability;
    private TextView legend_story_title;
    private TextView legend_strategy_title;
    private TextView win_rate;
    private TextView best_rune;
    private TextView ability_chart;
    private ImageView image_q;
    private ImageView image_w;
    private ImageView image_e;
    private ImageView image_r;
    private ImageView image_talent;
    private ImageView image_head;
    private ImageView imageWinRate;
    private ImageView imageBestRune;
    private ImageView imageAbility;
    private TextView legend_story ;

    Connection conn = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    ResultSet rs=null;
    ResultSet rs1 = null;

    private  static String TAG= "LegendInfoActivity";
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_information);

        legend_name = (TextView) findViewById(R.id.legend_name);
        legend_true_name = (TextView) findViewById(R.id.legend_true_name);
        legend_story = (TextView) findViewById(R.id.legend_story);
        ability_more = findViewById(R.id.legend_ability_more);
        image_q = findViewById(R.id.legend_ability_Q);
        image_w = findViewById(R.id.legend_ability_W);
        image_e = findViewById(R.id.legend_ability_E);
        image_r = findViewById(R.id.legend_ability_R);
        image_talent = findViewById(R.id.legend_ability_talent);
        image_head = findViewById(R.id.legend_img);
        imageAbility = findViewById(R.id.ability_chart_img);
        imageBestRune = findViewById(R.id.best_rune_img);
        imageWinRate = findViewById(R.id.win_rate_img);
        legend_ability = findViewById(R.id.legend_ability);
        legend_story_title = findViewById(R.id.legend_story_title);
        legend_strategy_title = findViewById(R.id.legend_strategy_title);
        best_rune = findViewById(R.id.best_rune);
        win_rate = findViewById(R.id.win_rate);
        ability_chart = findViewById(R.id.ability_chart);


        loadText();

        image_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ability_more.setText(ability_q_more);
            }
        });
        image_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ability_more.setText(ability_w_more);
            }
        });
        image_e.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ability_more.setText(ability_e_more);
            }
        });
        image_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ability_more.setText(ability_r_more);
            }
        });
        image_talent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ability_more.setText(ability_talent_more);
            }
        });




    }

    final Runnable updateText = new Runnable() {
        @Override
        public void run() {
            updateTextUI();
        }
    };

    private void updateTextUI(){
        legend_name.setText(legendInfo.legend_name);
        legend_true_name.setText(legendInfo.legend_true_name);

        ability_q_more = "Q技能："+legendInfo.ability_q +";"+legendInfo.ability_q_more;

        ability_w_more="W技能："+legendInfo.ability_q+";"+legendInfo.ability_w_more;
        ability_e_more="E技能："+legendInfo.ability_e+";"+legendInfo.ability_e_more;
        ability_r_more="R技能："+legendInfo.ability_r+";"+legendInfo.ability_r_more;
        ability_talent_more="被动技能："+legendInfo.ability_talent+";"+legendInfo.ability_talent_more;
        legend_story.setText(legendInfo.legend_story);


        legend_strategy_title.setText("英雄玩法");
        win_rate.setText("胜率-时间图");
        best_rune.setText("最佳符文推荐");
        legend_story_title.setText("英雄背景故事");
        ability_chart.setText("英雄能力分布图");
        legend_ability.setText("技能描述");


        image_q.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_q_pic,0,legendPics.ability_q_pic.length));
        image_w.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_w_pic,0,legendPics.ability_w_pic.length));
        image_e.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_e_pic,0,legendPics.ability_e_pic.length));
        image_r.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_r_pic,0,legendPics.ability_r_pic.length));
        image_talent.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_passive_pic,0,legendPics.ability_passive_pic.length));
        image_head.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.head_pic,0,legendPics.head_pic.length));
        imageWinRate.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.time_win_rate,0,legendPics.time_win_rate.length));
        imageBestRune.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.best_rune,0,legendPics.best_rune.length));
        imageAbility.setImageBitmap(BitmapFactory.decodeByteArray(legendPics.ability_chart,0,legendPics.ability_chart.length));

    }


    protected void loadText(){
        final Thread thread = new  Thread(new Runnable(){
            @Override
            public void run(){

                try{
                    Thread.sleep(100);
            }catch (InterruptedException e){
                Log.e(TAG,e.toString());
            }

                try{
                    conn = DriverManager.getConnection("jdbc:mysql://47.101.145.218:3306/lolsquare?autoReconnect=true&useSSL=false","root","123456");
                    Log.i(TAG,"远程连接成功");
                }catch (SQLException e){
                    Log.e(TAG,"远程连接失败");
                }

                if(conn!=null){
                    String sql = "select * from champion_info where legend_name=?";
                    String sql1 = "select * from champion_pics where champion_name=?";
                    try{
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, ReadFragment.legend_name);
                        rs = ps.executeQuery();

                        while(rs.next()){
                            legendInfo = new LegendInfo(rs.getString("legend_name"), rs.getString("legend_true_name"), rs.getString("ability_q"), rs.getString("ability_q_more"), rs.getString("ability_w"), rs.getString("ability_w_more"), rs.getString("ability_e"), rs.getString("ability_e_more"), rs.getString("ability_r"), rs.getString("ability_r_more"), rs.getString("ability_talent"), rs.getString("ability_talent_more"), rs.getString("legend_story"));
                        }
                    }catch (SQLException e){
                        Log.e(TAG,"创建对象发生错误");
                    }

                    try{
                        ps1 = conn.prepareStatement(sql1);
                        ps1.setString(1,ReadFragment.legend_name);
                        rs1 = ps1.executeQuery();
                        while(rs1.next()){
                            InputStream inAQ = rs1.getBinaryStream("ability_q");
                            InputStream inAW = rs1.getBinaryStream("ability_w");
                            InputStream inAE = rs1.getBinaryStream("ability_e");
                            InputStream inAR = rs1.getBinaryStream("ability_r");
                            InputStream inAT = rs1.getBinaryStream("ability_passive");
                            InputStream inHP = rs1.getBinaryStream("head_profit");
                            InputStream inBR = rs1.getBinaryStream("best_rune");
                            InputStream inBC = rs1.getBinaryStream("ability_chart");
                            InputStream inTW = rs1.getBinaryStream("time_win_rate");
                            legendPics = new LegendPics(readStream(inAQ),readStream(inAW),readStream(inAE),readStream(inAR),readStream(inAT),readStream(inHP),readStream(inBR),readStream(inBC),readStream(inTW));

                        }
                    }catch (SQLException e){
                        Log.e(TAG,"创建图片对象发生错误");
                    }catch (Exception e){
                        Log.e(TAG,"整合发生错误");
                    }


                    try{
                        conn.close();
                    }catch (SQLException e){
                        Log.e(TAG,"关闭连接失败");
                    }
                }
                myHandler.post(updateText);

            }
        });
        thread.start();
    }


    public static byte[] readStream(InputStream inputStream)throws Exception{
        byte[] buf   = new byte[1024];
        int len=-1;
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        while((len=inputStream.read(buf))!=-1){
            outputStream.write(buf,0,len);

        }
        byte[] data = outputStream.toByteArray();
        outputStream.close();
        inputStream.close();
        return data;
    }



}
