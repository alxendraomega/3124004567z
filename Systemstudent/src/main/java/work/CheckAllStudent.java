package work;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;
public class CheckAllStudent {
    /*查看学生*/
        public static void CheckAllStudents() throws SQLException {
            int i=1;
            Statement stmt=null;
            ResultSet rs=null;
            Connection conn=getConn();
            stmt =conn.createStatement();
            String sql="select * from stu";
            rs= stmt.executeQuery(sql);
            System.out.println("--------所有学生信息--------");
            System.out.println("学号\t"+"\t"+"姓名\t"+"\t"+"\t"+ "年龄\t"+"\t"+"班级\t"+"\t"+"手机号\t");
            while (rs.next()){
                String number = rs.getString("number");
                String name = rs.getString("name");
                int age= rs.getInt("age");
                String classname = rs.getString("classname");
                String phonenumber = rs.getString("phonenumber");
                System.out.println(number+"\t"+"\t"+name+"\t"+"\t"+age+"\t"+"\t"+classname+"\t"+"\t"+phonenumber);
            }
            closeDB(rs,stmt,conn);
            System.out.println("");
        }
    }

