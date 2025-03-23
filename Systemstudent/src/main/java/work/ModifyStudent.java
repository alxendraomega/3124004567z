package work;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class ModifyStudent {
    /*通过学号去修改年龄或省份*/
        public static void ModifyStudents() throws SQLException, IOException {
            String select;
            String number;
            Scanner sc = new Scanner(System.in);
            System.out.println("欢迎来到修改界面系统");
            System.out.println("请输入要修改的学生学号");
            number = sc.nextLine();
            System.out.println("1.修改学生年龄");
            System.out.println("2.修改学生手机号");
            System.out.println("3.返回上一级");
            System.out.println("--------------------------");
            System.out.println("请选择你需要的操作：");
            select = sc.nextLine();
            switch (select) {
                case "1":
                    ModifyStudentOfAge(number);
                    break;
                case "2":
                    ModifyStudentOfPhonenumber(number);
                    break;
                case "3":
                    new HomePage();
                    break;
                default:
                    System.out.println("请输入正确的操作序号");
            }



        }


        private static void ModifyStudentOfAge(String number) throws SQLException, IOException {
            int age;
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要修改的年龄：");
            age = sc.nextInt();
            Connection conn = getConn();
            String sql = "update stu set age=? where number=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, age);
            pst.setString(2, number);
            int i = pst.executeUpdate();
            if (i != 0) {
                System.out.println("修改成功");
                closeDB(null,pst,conn);
                new HomePage();
            }
        }

        private static void ModifyStudentOfPhonenumber(String number) throws SQLException, IOException {
            String phonenumber;
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要修改的手机号：");
            phonenumber = sc.nextLine();
            Connection conn = getConn();
            String sql = "update stu set phonenumber=? where number=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, phonenumber);
            pst.setString(2, number);
            int i = pst.executeUpdate();
            if (i != 0) {
                System.out.println("修改成功");
                closeDB(null,pst,conn);
                new HomePage();
            }
            else{System.out.println("false");}

        }}


