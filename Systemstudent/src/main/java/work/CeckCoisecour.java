package work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static work.JDBCUtill.closeDB;
import static work.JDBCUtill.getConn;

public class CeckCoisecour {
    public static void CeckCoisecours() throws SQLException {
        Statement stmt=null;
        ResultSet rs=null;
        Connection conn=getConn();
        stmt =conn.createStatement();
        String sql="select * from students.course";
        rs= stmt.executeQuery(sql);
        System.out.println("--------所有课程信息--------");
        System.out.println("课程名\t"+"\t"+"天\t"+"\t"+"\t"+"时间\t"+"\t"+"\t"+"\t"+"地点\t");
        while (rs.next()){
            String classname = rs.getString("name");
            String day = rs.getString("day");
            String time = rs.getString("time");
            String  local= rs.getString("local");
            //String stuname = rs.getString("stuname");
            System.out.println(classname+"\t"+"\t"+"\t"+day+"\t"+"\t"+ time +"\t"+"\t"+local+"\t");
        }
        closeDB(rs,stmt,conn);
        System.out.println("");
    }
}
