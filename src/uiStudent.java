//author: zou kehan

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class uiStudent {

    dbStudent operation;

    uiStudent(dbStudent operation) {
        this.operation = operation;
    }

    public Scanner scanner = new Scanner(System.in);

    public void setUsername() {
        System.out.println("Your current name:" + operation.getName());
        System.out.println("Do you want to change it? 1/Yes 0/No");
        int a = scanner.nextInt();
        if (a == 1) {
            System.out.println("Please enter your new account name:");
            String str = scanner.next();
            operation.setName(str);
            System.out.println("Success.");
        }
    }

    public void setPassword() {
        System.out.print("Enter your old password: ");
        String password = scanner.next();
        System.out.println("Enter your new password:");
        String str = scanner.next();
        if (ohasAuth.stuSignIn(operation.getID(), password).exist()) {
            operation.setPassword(str);
            System.out.println("Success.");
        } else {
            System.out.println("Wrong password. Operation is cancelled.");
        }

    }

    public void PersonalInfo() {
        System.out.println("Your personal account Infomstion");
        System.out.println("Username:" + operation.getName());
        System.out.println("ID      :" + operation.getID());
        System.out.println("What to do next? ");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("0. Exit ");
        System.out.print("> ");
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                setUsername();
                break;
            case 2:
                setPassword();
                break;
            default:
                return;
        }
    }
    //*******************������Ϣ********************************************* 

    public int CheckReservtion() {
        System.out.println("What would you like to do? ");
        System.out.println("1. Add Reservation");
        System.out.println("2. Cancel Reseravtion");
        System.out.print("0. Exit\n> ");
        int a = scanner.nextInt();

        switch (a) {
            case 1:
                Reserve();
                break;
            case 2:
                CancelReservation();
                break;
            default:
                return 0;
        }

        return 0;
    }


    public int Reserve() {
        if (operation.getReservationChance() >= ohasRules.getMonthlyChance()) {
            System.out.println("Sorry, you are out of reservation chances this month!");
            scanner.nextLine();
            return 0;
        }
        if (operation.getRuleBreakTime() >= ohasRules.getRuleBreakChance()) {
            System.out.println("Sorry, you have break the rules too much that you are forbidden to reserve now!");
            scanner.nextLine();
            return 0;
        }
        if (operation.getReservationTime() >= ohasRules.getMonthlyTime()) {
            System.out.println("Sorry, you are out of reservation time!");
            scanner.nextLine();
            return 0;
        }

        System.out.println("Please choose a Class you attended:     ");
        System.out.println("(Class# : select class. \"0\": cancel.)");
        ArrayList<dbClass> classlist = operation.getClasses();
        for (int i = 1; i <= classlist.size(); i++) {
            System.out.println(i + ". " + classlist.get(i - 1).getName());
        }
        System.out.print("> ");
        int a = scanner.nextInt();
        dbClass chosenClass;
        if (a == 0) {
            return 0;
        } else {
            chosenClass = classlist.get(a - 1);
        }
        System.out.println("Please choose a Teacher:     ");
        System.out.print("(Teacher# : select teacher. \"0\": cancel.)\n");
        ArrayList<Integer> TeacherList = chosenClass.getTeachers();
        for (int i = 1; i <= TeacherList.size(); i++) {
            System.out.println(i + ". " + TeacherList.get(i - 1));
        }
        System.out.print("> ");
        int b = scanner.nextInt();
        dbTeacher chosenTeacher;
        if (b == 0) {
            return 0;
        } else {
            chosenTeacher = new dbTeacher(TeacherList.get(b - 1));
        }
        ArrayList[] AvailableTime = new ArrayList[2];
        AvailableTime[0] = new ArrayList<LocalTime>();
        AvailableTime[1] = new ArrayList<LocalTime>();
        System.out.print("Querying available office hour. Please wait");
        for (LocalDate running = LocalDate.now(); running.isBefore(LocalDate.now().plusDays(ohasRules.getAppTimeRange() + 1)); running = running.plusDays(1)) {
            ArrayList[] test1 = chosenTeacher.getAvailableOfficeHour(running);
            AvailableTime[0].addAll(test1[0]);
            AvailableTime[1].addAll(test1[1]);
        }
        System.out.println();
        for (int i = 1; i <= AvailableTime[0].size(); i++) {
            System.out.println(i + ". " + ((java.util.Date[]) AvailableTime[0].get(i - 1))[0].toString() + " - " +
                    ((java.util.Date[]) AvailableTime[0].get(i - 1))[1].toString());
        }
        int c = 1;
        while (c == 1) {
            System.out.println("Please choose a date and time");
            System.out.print("> ");
            int ii = scanner.nextInt();
            System.out.println("Confirm the new Reservation? Yes/1  No/0");
            System.out.print("> ");
            if (scanner.nextInt() == 1) {
                c = chosenClass.setReservation(operation.getID(), chosenTeacher.getID(),
                        ((java.util.Date[]) AvailableTime[0].get(ii - 1))[0],
                        ((java.util.Date[]) AvailableTime[0].get(ii - 1))[1],
                        (String) AvailableTime[1].get(ii - 1));
                if (c == 1) {
                    System.out.println("Time occupied.");
                }
            } else {
                return 0;
            }
        }
        System.out.println("Success.\n");
        return 0;
    }

    public int CancelReservation() {
        System.out.print("Please wait");
        ArrayList[] list = operation.appQuery();
        System.out.println("\n#\tStudent\tDate\tStart\tEnd\tTeacher");
        for (int i = 1; i <= list[1].size(); i++) {
            System.out.println(i + ". " + list[1].get(i - 1));
        }
        while (true) {
            System.out.println("Please enter the number of the Reservation you want to cancel: (enter 0 to cancel operation)");
            System.out.print("> ");
            int a = scanner.nextInt();
            if (a > 0 && a <= list[0].size()) {
                dbStudent.delAppointment((int) list[0].get(a - 1));
                System.out.println("Success.");
                return 0;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void MainMenu() {
        int key = 0;
        while (key == 0) {
            System.out.println("Hello, " + operation.getName() + ". What service would you like to apply?");
            System.out.println("1. Personal Information");
            System.out.println("2. Reservation");
            System.out.println("0. Exit");
            System.out.print("> ");
            int a = scanner.nextInt();
            if (a == 1) {
                PersonalInfo();
            } else if (a == 2) {
                CheckReservtion();
            } else {
                key = 1;
            }
        }
    }
}