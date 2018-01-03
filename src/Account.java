import java.sql.ResultSet;
import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private int id;
    private ArrayList<Classes> classes;
    private uiLogin.Identity identity;
    private Account user = new Account();
    private static int Rulebreak;
    private static int monthlyTime;
    private static int monthlychance;

    public static void breakRule() {
        Rulebreak += 1;
    }

    public static void setRuleBreakChance(int a) {
        Account.Rulebreak = a;
    }

    public boolean ifOutOfMChance() {
        return monthlychance >= uiStudent.getMonthChance();
    }

    public boolean ifOutOfRBChance() {
        return Rulebreak >= uiStudent.getRBC();
    }

    public boolean ifOutOfTTime() {
        return monthlyTime >= uiStudent.getTotalTime();
    }

    private enum Identity {
        ADMINISTRATOR(0), TEACHER(1), STUDENT(2);
        private int type;

        Identity(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public static Identity getIdentity(int type) {
            for (Identity identity : Identity.values()) {
                if (identity.getType() == type) {
                    return identity;
                }
            }
            return null;
        }
    }

    public Account() {
        String username;
        String password;
        int id = uiLogin.EnteredID;
        ArrayList<Classes> Classes;
        Identity identity;
    }

    public Account(String username, String password, int id, uiLogin.Identity tempIdentity) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.classes = classes;//获取班级
        this.identity = tempIdentity;
    }

    public void setAccount() {
        user.username = uiLogin.user1.getUsername();
        user.password = uiLogin.user1.getPassword();
        user.id = uiLogin.EnteredID;
        user.classes = uiLogin.user1.getClasses();
        user.identity = getIdentity();
    }

    //**********************************************************
    public String getPassword() {
        ResultSet rs = sqlCommands.sqlQuery("SELECT * FROM person WHERE id=" + user.id);
        try {
            rs.next();
            return rs.getString("password");
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
            return null;
        }
    }

    public ArrayList<Classes> getClasses() {
        return new dbStudent(this.id).getClasses();
    }

    public String getUsername() { // TODO 通用用户账户的数据库连接！！
        //数据库获取账号
        return new dbStudent(this.id).getName();
    }

    public static uiLogin.Identity getIdentity() {
        //数据库获取账户类型
        return uiLogin.Identity.STUDENT;
    }

    public int getId() {
        return this.id;
    }


    public void setPassword(String str) {
        this.password = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setClasses(ArrayList<Classes> a) {
        this.classes = a;
    }

    public void setId(int str) {
        this.id = str;
    }

    public void setIdentity(uiLogin.Identity a) {
        this.identity = a;
    }
}
