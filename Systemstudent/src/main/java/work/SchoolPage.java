package work;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static work.AddStudent.AddStudents;
import static work.Addcourse.Addcourses;
import static work.CheckAllStudent.CheckAllStudents;
import static work.CheckCcoursestu.CheckCcoursestus;
import static work.Checkbase.Checkbases;
import static work.Checkcourse.Checkcourses;
import static work.Checkstucourse.Checkstucourses;
import static work.Deletecourse.Deletecourses;
import static work.ModifyStudent.ModifyStudents;
import static work.Modifypwd.Modifypwds;
import static work.CeckCoisecour.CeckCoisecours;
import static work.Choisecour.Choisecours;
import static work.Deletemycourse.Deletemycourses;
import static work.Checkstuinfo.Checkstuinfos;
import static work.Checkmycourse.Checkmycourses;
public class SchoolPage {
    public SchoolPage() throws SQLException, IOException {
        System.out.println("欢迎使用-学生系统");
        while (true) {
            System.out.println("--------学生系统---------");
            System.out.println("1.查看可选课程");
            System.out.println("2.选择课程");
            System.out.println("3.退选课程");
            System.out.println("4.查询自己已选课程");
            System.out.println("5.修改密码");
            System.out.println("6.查看自己的基本信息");
            System.out.println("0.退出系统");
            System.out.println("--------------------------");
            System.out.println("请选择你需要的操作：");
            Scanner sc = new Scanner(System.in);
            String select = sc.nextLine();
            switch (select) {
                case "0":{System.out.println("谢谢使用学生系统！");
                    System.exit(0);break;}
                case "1": Checkcourses();break;
                case "2" :Choisecours();break;
                case "3": Deletemycourses();
                case "4": Checkmycourses();break;
                case "5": Modifypwds();break;
                case "6": Checkstuinfos();break;
                default: System.out.println("请输入正确的操作序号");

            }
        }}
}
