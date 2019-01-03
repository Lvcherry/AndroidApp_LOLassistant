package com.ryan.newsapp.model;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;



//import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import static java.lang.Class.forName;

public class MySQLDbHelper {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://47.100.18.196:3306/LOLSquare?autoReconnect=true";
    private static final String myuser = "test";
    private static final String mypassword  = "test1";


    private static Connection connection;

    static{
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public  static Connection getConn() throws SQLException{

        connection = DriverManager.getConnection(url,myuser,mypassword);

        //DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url,myuser,mypassword);
        //driverManagerDataSource.setUrl("jdbc:mysql://47.100.18.196:3306/LOLSquare");
        //driverManagerDataSource.setUsername("test");
        //driverManagerDataSource.setPassword("test1");

        //return driverManagerDataSource.getConnection(myuser,mypassword);
       // MysqlDataSource mysqlDataSource = new MysqlDataSource();
      //  mysqlDataSource.setURL("jdbc:mysql://47.100.18.196:3306/LOLSquare?user=test&password=test1");
       // return mysqlDataSource.getConnection();
        return connection;
    }

    public static void closeConn(Connection connection){
        if(null!=connection){
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println("Close Connection Failed");
                e.printStackTrace();
            }
        }
    }

    /*
    //连接数据库
    public static Connection getConn() throws SQLException{
        /*Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,user,password);
            Log.i("链接数据库","远程连接成功");
            //Toast.makeText()
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
        System.out.println("Connecting database");
        try(Connection connection = DriverManager.getConnection(url,user,password)){
            System.out.println("database connected");
        }catch (SQLException e){
            throw new IllegalStateException("cannot connect the database!",e);
        }
        return DriverManager.getConnection(url,user,password);
    }
*/
    //关闭数据库
    public static void closeAll(Connection conn, PreparedStatement ps){
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //关闭数据库
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
