package lesson;

import lesson.utils.Jdbcutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Text {
    public static void main(String[] args){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rt=null;
        System.out.println("****************************");
        System.out.println("1.添加学生信息");
        System.out.println("2.删除学生信息");
        System.out.println("3.查询学生信息");
        System.out.println("4.查看学生信息");
        System.out.println("5.查询总分低于180的学生");
        System.out.println("****************************");
        Scanner s = new Scanner(System.in);
        int a=s.nextInt();
        try{
        conn = Jdbcutils.getConnection();
        stmt= conn.createStatement();
            switch (a){
                case 1:{
                    String sql=Ins();
                    int i=stmt.executeUpdate(sql);
                    System.out.println("插入成功");
                    break;
                }
                case 2:{
                    String sql=Dete();
                    int i=stmt.executeUpdate(sql);
                    System.out.println("删除成功");
                    break;
                }
                case 3:{
                    String sql=Upd();
                    int i=stmt.executeUpdate(sql);
                    System.out.println("更新成功");
                    break;
                }
                case 4:{
                    String sql=See();
                    rt=stmt.executeQuery(sql);
                    while(rt.next()){
                        System.out.println("id="+rt.getObject("id"));
                        System.out.println("name="+rt.getObject("name"));
                        System.out.println("math="+rt.getObject("math"));
                        System.out.println("eng="+rt.getObject("eng"));
                        System.out.println("ch="+rt.getObject("ch"));
                    }
                    System.out.println("显示成功");
                    break;
                }
                case 5:{
                    String sql=Seek();
                    rt=stmt.executeQuery(sql);
                    while(rt.next()){
                        System.out.println("name="+rt.getObject("name"));
                        System.out.println("math="+rt.getObject("math"));
                        System.out.println("eng="+rt.getObject("eng"));
                        System.out.println("ch="+rt.getObject("ch"));
                    }
                    System.out.println("查询成功");
                    break;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            Jdbcutils.release(conn,stmt,rt);
        }
    }
    public static String Ins(){
        String n="INSERT INTO stu(`id`,`name`,`math`,`eng`,`ch`)\n" +
                "VALUES('2023','kuang','100','110','130')";
        return n;
    }
    public  static String Dete(){
        String n="DELETE FROM stu WHERE id=2021";
        return n;
    }
    public static String Upd(){
        String n="UPDATE stu SET `name`='hhh',`math`='150',`eng`='150',`ch`='150'WHERE id=2022";
        return n;
    }
    public static String See(){
        String n="select * from `stu`";
        return n;
    }
    public static String Seek(){
        String n="SELECT DISTINCT `math`,`eng`,`ch`,`name` FROM stu\n" +
                "WHERE `math`+`eng`+`ch`<=180";
        return n;
    }



}
