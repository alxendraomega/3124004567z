package work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Checkstuinfo {
    public static void Checkstuinfos() throws SQLException {
        Statement stmt=null;
        ResultSet rs=null;
        Scanner sc=new Scanner(System.in);
        Connection conn=getConn();
        stmt =conn.createStatement();
        String sql="select * from stu";
        rs= stmt.executeQuery(sql);
        System.out.println("请输入《你的名字》");
        String stuname=sc.nextLine();
        System.out.println("--------你的信息--------");
        System.out.println("学号\t"+"\t"+"姓名\t"+"\t"+"\t"+ "年龄\t"+"\t"+"班级\t"+"\t"+"手机号\t");
        while (rs.next()){
            String number = rs.getString("number");
            String name = rs.getString("name");
            int age= rs.getInt("age");
            String classname = rs.getString("classname");
            String phonenumber = rs.getString("phonenumber");
            if(stuname.equals(name)){
                System.out.println(number+"\t"+"\t"+name+"\t"+"\t"+age+"\t"+"\t"+classname+"\t"+"\t"+phonenumber);
            }}
        closeDB(rs,stmt,conn);
        System.out.println("");
    }
}
