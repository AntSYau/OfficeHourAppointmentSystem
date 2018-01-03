//author: qiu shi, zou kehan

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class dbClass {
    private String name;

    dbClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getStudents() {
        ArrayList<Integer> students = new ArrayList<Integer>();
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM stuClass WHERE `name`='" + name +
                "' ORDER BY `stuClass`.`studentid` ASC");
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
        ArrayList<Integer> teachers = new ArrayList<Integer>();
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE `name`='" + name +
                "' ORDER BY `sumClass`.`teacherid` ASC");
        try {
            while (rs.next()) {
                teachers.add(rs.getInt("teacherid"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return teachers;
    }

    public int setReservation(int studentid, int teacherid, java.util.Date startTime, java.util.Date endTime, String address) {
        LocalDate date = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime start = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime end = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        dbTeacher teacher = new dbTeacher(teacherid);
        if (teacher.isOccupied(date, start, end) == 0) {
            sqlCommands.sqlUpdate("INSERT INTO `appointments`(teacherID,studentID,date,timeStart,timeEnd,address,className) VALUES (" +
                    teacherid + "," + studentid + ",'" + date.toString() + "','" + start.toString() + "','" + end.toString() + "','" + address + "','" + name + "')");
            return 0;
        } else {
            return 1;
        }
    }

    public int setReservation(int studentid, int teacherid, LocalDate date, LocalTime startTime, LocalTime endTime, String address) {
        System.out.println();
        dbTeacher teacher = new dbTeacher(teacherid);
        if (teacher.isOccupied(date, startTime, endTime) == 0) {
            sqlCommands.sqlUpdate("INSERT INTO `appointments`(teacherID,studentID,date,timeStart,timeEnd,address,className) VALUES (" +
                    teacherid + "," + studentid + ",\"" + date.toString() + "\",'" + startTime.toString() +
                    "','" + endTime.toString() + "','" + address + "','" + name + "')");
        }
        return 0;
    }

    public int addStudent(int id) {
        return sqlCommands.sqlUpdate("INSERT INTO stuClass(studentid,name) VALUES (" + id + ",'" + name + "')");
    }

    public int addTeacher(int id) {
        return sqlCommands.sqlUpdate("INSERT INTO sumClass(teacherid,name) VALUES (" + id + ",'" + name + "')");
    }

    public int delTeacher(int id) {
        return sqlCommands.sqlUpdate("DELETE FROM sumClass WHERE teacherid=" + id + " AND name='" + name + "'");
    }

    public int delStudent(int id) {
        return sqlCommands.sqlUpdate("DELETE FROM stuClass WHERE studentid=" + id + " AND name='" + name + "'");
    }

    public boolean exist() {
        return !(name.equals("error"));
    }

    public void delete() {
        return;
    }

    public void setName(String name ) {
        sqlCommands.sqlUpdate("UPDATE stuClass VALUES `name`='"+name+"' WHERE `name`='"+this.name+"'");
        sqlCommands.sqlUpdate("UPDATE sumClass VALUES `name`='"+name+"' WHERE `name`='"+this.name+"'");
        sqlCommands.sqlUpdate("UPDATE appointments VALUES `className`='"+name+"' WHERE `className`='"+this.name+"'");
    }
}
