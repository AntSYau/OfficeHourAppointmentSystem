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
                while (appointments.next()) {
                    start = appointments.getInt("timestart");
                    end = appointments.getInt("timeend");
                    ddate = appointments.getDate("date");
                    if (date.equals(ddate) && (timeEnd < start || timeStart > end)) {
                        return 0;
                    }
                }
            } catch (Exception e) {
                return -1;
            }
        }
        return 1;
    }

    int isOccupied(int day, int timeStart, int timeEnd) {
        ResultSet result = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherid=" + id +
                " AND day=" + day);
        try {
            while (result.next()) {
                if (timeStart >= result.getInt("startTime")
                        && timeEnd <= result.getInt("endTime")) {
                    return 0;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    int setRegularOfficeHour(int day, int timeStart, int timeEnd, String address) {
        return 0;
    }
}
