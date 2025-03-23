package work;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import static work.Checkcourse.Checkcourses;
import static work.Addcourse.Addcourses;
import static work.AddStudent.AddStudents;
import static work.CheckAllStudent.CheckAllStudents;
import static work.ModifyStudent.ModifyStudents;
import static work.Modifypwd.Modifypwds;
import static work.Checkbase.Checkbases;
import static work.Deletecourse.Deletecourses;
import static work.Checkstucourse.Checkstucourses;
import static work.CheckCcoursestu.CheckCcoursestus;
public class HomePage {

        public HomePage() throws SQLException, IOException {
            System.out.println("欢迎使用-学生管理系统");
            while (true) {
                System.out.println("--------学生管理系统---------");
                System.out.println("1.修改学生手机号/年龄");
                System.out.println("2.查看学生信息");
                System.out.println("3.查看课程信息");
                System.out.println("4.对课程增加");
                System.out.println("5.查询某一门课程的选课学生");
                System.out.println("6.查询某个学生的选课信息");
                System.out.println("7.修改密码");
                System.out.println("8.查看自己的基本信息");
                System.out.println("9.添加学生信息");
                System.out.println("10.删除课程信息");
                System.out.println("0.退出系统");
                System.out.println("--------------------------");
                System.out.println("请选择你需要的操作：");
                Scanner sc = new Scanner(System.in);
                String select = sc.nextLine();
                switch (select) {
                    case "0":{System.out.println("谢谢使用学生系统！");
                        System.exit(0);break;}
                    case "1": ModifyStudents();break;
                    case "2": CheckAllStudents();break;
                    case "3": Checkcourses();break;
                    case "4": Addcourses();break;
                    case "5": CheckCcoursestus();break;
                    case "6": Checkstucourses();break;
                    case "7": Modifypwds();break;
                    case "8": Checkbases();break;
                    case "9": AddStudents();break;
                    case "10": Deletecourses();break;
                    default: System.out.println("请输入正确的操作序号");

                }
            }}}


