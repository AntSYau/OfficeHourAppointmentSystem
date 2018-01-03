import com.sun.org.apache.regexp.internal.RE;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbClass {
    private String name;
    private int id;
    private ArrayList<Integer> students = new ArrayList<Integer>();
    private ArrayList<Integer> teachers = new ArrayList<Integer>();

    dbClass(int id) {
        this.id = id;
        name = getName();
    }

    dbClass(String name) {
        this.name = name;
        id = getID();
    }

    public int getID() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE classname=" + name);
        try {
            rs.next();
            return rs.getInt("#");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return 0;
        }
    }

    public String getName() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE #=" + id);
        try {
            rs.next();
            return rs.getString("classname");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return "error";
        }
    }

    public ArrayList<Integer> getStudents() {
        students = null;
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM stuClass WHERE #=" + id +
                " ORDER BY `stuClass`.`studentid` ASC");
        try {
            while (rs.next()) {
                students.add(rs.getInt("studentid"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return students;
    }

    public ArrayList<Integer> getTeachers() {
        teachers = null;
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE #=" + id +
                " ORDER BY `sumClass`.`teacherid` ASC");
        try {
            while (rs.next()) {
                teachers.add(rs.getInt("teacherid"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return teachers;
    }

    public int setReservation(int studentid, int teacherid, Date date, int startTime, int endTime) {
        dbTeacher teacher = new dbTeacher(teacherid);
        if (teacher.isOccupied(date, startTime, endTime) == 0) {
            sqlCommands.sqlUpdate("INSERT INTO `appointments`(teacherID,studentID,date,timeStart,timeEnd) VALUES (" +
                    teacherid + "," + studentid + ",\"" + date.toString() + "\"," + startTime + "," + endTime + ")");
        }
        return 0;
    }

    public int addStudent(int id) {
        return sqlCommands.sqlUpdate("INSERT INTO stuClass(studentid,classid) VALUES (" + id + "," + this.id + ")");
    }

    public boolean exist() {
        return !(name.equals("error")||id==0);
    }

    public void delete() {
        return;
    }
}
