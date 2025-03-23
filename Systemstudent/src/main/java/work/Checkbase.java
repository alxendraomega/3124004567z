package work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Checkbase {


    public static void Checkbases() throws SQLException {
        Statement stmt=null;
        ResultSet rs=null;
        Scanner sc=new Scanner(System.in);
        Connection conn=getConn();
        stmt =conn.createStatement();
        String sql="select * from user";
        rs= stmt.executeQuery(sql);
        System.out.println("请输入密码");
        String password=sc.nextLine();
        System.out.println("--------所有用户信息--------");
        System.out.println("ID\t"+"\t"+"姓名\t"+"\t"+"\t"+"密码\t"+"\t"+"身份\t");
        while (rs.next()){
            String id = rs.getString("id");
            String numberless = rs.getString("numberuser");
            String pwd= rs.getString("password");
            String sort1 = rs.getString("sort");
            if(pwd.equals(password)){
            System.out.println(id+"\t"+"\t"+numberless+"\t"+"\t"+"\t"+pwd+"\t"+"\t"+sort1+"\t"+"\t"+sort1);
        }}
        closeDB(rs,stmt,conn);
        System.out.println("");
    }
}
