import com.sun.org.apache.regexp.internal.RE;

import javax.swing.tree.ExpandVetoException;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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
            System.out.println("We cannot identify this uiStudent by Student ID.");
            return "error";
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return "error";
        }
    }

    public String toString() {
        return name + " (" + id + ")";
    }

    int getReservationChance() {
        Calendar calendar = Calendar.getInstance();
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE studentID=" + id + " AND year(`date`)=" +
                calendar.get(Calendar.YEAR) + " AND month(`date`)=" + calendar.get(Calendar.MONTH));
        int count = 0;
        try {
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return count;
    }

    int getReservationTime() {
        Calendar calendar = Calendar.getInstance();
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE studentID=" + id + " AND year(`date`)=" +
                calendar.get(Calendar.YEAR) + " AND month(`date`)=" + calendar.get(Calendar.MONTH));
        int count = 0;
        Time start;
        Time end;
        try {
            while (rs.next()) {
                start = rs.getTime("timeStart");
                end = rs.getTime("timeEnd");
                count += ((end.getTime() / 60000) - (start.getTime() / 60000));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return count;
    }

    int getRuleBreakTime() {
        Calendar calendar = Calendar.getInstance();
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE studentID=" + id + " AND year(`date`)=" +
                calendar.get(Calendar.YEAR) + " AND month(`date`)=" + calendar.get(Calendar.MONTH)+" AND absent=1");
        int count=1;
        try {
            while(rs.next()) {
                count++;
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return count;
    }

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

    public ArrayList<dbClass> getClasses() {
        ArrayList<dbClass> ls = new ArrayList<dbClass>();
        ResultSet rs = sqlCommands.sqlQuery("SELECT classid FROM stuClass WHERE studentid=" + this.id);
        try {
            while (rs.next()) {
                ls.add(new dbClass(rs.getString("classname")));
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
