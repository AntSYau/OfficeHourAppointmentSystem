//author: qiu shi

import java.sql.ResultSet;

public class ohasRules {
    public static int setRuleBreakChance(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` SET `value`=" + rule + " WHERE `Name`='s_RuleBreakChance'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setMonthlyTime(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` SET `value`=" + rule + " WHERE `Name`='s_MonthlyTime'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setMonthlyChance(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` SET `value`=" + rule + " WHERE `Name`='s_MonthlyChance'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setAppTimeRange(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE  `ohasRules` SET `value`=" + rule + " WHERE `Name`='a_AppTimeRange'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int getRuleBreakChance() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM `ohasRules` WHERE `Name`='s_RuleBreakChance'");
        return getBody(rs);
    }

    public static int getMonthlyTime() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM `ohasRules` WHERE `Name`='s_MonthlyTime'");
        return getBody(rs);
    }

    public static int getMonthlyChance() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM `ohasRules` WHERE `Name`='s_MonthlyChance'");
        return getBody(rs);
    }

    public static int getAppTimeRange() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM `ohasRules` WHERE `Name`='a_AppTimeRange'");
        return getBody(rs);
    }

    public static int getBody(ResultSet rs) {
        try {
            rs.next();
            return rs.getInt("Value");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
    }

    public static int setTryChances(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` SET `value`=" + rule + " WHERE `Name`='a_TryChances'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int getTryChances() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM `ohasRules` WHERE `Name`='a_TryChances'");
        return getBody(rs);
    }
}
