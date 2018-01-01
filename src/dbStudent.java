import javax.swing.tree.ExpandVetoException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbStudent {
    private int id = 0;
    private String name = "null";

    dbStudent(int id) {
        this.id = id;
        name = getName();
    }

    String getName() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + id);
        try {
            rs.next();
            return rs.getString(3);
        } catch (java.lang.NullPointerException e) {
            System.out.println("We cannot identify this student by Student ID.");
            return "error";
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return "error";
        }
    }

    public String toString() {
        return name + " (" + id + ")";
    }

    // TODO 预约的上限（老师.3学生管理）

    public List<String> appQuery() { //TODO:筛选
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE studentID=" + id +
                " SORT BY `appointments`.`date` ASC, `appointments`.`timeStart` ASC");
        List<String> query = new ArrayList<String>();
        try {
            while (rs.next()) {
                query.add(rs.getDate("date") + "\t" + rs.getInt("timeStart") + "\t" +
                        rs.getInt("timeEnd") + "\t" +
                        (new dbTeacher(rs.getInt("teacherID")).getName()));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return query;
    }

    public void setAbsent(int appid) {
        sqlCommands.sqlUpdate("UPDATE appointments SET absent=1 WHERE `appointments`.`#`=" + appid);
    }

    public ArrayList<Classes> getClasses() {
        ArrayList<Classes> ls = new ArrayList<Classes>();
        ResultSet rs = sqlCommands.sqlQuery("SELECT classid FROM stuClass WHERE studentid=" + this.id);
        try {
            while (rs.next()) {
                ls.add(new Classes(rs.getInt("#")));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return ls;
    }

    boolean exist() {
        return !(name.equals("error"));
    }
}
