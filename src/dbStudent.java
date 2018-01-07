//author: qiu shi, zou kehan

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class dbStudent {
    private int id = 0;
    private String name = "null";

    dbStudent(int id) {
        this.id = id;
        name = ohasAuth.getName(id);
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
                calendar.get(Calendar.YEAR) + " AND month(`date`)=" + calendar.get(Calendar.MONTH) + " AND absent=1");
        int count = 1;
        try {
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return count;
    }

    public ArrayList[] appQuery() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE studentID=" + id +
                " ORDER BY `appointments`.`date` ASC, `appointments`.`timeStart` ASC");
        ArrayList[] query = new ArrayList[2];
        query[0] = new ArrayList<Integer>();
        query[1] = new ArrayList<String>();
        try {
            while (rs.next()) {
                query[0].add(rs.getInt("#"));
                query[1].add(rs.getDate("date") + "\t" + rs.getTime("timeStart").toString() + "\t" +
                        rs.getTime("timeEnd").toString() + "\t" +
                        (ohasAuth.getName(rs.getInt("teacherID"))));
                System.out.print(".");
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
        ResultSet rs = sqlCommands.sqlQuery("SELECT name FROM stuClass WHERE studentid=" + this.id);
        try {
            while (rs.next()) {
                ls.add(new dbClass(rs.getString("name")));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return ls;
    }

    public int getID() {
        return id;
    }

    public static void delAppointment(int id) {
        sqlCommands.sqlUpdate("DELETE FROM appointments WHERE `#`=" + id);
    }

    boolean exist() {
        return !(name.equals("error"));
    }

    void setPassword(String password) {
        sqlCommands.sqlUpdate("UPDATE person SET `passwd`='" + password + "' WHERE id=" + this.id);
    }
}
