package com.ryan.newsapp.model;

import android.util.Log;

import com.ryan.newsapp.adapter.LegendInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDbManager {

    private Connection conn=null;
    private PreparedStatement ps =null;
    private ResultSet rs=null;

    //DbManager对象
    public static MySQLDbManager mySQLDbManager=null;

    private MySQLDbManager(){

    }

    public static MySQLDbManager getDbManger(){
        if(mySQLDbManager==null){
            mySQLDbManager=new MySQLDbManager();
        }
        return mySQLDbManager;
    }

    public LegendInfo getData(String string){
        LegendInfo legendInfo=null;

        String sql = "select * from champion_info where legend_name=?";

        try{
            conn = MySQLDbHelper.getConn();

            if(conn!=null&&(!conn.isClosed())){
                ps=(PreparedStatement)conn.prepareStatement(sql);
                ps.setString(1,string);
                if(ps!=null){
                    rs=ps.executeQuery();
                    if(rs!=null){
                        legendInfo  = new LegendInfo(rs.getString("legend_name"),rs.getString("legend_true_name"),rs.getString("ability_q"),rs.getString("ability_q_more"),rs.getString("ability_w"),rs.getString("ability_w_more"),rs.getString("ability_e"),rs.getString("ability_e_more"),rs.getString("ability_r"),rs.getString("ability_r_more"),rs.getString("ability_talent"),rs.getString("ability_talent_more"),rs.getString("legend_story"));

                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        MySQLDbHelper.closeAll(conn,ps,rs);
        return legendInfo;
    }

}
