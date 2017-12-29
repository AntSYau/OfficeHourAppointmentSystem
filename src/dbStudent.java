import java.sql.ResultSet;

public class dbStudent {
    private int id = 0;
    private String name = "null";

    dbStudent(int id) {
        this.id = id;
        name=getName();
    }

    String getName() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + id);
        try {
            rs.next();
            return rs.getString(3);
        } catch (java.lang.NullPointerException e) {
            System.out.println("We cannot identify this student by dbStudent ID.");
            return "error";
        } catch (java.sql.SQLException e) {
            System.out.println("Server failed while identifying this student. Please try again later.");
            return "error";
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return "error";
        }
    }

    public String toString() {
        return name+" ("+id+")";
    }

    boolean exist() {
        return !(name.equals("error"));
    }
}