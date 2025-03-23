package work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Addcourse {
    public static  void Addcourses() throws SQLException, IOException {
        System.out.println("--------欢迎来到添加课程界面-----------");
        String coursename,day;
        String time;
        String local;
        Scanner sc = new Scanner(System.in);
        System.out.println("请添加课程基本信息");
        System.out.println("请输入添加的课程名字:");
        coursename=sc.nextLine();
        System.out.println("请输入添加的课程的天:");
        day=sc.nextLine();
        System.out.println("请输入添加的课程时间:");
        time=sc.nextLine();
        System.out.println("请输入添加的上课地点:");
        local=sc.next();
        System.out.println();
        Connection conn=getConn();
        String sql = "insert into course(name,day,time,local) values(?,?,?,?)";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1,coursename);
        pst.setString(2,day);
        pst.setString(3,time);
        pst.setString(4,local);

        int i = pst.executeUpdate();
        closeDB(null,pst,conn);
        if(i!=0){
            System.out.println("课程添加成功");new HomePage();
        }
        else {
            System.out.println("课程添加失败");
        }
    }
}
