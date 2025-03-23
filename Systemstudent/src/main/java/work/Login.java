package work;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;
public class Login {

        public Login() throws SQLException, IOException {
            String name;
            String password;
            String sort;
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println("-------------------------------");
                System.out.println("--欢迎使用---学生管理系统--");
                System.out.println("-------------请登录-------------");
                System.out.println("请输入用户名:");
                name=sc.nextLine();
                System.out.println("请输入密码:");
                password=sc.nextLine();
                System.out.println("请输入身份");
                sort=sc.nextLine();
                Connection conn=getConn();
                Statement stat=conn.createStatement();
                String sql="select * from user";
                ResultSet rs= stat.executeQuery(sql);
                while (rs.next()){
                    String UserName = rs.getString("numberuser");
                    String UserPass = rs.getString("password");
                    String UserSort = rs.getString("sort");
                    if (name.equals(UserName)&&password.equals(UserPass)&&sort.equals("学生")){

                            System.out.println("学生登录成功");
                            new SchoolPage();
                        closeDB(rs,stat,conn);
                        break;
                    }
                    else if(name.equals(UserName)&&password.equals(UserPass)&&sort.equals("老师")){

                        System.out.println("老师登录成功");
                        new HomePage();
                        closeDB(rs,stat,conn);break;
                    }

                    }
                }

                }
        }


        /*public  static boolean LoginCheck(String name,String pass) throws SQLException {
            Connection conn=getConn();
            Statement stat=conn.createStatement();
            String sql="select * from user";
            ResultSet rs= stat.executeQuery(sql);
            while (rs.next()){
                String UserName = rs.getString("numberuser");
                String UserPass = rs.getString("password");
                if (name.equals(UserName)&&pass.equals(UserPass)){
                    closeDB(rs,stat,conn);return true;
                }
            }
            closeDB(rs,stat,conn);
            System.out.println("mysql里面没有相关账户");
            return false;
        }*/




