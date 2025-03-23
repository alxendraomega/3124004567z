package work;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;
public class Register {
    /*注册*/
        public Register() throws SQLException, IOException {
            String name;
            String password;
            String psdagain;
            String sort;
            Scanner sc = new Scanner(System.in);
            System.out.println("---------欢迎来到注册页面---------");
            System.out.println("请输入注册的用户名：");
            name=sc.nextLine();
            System.out.println("请输入设定的密码：");
            password=sc.next();
            System.out.println("请确定密码");
            psdagain=sc.next();
            System.out.println("请选择身份");
            sort=sc.next();
            if(password.equals(psdagain)){
            Connection conn=getConn();
            String sql = "insert into user(numberuser,password,sort) values(?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,password);
            pst.setString(3,sort);
            int i = pst.executeUpdate();
            if (i!=0){
                    System.out.println("用户添加成功，返回主页");new Enter();
                }
                else {
                    System.out.println("请重新操作");new Register();
                }}
            }}



