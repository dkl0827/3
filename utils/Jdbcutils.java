package lesson.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Jdbcutils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    static{
        try {
            InputStream in = Jdbcutils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(url,username,password);

    }//链接
    public static void release(Connection conn, Statement stmt, ResultSet rt){
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(rt!=null){
            try{
                rt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
