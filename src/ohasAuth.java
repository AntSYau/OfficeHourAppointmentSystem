import java.sql.ResultSet;

class ohasAuth {
    static int signUp(int id, int pos, String name, String password) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE name = \"" + name + "\" or id = " + id);
            if (rs.next()) {
                return 1; // code 1: existing username
            }
            int result = sqlCommands.sqlUpdate("INSERT INTO person(id, pos, name, passwd) VALUES('" + id + "', '"
                    + pos + "', '" + name + "', '" + password + "')");
            if (result != 0) {
                return result;
            }
            if (pos == 1) {
                sqlCommands.sqlUpdate("INSERT INTO teacher(id) VALUES('" + id + "')"); //possible sql error -1
            }
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1; // code -1: sql error
        }
        return 0; // code 0: success
    }

    static dbStudent stuSignIn(int id, String password) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE (pos=2 and id=\""
                    + id + "\" and passwd=\"" + password + "\")");
            if (rs.next()) {
                return new dbStudent(id);
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Your Student ID or password was incorrect. Please check again.");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return null;
    }

    static dbTeacher teaSignIn(int id, String password) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE (pos=1 and id=\""
                    + id + "\" and passwd=\"" + password + "\")");
            if (rs.next()) {
                return new dbTeacher(id);
            }
        } catch (java.lang.NullPointerException e) {
            System.out.println("Your Teacher ID or password was incorrect. Please check again.");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return null;
    }

    static int findIdentity(int id) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT pos FROM person WHERE id="+id);
            if(!rs.next()) return -1;
            return rs.getInt("pos");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
    }
}
