import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class dbTeacher {
    private int id;
    private String name;
    private int upvote, downvote;
    private ResultSet officehour;
    private ResultSet appointments;

    dbTeacher(int id) {
        this.id = id;
        name = getName();
    }

    void getOfficeHour() {
        officehour = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherid=" + id);
    }

    void getAppointments() {
        appointments = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE teacherid=" + id);
    }

    int isOccupied(Date date, int timeStart, int timeEnd) {
        int start, end;
        Date ddate;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (isOccupied(dayOfWeek, timeStart, timeEnd) == 0) {
            try {
                appointments.beforeFirst();
                if (!appointments.next()) {
                    return 0;
                }
                appointments.beforeFirst();
                while (appointments.next()) {
                    start = appointments.getInt("timeStart");
                    end = appointments.getInt("timeEnd");
                    ddate = appointments.getDate("date");
                    if (date.equals(ddate) && ((timeEnd >= start && timeEnd <= end) || (timeStart >= start &&
                            timeStart <= end))) {
                        return 0;
                    }
                }
            } catch (Exception e) {
                sqlCommands.errorPrint(e);
                return -1;
            }
        }
        return 1;
    }

    int isOccupied(int day, int timeStart, int timeEnd) {
        ResultSet result = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherid=" + id +
                " AND day=" + day);
        try {
            if (!result.next()) {
                return 0;
            }
            result.beforeFirst();
            while (result.next()) {
                if ((timeStart <= result.getInt("timeEnd") && timeStart >= result.getInt("timeStart"))
                        || (timeEnd >= result.getInt("timeStart") && timeEnd <= result.getInt("timeEnd"))) {
                    return 1;
                }
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    int setRegularOfficeHour(int day, int timeStart, int timeEnd, String address) {
        if (isOccupied(day, timeStart, timeEnd) == 0) {
            return sqlCommands.sqlUpdate("INSERT INTO officehour(teacherid,day,timeStart,timeEnd,address) VALUES (" +
                    id + "," + day + "," + timeStart + "," + timeEnd + ",\"" + address + "\")");
        }
        return 1;
    }

    int delRegularOfficeHour(int day, int timeStart) {
        return sqlCommands.sqlUpdate("DELETE FROM officehour WHERE (day=" + day + " and timeStart=" + timeStart + ")");
    }

    String getName() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + id);
        try {
            rs.next();
            return rs.getString(3);
        } catch (java.lang.NullPointerException e) {
            System.out.println("We cannot identify this teacher by Teacher ID.");
            return "error";
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return "error";
        }
    }

    boolean exist() {
        return !(name.equals("error"));
    }
}
