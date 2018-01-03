//author: zou kehan

import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class uiLogin {
    public static int EnteredID;
    public static String EnteredPassword;
    public static int[] DataBaseAccount = new int[100];//temp,databaseÄ£Äâ
    private static Identity tempIdentity = Identity.STUDENT;

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

    public static Scanner sc = new Scanner(System.in);

    public static void LoginMain() {
        while (true) {
            int c = ohasRules.getTryChances();
            System.out.println("Welcome to use SUSTech Office Hour Appointment System. ");
            Label1:
            for (int attempt = 1; attempt <= c; attempt++) {
                System.out.print("ID: ");
                EnteredID = sc.nextInt();
                System.out.print("Password: ");
                EnteredPassword = sc.next();
                switch (ohasAuth.findIdentity(EnteredID)) {
                    case -1:
                        System.out.println("ID and password mismatch. Chances left: " + (c - attempt));
                        break;
                    case 2:
                        dbStudent db1 = ohasAuth.stuSignIn(EnteredID, EnteredPassword);
                        if (db1.exist()) {
                            new uiStudent(db1).MainMenu();
                            break Label1;
                        }
                        System.out.println("ID and password mismatch. Chances left: " + (c - attempt));
                        break;
                    case 1:
                        dbTeacher db2 = ohasAuth.teaSignIn(EnteredID, EnteredPassword);
                        if (db2.exist()) {
                            new uiTeacher(db2).MainMenu();
                            break Label1;
                        }
                        System.out.println("ID and password mismatch. Chances left: " + (c - attempt));
                        break;
                    case 0:
                        dbAdmin db3 = ohasAuth.adminSignIn(EnteredID, EnteredPassword);
                        if (db3.exist()) {
                            new uiAdministrator(db3).MainMenu();
                            break Label1;
                        }
                        System.out.println("ID and password mismatch. Chances left: " + (c - attempt));
                        break;
                }
                if (attempt == c) {
                    System.out.println("Sorry, you are out of attempts. System will now shut down.");
                }
            }
        }
    }

    //TODO public static void timeControl() {
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                int time = 600;
                time--;
                if (time < 0) {
                    uiLogin.tries = uiLogin.tryChances;
                }
            }
        }, 0, 1000);
    }*/

}
