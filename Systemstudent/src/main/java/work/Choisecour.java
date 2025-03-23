package work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Choisecour {
    public static  void Choisecours() throws SQLException, IOException {
        System.out.println("--------欢迎来到选择课程界面-----------");
        String studentname,courname;
        String teachername;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的名字:");
        studentname=sc.nextLine();
        System.out.println("请输入选择的课程的名字:");
        courname=sc.nextLine();
        System.out.println("请输入选择的课程老师:");
        teachername=sc.nextLine();
        System.out.println();
        Connection conn=getConn();
        String sql = "insert into ccourse(studentname,coursename,courseteac) values(?,?,?)";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1,studentname);
        pst.setString(2,courname);
        pst.setString(3,teachername);
        int i = pst.executeUpdate();
        closeDB(null,pst,conn);
        if(i!=0){
            System.out.println("课程选择成功");new SchoolPage();
        }
        else {
            System.out.println("课程选择失败");
        }
    }
}
