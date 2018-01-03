//author: qiu shi, zou kehan

import java.sql.ResultSet;

public class dbAdmin {
    private int id;
    private String name;

    dbAdmin(int id) {
        this.id = id;
        name=getName();
    }

    String getName() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + id);
        try {
            rs.next();
            return rs.getString("name");
        } catch (java.lang.NullPointerException e) {
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }

    boolean exist() {
        return !(name.equals("error"));
    }
}
