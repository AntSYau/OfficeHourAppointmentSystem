import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Login {
    public static int EnteredID;
    public static String EnteredPassword;
    public static int[] DataBaseAccount = new int[100];//temp,database模拟
    private static boolean AccountExist;
    private static int tempId;
    private static String tempPassword = "a";
    private static Identity tempIdentity = Identity.STUDENT;
    static Account user1;

    enum Identity {
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

    private static boolean LoginTrue = false;
    private static int tryChances = 5;
    private static int tries = 5;
    public static Scanner sc = new Scanner(System.in);

    public static int getTryChances() {
        return Login.tryChances;
    }

    public static void setTryChances() {
        if (Account.getIdentity() == Identity.ADMINISTRATOR) {//管理员权限******************************************
            System.out.println("Please Enter The Number of Acceptable Attemps:");
            Login.tryChances = sc.nextInt();
        }
    }

    public int getTempId() {
        return Login.tempId;
    }

    public static String getTempPassword() {
        return Login.tempPassword;
    }

    public boolean getLogninTrue() {
        return Login.LoginTrue;
    }

    public boolean getAccountExist() {
        return Login.AccountExist;
    }

    public static void setLoginTrue(boolean a) {
        Login.LoginTrue = a;
    }

    public static void setAccountExist(boolean a) {
        Login.AccountExist = a;
    }

    public static void searchForAccount() {
        for (int i = 0;/*遍历数据库account栏目*/ ; ) {
            if (EnteredID == DataBaseAccount[i]) {
                Login.setAccountExist(true);
                user1 = new Account("get name", tempPassword, EnteredID, tempIdentity);//getName
                tempPassword = Account.getPassword();
                tempIdentity = Account.getIdentity();
                break;
            }
        }
        if (AccountExist != true) {
            System.out.println("***Your Input Account Doesn't Exist. Please Enter a Valid Account.***");
        }
    }

    public static void checkPassword() {
        if (EnteredPassword == tempPassword) {
            Login.setLoginTrue(true);
            System.out.println("Login Succeeded. Welcome," + EnteredID);
            switch (Account.getIdentity()) {
                case STUDENT:
                    student.MainMenu();
                    break;
                case TEACHER:
                    Teacher.MainMenu();
                    break;
                case ADMINISTRATOR:
                    Administrator.MainMenu();
                    break;
            }
        } else {
            System.out.println("Wrong Password. Please Try Again. " + tryChances + " attemps left.");
            tryChances--;
        }
    }

    public static void LoginMain() {
        Login login1 = new Login();
        Login.tries = Login.tryChances;
        System.out.println("Welcome to use SUSTech Office Hour Appointment System. ");
        while (Login.tries > 0) {
            System.out.println("ID: ");
            EnteredID = sc.nextInt();
            System.out.println("Password: ");
            EnteredPassword=sc.next();
            switch(ohasAuth.findIdentity(EnteredID)) {
                case -1:
                    System.out.println("Network error. Please try again.");
                case 1:

            }

        }
        if (Login.tries == 0) {
            System.out.println("Sorry, you are out of attempts. Try 10 minutes later.");//timer  时间设置
            login1.timeControl();
        }
    }

    public void timeControl() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                int time = 600;
                time--;
                if (time < 0) {
                    Login.tries = Login.tryChances;
                }
            }
        }, 0, 1000);
    }

    public Account getUser1() {
        return user1;
    }

    public void setUser1(Account user1) {
        Login.user1 = user1;
    }

    public void setTempPassword() {
        tempPassword = tempPassword;
    }

}
