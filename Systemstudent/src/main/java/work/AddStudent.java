
package work;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;
public class AddStudent {
    /*添加学生*//*添加成功返回true*/

        public static  void AddStudents() throws SQLException, IOException {
            System.out.println("--------欢迎来到添加学生界面-----------");
            String number,name;
            int age;
            String classname,phonenumber;
            Scanner sc = new Scanner(System.in);
            System.out.println("请添加学生基本信息");
            System.out.println("请输入添加的学生学号:");
            number=sc.nextLine();
            System.out.println("请输入添加的学生姓名:");
            name=sc.nextLine();
            System.out.println("请输入添加的学生年龄:");
            age=sc.nextInt();
            System.out.println("请输入添加的学生班级:");
            classname=sc.next();
            System.out.println("请输入添加的学生手机号:");
            phonenumber=sc.next();
            System.out.println();
            Connection conn=getConn();
            String sql = "insert into stu(number,name,age,classname,phonenumber) values(?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,number);
            pst.setString(2,name);
            pst.setInt(3,age);
            pst.setString(4,classname);
            pst.setString(5,phonenumber);
            int i = pst.executeUpdate();
            closeDB(null,pst,conn);
            if(i!=0){
                System.out.println("学生添加成功");new HomePage();
            }
            else {
                System.out.println("学生添加失败");
            }
        }
    }

