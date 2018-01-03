import java.sql.ResultSet;

public class ohasRules {
    private int s_RuleBreakChance = 0;
    private int s_MonthlyTime = 0;
    private int s_MonthlyChance = 0;
    private int a_AppTimeRange = 0;

    public static int setRuleBreakChance(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` VALUES `value`=" + rule + " WHERE `Name`='s_RuleBreakChance'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setMonthlyTime(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` VALUES `value`=" + rule + " WHERE `Name`='s_MonthlyTime'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setMonthlyChance(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` VALUES `value`=" + rule + " WHERE `Name`='s_MonthlyChance'");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return -1;
        }
        return 0;
    }

    public static int setAppTimeRange(int rule) {
        try {
            sqlCommands.sqlUpdate("UPDATE `ohasRules` VALUES `value`=" + rule + " WHERE `Name`='a_AppTimeRange'");
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
}
