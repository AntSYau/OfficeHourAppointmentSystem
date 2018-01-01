import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbClass {
    private String id;
    private List<Integer> students = new ArrayList<Integer>();
    private List<Integer> teachers = new ArrayList<Integer>();

    dbClass(String id) {
        this.id = id;
    }

    public List<Integer> getStudents() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM stuClass WHERE classid=" + id +
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

    public List<Integer> getTeachers() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE classid=" + id +
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

    public void delete() {
        return;
    }
}
