import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbClass {
    private int id;
    private List<Integer> students = new ArrayList<Integer>();
    private List<Integer> teachers = new ArrayList<Integer>();

    dbClass(int id) {
        this.id = id;
    }

    public void getStudents() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM stuClass WHERE classid=" + id +
                " ORDER BY `stuClass`.`studentid` ASC");
        try {
            while (rs.next()) {
                students.add(rs.getInt("studentid"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }

    public void getTeachers() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE classid=" + id +
                " ORDER BY `sumClass`.`teacherid` ASC");
            try {
            while (rs.next()) {
                teachers.add(rs.getInt("teacherid"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }

    public void delete() {
        return;
    }
}
