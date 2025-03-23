package work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Deletemycourse {
    public static void Deletemycourses() throws SQLException, IOException {
        System.out.println("欢迎来到退选界面");
        int id;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------欢迎来到删除课程界面-----------");
        System.out.println("请输入要删除课程的ID：");
        id=sc.nextInt();
        Connection conn=getConn();
        String sql = "delete from ccourse where id=?";
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setInt(1,id);
        int i = pst.executeUpdate();
        closeDB(null,pst,conn);
        if (i!=0){
            System.out.println("课程ID为"+id+"删除成功"); new SchoolPage();
        }


    }
}
