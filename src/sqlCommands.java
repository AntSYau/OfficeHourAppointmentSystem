//author: qiu shi

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

class sqlCommands {

    private static String url = "jdbc:mysql://202.5.19.198:3306/ohas";
    private static String user = "ohas";
    private static String password = "NaBr0365ohas";

    static int sqlUpdate(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    static ResultSet sqlQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static void errorPrint(Exception e) {
        System.out.println("OHAS is having a bad time... Send the following message to your administrator:\n\t" +
                e.toString());
    }

    static void errorPrint(Exception e, int code) {
        System.out.println("OHAS is having a bad time... Send the following message to your administrator:\n\tError " +
                code + ": " + e.toString());
    }
}
