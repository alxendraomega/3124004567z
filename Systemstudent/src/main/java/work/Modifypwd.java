package work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class Modifypwd {
    public static void Modifypwds() throws SQLException, IOException {
        String select;
        String name;
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到修改界面系统");
        System.out.println("请输入要修改的用户名");
        name= sc.nextLine();
        System.out.println("1.修改密码");
        System.out.println("2.返回上一级");
        System.out.println("--------------------------");
        System.out.println("请选择你需要的操作：");
        sc.nextLine();
        select = sc.nextLine();
        switch (select) {
            case "1":
                ModifyStudentOfPWD(name);
                break;
            case "2":
                new HomePage();
                break;
            default:
                System.out.println("请输入正确的操作序号");
        }



    }


    private static void ModifyStudentOfPWD(String name) throws SQLException, IOException {
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的密码：");
        password = sc.nextLine();
        Connection conn = getConn();
        String sql = "update user set password=? where numberuser=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, password);
        pst.setString(2, name);
        int i = pst.executeUpdate();
        if (i != 0) {
            System.out.println("修改成功");
            closeDB(null,pst,conn);
            new HomePage();
        }
    }
}
