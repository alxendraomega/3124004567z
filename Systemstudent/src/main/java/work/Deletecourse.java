package work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Deletecourse {
    /*删除课程*/
        public static void Deletecourses() throws SQLException, IOException {
            String courname;
            Scanner sc = new Scanner(System.in);
            System.out.println("--------欢迎来到删除课程界面-----------");
            System.out.println("请输入要删除课程的名字：");
            courname=sc.nextLine();
            Connection conn=getConn();
            String sql = "delete from course where name=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,courname);
            int i = pst.executeUpdate();
            closeDB(null,pst,conn);
            if (i!=0){
                System.out.println("课程为"+courname+"删除成功"); new HomePage();
            }


        }
    }

