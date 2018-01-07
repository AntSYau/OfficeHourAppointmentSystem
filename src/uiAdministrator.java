//author: ren haixiang

import java.util.ArrayList;
import java.util.Scanner;

public class uiAdministrator {
    public static Scanner scanner = new Scanner(System.in);
    private int dayLimit;
    dbAdmin operation;

    public int getDayLimit() {
        return this.dayLimit;
    }

    uiAdministrator(dbAdmin operation) {
        this.operation = operation;
    }

    public static void addAccount() {
        System.out.print("Please enter Username: ");
        String name = scanner.next();
        System.out.print("Please enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Please enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Please enter Identity Code: ");
        int pos = scanner.nextInt();
        System.out.println("Account Information:");
        System.out.println("Username: " + name);
        System.out.println("   ID   : " + id);
        System.out.println("Password: " + password);
        System.out.println("Do you confirm to add this account? 1/Yes 0/No");
        System.out.print("> ");
        int a = scanner.nextInt();
        if (a == 1) {
            ohasAuth.signUp(id, pos, name, password);
            System.out.println("Success.");
        } else {
            System.out.println("Canceled.");
        }
    }
    //*************************����*********************************

    public static void setAccount() {
        System.out.println("Please enter ID:");
        int a = scanner.nextInt();
        ohasAuth.findIdentity(a);
        System.out.println("Account Infomstion");
        System.out.println("Username:" + ohasAuth.getName(a));
        System.out.println("   ID   :" + a);
        System.out.println("What to do next?  :");
        System.out.println("1. set Username");
        System.out.println("2. set Password");
        //System.out.println("3. set ID");
        //System.out.println("4. set Classes");
        //System.out.println("4. set Identity");
        System.out.println("0. exit");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Please enter the new Username");
                String string = scanner.nextLine();
                ohasAuth.setName(a, string);
                break;
            case 2:
                System.out.println("Please enter the new Password");
                String string2 = scanner.nextLine();
                ohasAuth.setPassword(a, string2);
                break;
        }
    }

    public static void AccountMenu() {
        System.out.println("Please choose your operation:");
        System.out.println("1. set Account");
        System.out.println("2. add Account");
        System.out.println("3. delete Account");
        System.out.println("0. exit");
        switch (scanner.nextInt()) {
            case 1:
                setAccount();
                break;
            case 2:
                addAccount();
                break;
            case 3:
                deleteAccount();
                break;
        }
    }

    public void MainMenu() {
        while (true) {
            System.out.println("Hello, " + ohasAuth.getName(operation.getID()) + ". What service would you like to apply?");
            System.out.println("1. Personal Information");
            System.out.println("2. Account Setting");
            System.out.println("3. Set Reservation Day Limit");
            System.out.println("4. Set try chances");
            System.out.println("5. Set rule break ");
            System.out.println("0. Exit");
            System.out.print("> ");
            switch (scanner.nextInt()) {
                case 1:
                    //PersonalInfo();
                    break;
                case 2:
                    //AccountMenu();
                    break;
                case 3:
                    setDayLimit();
                    break;
                case 4:
                    setTryChances();
                    break;
                case 5:
                    setRuleBreakChance();
                    break;
                default:
                    return;
            }
        }

    }

    public void setUsername() {
        System.out.println("Your current Username:" + ohasAuth.getName(operation.getID()));
        System.out.println("Do you want to change it? 1/Yes 0/No");
        int a = scanner.nextInt();
        if (a == 0) {
            System.out.println("Please enter the new account name:");
            String str = scanner.next();
            ohasAuth.setName(operation.getID(), str);
        }
    }

    public static void deleteAccount() {
        System.out.println("All users:\nID\tName\tPosition");
        ArrayList[] res = ohasAuth.getAllUsers();
        for (int i = 0; i < res[0].size(); i++) {
            System.out.println(res[0].get(i) + "\t" + res[1].get(i) + "\t" + res[2].get(i));
        }
        System.out.print("Please enter ID: ");
        int a = scanner.nextInt();
        System.out.println("Do you confirm to delete this Account? 1/Yes 0/No");
        System.out.print("> ");
        if (scanner.nextInt() == 1) {
            ohasAuth.delete(a);
        }
    }

    public void setPassword() {
        System.out.print("Enter your old password: ");
        String password = scanner.next();
        System.out.println("Enter your new password:");
        String str = scanner.next();
        if (ohasAuth.adminSignIn(operation.getID(), password).exist()) {
            ohasAuth.setPassword(operation.getID(), str);
            System.out.println("Success.");
        } else {
            System.out.println("Wrong password. Operation is cancelled.");
        }
    }

    public void PersonalInfo() {
        System.out.println("Your personal account Infomstion");
        System.out.println("Username:" + ohasAuth.getName(operation.getID()));
        System.out.println("ID      :" + operation.getID());
        System.out.println("What to do next? ");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("0. Exit ");
        System.out.println("> ");
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                setUsername();
                break;
            case 2:
                setPassword();
                break;
            default:
                MainMenu();
                break;
        }
    }

    //********************************* account ******************************
    public void setDayLimit() {
        System.out.println("Current day limit: " + ohasRules.getAppTimeRange());
        System.out.println("Do you want to change Day Limit? 1/Yes 0/No");
        System.out.print("> ");
        if (scanner.nextInt() == 1) {
            while (true) {
                System.out.println("Please enter a day limit (3~15):");
                int a = scanner.nextInt();
                if (a <= 15 && a >= 3) {
                    ohasRules.setAppTimeRange(a);
                    System.out.println("Success.");
                    return;
                } else {
                    System.out.println("Invalid integer!");
                }
            }
        }
    }

    //********************************* dayLimit ******************************
    public static void setTryChances() {
        System.out.println("Current day limit: " + ohasRules.getTryChances());
        System.out.println("Do you want to change Try Chances? 1/Yes 0/No");
        System.out.print("> ");
        if (scanner.nextInt() == 1) {
            while (true) {
                System.out.print("Please enter the number of the attempts (3~10): ");
                int a = scanner.nextInt();
                if (a <= 10 && a >= 3) {
                    ohasRules.setTryChances(a);
                    System.out.println("Operation complete.");
                    break;
                } else {
                    System.out.println("Invalid integer!");
                    scanner.nextLine();
                }
            }
        }
    }

    //********************************* TryChances******************************
    public static void setRuleBreakChance() {
        System.out.println("Current rule break chances per month:" + ohasRules.getRuleBreakChance());
        System.out.println("Do you want to change rule break chances?");
        System.out.println("1. Yes");
        System.out.println("0. No");
        if (scanner.nextInt() == 1) {
            while (true) {
                System.out.print("Please enter the number of the chances (0~3): ");
                int a = scanner.nextInt();
                if (a <= 3 && a >= 0) {
                    ohasRules.setRuleBreakChance(a);
                    System.out.println("Operation complete.");
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid integer!");
                    scanner.nextLine();
                }
            }
        }
    }
}
