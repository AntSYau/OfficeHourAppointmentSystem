//author: qiu shi

import java.sql.ResultSet;
import java.util.ArrayList;

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
        } catch (java.sql.SQLException e) {
            System.out.println("Your Student ID or password was incorrect. Please check again.");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return new dbStudent(-1);
    }

    static dbTeacher teaSignIn(int id, String password) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE (pos=1 and id=\""
                    + id + "\" and passwd=\"" + password + "\")");
            if (rs.next()) {
                return new dbTeacher(id);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Your uiTeacher ID or password was incorrect. Please check again.");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return new dbTeacher(-1);
    }

    static dbAdmin adminSignIn(int id, String password) {
        try {
            ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE (pos=0 and id=\""
                    + id + "\" and passwd=\"" + password + "\")");
            if (rs.next()) {
                return new dbAdmin(id);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Your ID or password was incorrect. Please check again.");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
        return new dbAdmin(-1);
    }

    static void delete(int id) {
        try{
            sqlCommands.sqlUpdate("DELETE FROM person WHERE id="+id);
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
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

    static ArrayList[] getAllUsers() {
        ArrayList[] result = new ArrayList[3];
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person");
        try{
            while(rs.next()) {
                result[0].add(rs.getInt("id"));
                result[1].add(rs.getString("name"));
                int pos = rs.getInt("pos");
                switch(pos) {
                    case 0:
                        result[2].add("Administrator");
                    case 1:
                        result[2].add("Teacher");
                    case 2:
                        result[2].add("Student");
                }
            }
        } catch ( Exception e) {
            sqlCommands.errorPrint(e);
        }
        return result;
    }

    static String getName(int id) {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + id);
        try {
            rs.next();
            return rs.getString(3);
        } catch (java.lang.NullPointerException e) {
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }

    static void setName(int id, String name) {
        sqlCommands.sqlUpdate("UPDATE `person` SET name='" + name + "' WHERE id=" + id);
    }

    static void setPassword(int id, String password) {
        sqlCommands.sqlUpdate("UPDATE person SET `passwd`='" + password + "' WHERE id=" + id);
    }
}
