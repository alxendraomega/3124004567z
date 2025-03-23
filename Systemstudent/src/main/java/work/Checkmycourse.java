package work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Checkmycourse {
    public static void Checkmycourses() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Statement stmt=null;
        ResultSet rs=null;
        Connection conn=getConn();
        stmt =conn.createStatement();
        String sql="select * from ccourse";
        rs= stmt.executeQuery(sql);
        System.out.println("请输入自己的名字");
        String studentname=sc.nextLine();
        System.out.println("--------学生所有选课信息--------");
        System.out.println("");
        System.out.println("ID\t"+"\t"+"姓名\t"+"\t"+"\t"+"课程\t"+"\t"+"课程老师\t");
        while (rs.next()){
            String id = rs.getString("id");
            String stuname = rs.getString("studentname");
            String coursename = rs.getString("coursename");
            String courseteacher = rs.getString("courseteac");

            if(studentname.equals(stuname)){
                System.out.println(id+"\t"+"\t"+stuname+"\t"+"\t"+"\t"+coursename+"\t"+"\t"+courseteacher+"\t");
            }}
        closeDB(rs,stmt,conn);
        System.out.println("");
    }
}
