//author: qiu shi, zou kehan

import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


//

public class dbTeacher {
    private int id;
    private String name;

    dbTeacher(int id) {
        this.id = id;
        name = ohasAuth.getName(id);
    }

    ArrayList[] getAppointments(LocalDate start, LocalDate end) {
        System.out.println("SELECT * FROM appointments WHERE teacherid=" + id +
                " AND date>='" + start.toString() + "' AND date<='" + end.toString() + "'");
        ResultSet appointments = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE teacherid=" + id +
                " AND date>='" + start.toString() + "' AND date<='" + end.toString() + "'");
        ArrayList[] result = new ArrayList[9];
        result[0] = new ArrayList<Integer>();
        result[1] = new ArrayList<Integer>();
        result[2] = new ArrayList<LocalDate>();
        result[3] = new ArrayList<Time>();
        result[4] = new ArrayList<Time>();
        result[5] = new ArrayList<String>();
        result[6] = new ArrayList<Integer>();
        result[7] = new ArrayList<String>();
        result[8] = new ArrayList<Integer>();
        try {
            while (appointments.next()) {
                result[0].add(appointments.getInt("#"));
                result[1].add(appointments.getInt("studentID"));
                result[2].add(appointments.getDate("date").toLocalDate());
                result[3].add(appointments.getTime("timeStart").toLocalTime());
                result[4].add(appointments.getTime("timeEnd").toLocalTime());
                result[5].add(appointments.getString("address"));
                result[6].add(appointments.getInt("absent"));
                result[7].add(appointments.getString("className"));
                result[8].add(appointments.getInt("teacherID"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return result;
    }

    int getPeriod() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT timeperiod FROM teacher WHERE id=" + id);
        try {
            rs.next();
            return rs.getInt("timeperiod");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
    }

    void delAppointment(int id) {
        try {
            sqlCommands.sqlUpdate("DELETE FROM appointments WHERE `#`=" + id);
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }

    int isOccupied(LocalDate date, LocalTime timeStart, LocalTime timeEnd) {
        LocalTime start, end;
        LocalDate ddate;
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (isOccupied(dayOfWeek, timeStart, timeEnd) == 0) {
            try {
                ResultSet appointments = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE date='" + date.toString() + "' AND teacherID=" + id);
                if (!appointments.next()) {
                    return 0;
                }
                appointments.beforeFirst();
                while (appointments.next()) {
                    start = appointments.getTime("timeStart").toLocalTime();
                    end = appointments.getTime("timeEnd").toLocalTime();
                    ddate = appointments.getDate("date").toLocalDate();
                    if (date.equals(ddate) && (((timeEnd.isAfter(start)) &&
                            (timeEnd.isBefore(end) || timeEnd.equals(end))) ||
                            ((timeStart.isAfter(start) || timeStart.equals(start)) &&
                                    (timeStart.isBefore(end))) ||
                            (timeStart.isBefore(start) && timeEnd.isAfter(end)))) {
                        return 1;
                    }
                }
            } catch (Exception e) {
                sqlCommands.errorPrint(e);
                return -1;
            }
        }
        return 0;
    }

    int isOccupied(int day, LocalTime timeStart, LocalTime timeEnd) {
        ResultSet result = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherid=" + id +
                " AND day=" + day);
        try {
            if (!result.next()) {
                return 0;
            }
            LocalTime start = result.getTime("timeStart").toLocalTime();
            LocalTime end = result.getTime("timeEnd").toLocalTime();
            while (result.next()) {
                if (((timeStart.isBefore(end) || timeStart.equals(end)) &&
                        (timeStart.isAfter(start) || timeStart.equals(start))) ||
                        ((timeEnd.isAfter(start) || timeEnd.equals(start)) &&
                                (timeEnd.isBefore(end) || timeEnd.equals(end)))) {
                    return 1;
                }
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    int setRegularOfficeHour(int day, LocalTime timeStart, LocalTime timeEnd, String address) {  // Date; 800, 1200
        if (isOccupied(day, timeStart, timeEnd) == 0) {
            return sqlCommands.sqlUpdate("INSERT INTO officehour(teacherid,day,timeStart,timeEnd,address) VALUES (" +
                    id + "," + day + "," + timeStart + "," + timeEnd + ",\"" + address + "\")");
        }
        return 1;
    }

    ArrayList[] getRegularOfficeHour() {
        ArrayList[] result = new ArrayList[3];
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherid=" + id +
                " ORDER BY `day` ASC, `timeStart` ASC");
        result[0] = new ArrayList<LocalTime>();
        result[1] = new ArrayList<LocalTime>();
        result[2] = new ArrayList<String>();
        try {
            while (rs.next()) {
                result[0].add(rs.getTime("timeStart").toLocalTime());
                result[1].add(rs.getTime("timeEnd").toLocalTime());
                result[2].add(rs.getString("address"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return result;
    }


    ArrayList[] getAvailableOfficeHour(LocalDate date) {
        int tperiod = getPeriod();
        int dayOfWeek = date.getDayOfWeek().getValue();
        ResultSet office = sqlCommands.sqlQuery("SELECT * FROM officehour WHERE teacherID=" + id + " and day=" +
                dayOfWeek + " ORDER BY `officehour`.`timeStart` ASC");
        /*ResultSet app = sqlCommands.sqlQuery("SELECT * FROM appointments WHERE teacherID=" + id +
                " AND date='" + date.toString() + "'");*/
        ArrayList[] period = new ArrayList[2];
        period[0] = new ArrayList<LocalTime>();
        period[1] = new ArrayList<LocalTime>();
        try {
            while (office.next()) {
                LocalTime timeStart = office.getTime("timeStart").toLocalTime();
                LocalTime timeEnd = office.getTime("timeEnd").toLocalTime();
                LocalTime running = timeStart;
                String address = office.getString("address");
                while (running.isBefore(timeEnd)) {
                    java.util.Date dstart, dend;
                    dstart = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date.toString() +
                            " " + running.getHour() + ":" + running.getMinute());
                    LocalTime lt = running.plusMinutes(getPeriod());
                    dend = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date.toString() +
                            " " + lt.getHour() + ":" + lt.getMinute());
                    period[0].add(new java.util.Date[]{dstart, dend});
                    period[1].add(address); //ArrayList[1] 储存当前答疑时间的地址！
                    running = running.plusMinutes(tperiod);
                    System.out.print(".");
                }
                java.util.Date[] fin = (java.util.Date[]) period[0].get(period[0].size() - 1);
                fin[1] = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date.toString() +
                        " " + timeEnd.getHour() + ":" + timeEnd.getMinute());
                period[0].set(period[0].size() - 1, fin);
            }
        } catch (
                Exception e)

        {
            sqlCommands.errorPrint(e);
        }
        return period;
    }

    int delRegularOfficeHour(int day, int timeStart) {
        return sqlCommands.sqlUpdate("DELETE FROM officehour WHERE (day=" + day + " and timeStart=" + timeStart + ")");
    }

    public int getID() {
        return id;
    }

    boolean exist() {
        return !(name.equals("error"));
    }

    int addClass(String name) {
        ResultSet query = sqlCommands.sqlQuery("SELECT name FROM sumClass WHERE name='" + name + "'");
        try {
            if (query.next()) {
                return 1;
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return sqlCommands.sqlUpdate("INSERT INTO sumClass(teacherid,name) VALUES (" + this.id + ",'" + name + "')");
    }

    int setWorkDayEnd(LocalDate date) {
        try {
            sqlCommands.sqlUpdate("UPDATE teacher SET `workdayend`='" + date.toString() + "' WHERE id=" + id);
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    int setTimeInterval(int timeperiod) {
        try {
            sqlCommands.sqlUpdate("UPDATE teacher SET `timeperiod`='" + timeperiod + "' WHERE id=" + id);
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    ArrayList<String> getClasses() {
        ArrayList result = new ArrayList<Integer>();
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM sumClass WHERE teacherid=" + id + " ORDER BY `#` ASC");
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return result;
    }
}
