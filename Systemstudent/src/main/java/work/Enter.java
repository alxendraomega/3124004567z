package work;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Enter {
        public Enter() throws SQLException, IOException {
            System.out.println("--欢迎使用---学生管理系统--");
            System.out.println("-------欢迎来到登录页------");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("请选择你需要的操作：");
            Scanner sc = new Scanner(System.in);
            String select=sc.nextLine();
            switch (select){
                case "1":new Login();break;
                case "2":new Register();break;
                default: System.out.println("请输入正确的操作序号");new Enter();
            }
        }
    }
