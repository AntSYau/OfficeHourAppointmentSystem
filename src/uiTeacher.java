//author: ren haixiang

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class uiTeacher {
    public static Scanner scanner = new Scanner(System.in);
    private dbTeacher operation;

    uiTeacher(dbTeacher operation) {
        this.operation = operation;
    }

    public int MainMenu() {
        int key = 0;
        while (key == 0) {
            System.out.println("Hello, " + ohasAuth.getName(operation.getID()) + ". What service would you like to apply?");
            System.out.println("1. Personal Information");
            System.out.println("2. Reservation");
            System.out.println("3. Set Reservation Rules");
            System.out.println("4. Set Classes and Rules");
            System.out.println("0. Exit");
            System.out.print("> ");
            int a = scanner.nextInt();
            if (a == 1) {
                PersonalInfo();
            } else if (a == 2) {
                CheckReservtion();
            } else if (a == 3) {
                setReservationRules();
            } else if (a == 4) {
                setTeaching();
            } else {
                key = 1;
            }
        }
        return 0;
    }

    public int setReservationRules() {
        while (true) {
            System.out.println("What would you like to set?");
            System.out.println("1. set office hour");
            System.out.println("2. set Work days");
            System.out.println("3. set reservation time Interval");
            System.out.println("0. exit");
            System.out.print("> ");
            switch (scanner.nextInt()) {
                case 1:
                    //setOfficeHour();
                    break;
                case 2:
                    setWorkDays();
                    break;
                case 3:
                    setReserInTime();
                    break;
                default:
                    return 0;
            }
        }
    }

    public int setWorkDays() {
        System.out.print("Please input your work day's end in the form of YYYY-MM-DD: ");
        String input = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, formatter);
        operation.setWorkDayEnd(date);
        System.out.println("Successful.");
        scanner.nextLine();
        setReservationRules();
        return 0;
    }

    public int setReserInTime() {
        System.out.print("Please enter the new time interval: ");
        int input = scanner.nextInt();
        operation.setTimeInterval(input);
        System.out.println("Time interval is set to " + input + " minutes.");
        return 0;
    }
    //*************************����*********************************

    public void setUsername() {
        System.out.println("Your current name:" + ohasAuth.getName(operation.getID()));
        System.out.println("Do you want to change it? 1/Yes 0/No");
        System.out.print("> ");
        int a = scanner.nextInt();
        if (a == 1) {
            System.out.print("Please enter your new account name: ");
            String str = uiLogin.sc.nextLine();
            ohasAuth.setName(operation.getID(), str);
        }
    }

    public void setPassword() {
        System.out.print("Enter your old password: ");
        String password = scanner.next();
        System.out.print("Enter your new password: ");
        String str = scanner.next();
        if (ohasAuth.teaSignIn(operation.getID(), password).exist()) {
            ohasAuth.setPassword(operation.getID(), str);
        } else {
            System.out.println("Wrong password. Operation is cancelled.");
        }

    }

    public void PersonalInfo() {
        System.out.println("Your personal account info:");
        System.out.println("Username: " + ohasAuth.getName(operation.getID()));
        System.out.println("   ID   : " + operation.getID());
        while (true) {
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
    }

    //*******************������Ϣ*********************************************

    public void CheckReservtion() {
        int key = 0;
        while (key == 0) {
            System.out.println("What would you like to do? ");
            System.out.println("1. Cancel Reservation");
            System.out.println("2. Set Reservation");
            System.out.println("0. Exit");
            System.out.print("> ");
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    cancelReservation();
                    break;
                case 2:
                    setReservation();
                    break;
                default:
                    return;
            }
        }
    }

    public void cancelReservation() {
        ArrayList[] list = operation.getAppointments(LocalDate.now(), LocalDate.now().plusDays(ohasRules.getAppTimeRange()));
        System.out.println("Please enter the number of the Reservation you want to cancel: (enter 0 to cancel operation)\n" +
                "#\tStudent\tDate\tStart\tEnd");
        for (int i = 1; i <= list[0].size(); i++) {
            System.out.println(i + "\t" + list[1].get(i - 1) + "\t" + list[2].get(i - 1).toString() +
                    list[3].get(i - 1).toString() + "\t" + list[4].get(i - 1).toString());
        }
        System.out.print("> ");
        int a = scanner.nextInt();
        if (a > 0) {
            operation.delAppointment((int) list[0].get(a - 1));
            System.out.println("Removed successfully.");
        }
    }

    public int setReservation() {
        int key = 0;
        while (key == 0) {
            ArrayList[] list = operation.getAppointments(LocalDate.now().minusDays(2), LocalDate.now().plusDays(ohasRules.getAppTimeRange()));
            System.out.println("Please enter the number of the Reservation you want to modify: (enter 0 to cancel operation)\n" +
                    "#\tStudent\tDate\tStart\tEnd");
            for (int i = 1; i <= list[0].size(); i++) {
                System.out.println(i + "\t" + list[1].get(i - 1) + "\t" + list[2].get(i - 1).toString() +
                        list[3].get(i - 1).toString() + "\t" + list[4].get(i - 1).toString());
            }
            System.out.print("> ");
            int a = scanner.nextInt();
            if (a <= 0 || a > list[0].size()) {
                return 0;
            }
            int resid = (int) list[0].get(a - 1);
            int studentid = (int) list[1].get(a - 1);
            LocalDate date = (LocalDate) list[2].get(a - 1);
            LocalTime start = (LocalTime) list[3].get(a - 1);
            LocalTime end = (LocalTime) list[4].get(a - 1);
            String address = (String) list[5].get(a - 1);
            int absence = (int) list[6].get(a - 1);
            String classname = (String) list[7].get(a - 1);
            int teacherid = (int) list[8].get(a - 1);
            while (true) {
                System.out.println("What would you like to change?");
                System.out.println("1. Date: " + date.toString());
                System.out.println("2. Time: " + start.toString() + " - " + end.toString());
                System.out.println("3. Address: " + address);
                System.out.println("4. Teacher: " + ohasAuth.getName(teacherid));
                System.out.println("5. Absence: " + (absence == 1));
                System.out.println("6. Save and exit");
                System.out.println("0. Discard and exit");
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
                System.out.print("> ");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Please input the date you want to apply in the format of YYYY-MM-DD");
                        String s1 = scanner.next();
                        date = LocalDate.parse(s1, formatter1);
                        break;
                    case 2:
                        System.out.println("Please input the new start time in the format of HH:MM");
                        String s2 = scanner.next();
                        start = LocalTime.parse(s2, formatter2);
                        System.out.println("Please input the new end time in the format of HH:MM");
                        s2 = scanner.next();
                        end = LocalTime.parse(s2, formatter2);
                        break;
                    case 3:
                        System.out.println("Please input the new address");
                        address = scanner.next();
                        break;
                    case 4:
                        System.out.println("Please input the id of the teacher you want to switch to");
                        ArrayList<Integer> teachers = new dbClass(classname).getTeachers();
                        for (int i = 1; i <= teachers.size(); i++) {
                            System.out.println(i + ". " + (ohasAuth.getName(teachers.get(i - 1))));
                        }
                        a = scanner.nextInt();
                        teacherid = teachers.get(a - 1);
                        break;
                    case 5:
                        System.out.println(ohasAuth.getName(studentid) + " is 0. presence 1. absence");
                        a = scanner.nextInt();
                        if (a == 0) {
                            absence = 0;
                        } else {
                            absence = 1;
                        }
                        break;
                    case 6:
                        operation.delAppointment(resid);
                        System.out.println(new dbClass(classname).setReservation(studentid, teacherid, date, start, end, address));
                    default:
                        return 0;
                }
            }
        }
        return 0;
    }

    //*************************ԤԼ����**************************
    public void setTeaching() {
        while (true) {
            System.out.println("What would you like to do? ");
            System.out.println("1. Set Class");
            System.out.println("2. Set Reservation Rules");
            System.out.println("0. Exit ");
            System.out.print("> ");
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    ClassMenu();
                    break;
                case 2:
                    //setRule();
                    break;
                default:
                    return;
            }
        }
    }

    //**********************�༶*****************************
    public void ClassMenu() {
        while (true) {
            System.out.println("What would you like to do? ");
            System.out.println("1. Add New Class");
            System.out.println("2. Edit Previous Class");
            System.out.println("0. Exit ");
            System.out.print("> ");
            int a = scanner.nextInt();
            switch (a) {
                case 1:
                    addNewClass();
                    break;
                case 2:
                    checkClass();
                    break;
                default:
                    return;
            }
        }
    }

    public void checkClass() {
        while (true) {
            System.out.println("Please choose a class:");
            ArrayList<String> classes = operation.getClasses();
            for (int i = 1; i <= classes.size(); i++) {
                System.out.println(i + ". " + new dbClass(classes.get(i - 1)).getName());
            }
            dbClass cls = new dbClass(classes.get(scanner.nextInt() - 1));
            System.out.println("What would you like to do? ");
            System.out.println("1. Edit class");
            System.out.println("2. Delete class");
            System.out.println("0. exit");
            System.out.print("> ");
            switch (scanner.nextInt()) {
                case 1:
                    setClasses(cls);
                    break;
                case 2:
                    deleteClass(cls);
                    break;
                default:
                    return;
            }
        }
    }

    public void setClasses(dbClass cls) {
        System.out.println("Please choose your operation");
        System.out.println("1. set Class Name");
        System.out.println("2. set Class Teacher");
        System.out.println("3. set Class Students");
        System.out.println("0. exit");
        System.out.print("> ");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("Please enter a new name for this class: ");
                cls.setName(scanner.next());
                break;
            case 2:
                System.out.println("Please choose your operation:");
                System.out.println("1. Remove");
                System.out.println("2. Add");
                System.out.println("0. Exit");
                System.out.print("> ");
                switch (scanner.nextInt()) {
                    case 1:
                        ArrayList<Integer> teachers = cls.getTeachers();
                        for (int i = 1; i <= teachers.size(); i++) {
                            System.out.println(i + ". " + (ohasAuth.getName(teachers.get(i - 1))));
                        }
                        System.out.println("Please choose the teacher you want to Remove:");
                        int a = scanner.nextInt();
                        cls.delTeacher(teachers.get(a - 1));
                        System.out.println("Success.");
                        return;
                    case 2:
                        System.out.print("PTeacher's ID: ");
                        a = scanner.nextInt();
                        if (!new dbTeacher(a).exist()) {
                            System.out.println("ID is incorrect.");
                        }
                        System.out.println("Name: " + ohasAuth.getName(a));
                        System.out.println("ID: " + new dbTeacher(a).getID());
                        System.out.println("Confirm to add?  1.Yes  2.No");
                        System.out.print("> ");
                        a = scanner.nextInt();
                        if (a == 1) {
                            cls.addTeacher(a);
                            //database ��class ��Ӹ���ʦ
                            //database �����ʦ��Ӹ�classes
                            System.out.println("Success.");
                        } else {
                            System.out.println("Canceled.");
                        }
                        return;
                    default:
                        return;
                }

            case 3:
                System.out.println("Please choose your operation:");
                System.out.println("1. Remove");
                System.out.println("2. Add");
                System.out.println("0. Exit");
                System.out.print("> ");
                switch (scanner.nextInt()) {
                    case 1:
                        ArrayList<Integer> students = cls.getStudents();
                        for (int i = 1; i <= students.size(); i++) {
                            System.out.println(i + ". " + (ohasAuth.getName(students.get(i - 1))));
                        }
                        System.out.println("Please choose the Student you want to Remove:");
                        System.out.print("> ");
                        int a = scanner.nextInt();
                        if (a > 0 && a <= students.size()) {
                            cls.delStudent(students.get(a - 1));
                            System.out.println(ohasAuth.getName(students.get(a - 1)) + " is kicked out from your class.");
                        }
                        return;
                    case 2:
                        System.out.println("Student's ID: ");
                        a = scanner.nextInt();
                        if (!new dbStudent(a).exist()) {
                            System.out.println("ID is incorrect.");
                        }
                        System.out.println("Name: " + ohasAuth.getName(a));
                        System.out.println("ID: " + new dbStudent(a).getID());
                        System.out.println("Confirm to add?  1.Yes  2.No");
                        System.out.print("> ");
                        a = scanner.nextInt();
                        if (a == 1) {
                            cls.addStudent(a);
                            System.out.println("Success.");
                        } else {
                            System.out.println("Canceled.");
                        }
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void deleteClass(dbClass cls) {
        cls.delete();
    }

    public void setOfficeHour() {
        System.out.print("Day of week: ");
        int dow = scanner.nextInt();
        System.out.print("Start time in format HH:MM: ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(scanner.next());
        System.out.print("End time in format HH:MM: ");
        LocalTime stop = LocalTime.parse(scanner.next());
        System.out.print("Your office address:\n> ");
        String str = scanner.next();
        operation.setRegularOfficeHour(dow,start,stop,str);
        System.out.println("Success.");
    }


    public void addNewClass() {
        System.out.println("Please enter Class name: ");
        System.out.print("> ");
        String name = scanner.next();
        operation.addClass(name);
        System.out.println("Please add teachers to this Class:");
        int a;
        do {
            System.out.print("Teacher's ID: ");
            a = scanner.nextInt();
            if (!new dbTeacher(a).exist()) {
                System.out.println("ID is incorrect.");
                continue;
            }
            System.out.println("Name: " + ohasAuth.getName(a));
            System.out.println(" ID : " + a);
            new dbClass(name).addTeacher(a);
            System.out.println("Continue to add teachers? 1/Yes 0/No");
            System.out.print("> ");
            a = scanner.nextInt();
        } while (a == 1);
        System.out.println("Please add students to the Class:");
        do {
            System.out.print("Student ID: ");
            a = scanner.nextInt();
            if (!new dbStudent(a).exist()) {
                System.out.println("ID is incorrect.");
            }
            System.out.println("Name: " + ohasAuth.getName(a));
            System.out.println(" ID : " + a);
            new dbClass(name).addStudent(a);
            System.out.println("Continue to add students? 1/Yes 0/No");
            System.out.print("> ");
            a = scanner.nextInt();
        } while (a == 1);
        System.out.println("Class Information");
        System.out.println("Class Name: " + name);
        System.out.println(" Teachers : ");
        ArrayList<Integer> teachers = new dbClass(name).getTeachers();
        for (int i = 1; i <= teachers.size(); i++) {
            System.out.println(i + ". " + (ohasAuth.getName(teachers.get(i - 1))));
        }
        System.out.println(" Students : ");
        ArrayList<Integer> students = new dbClass(name).getStudents();
        for (int i = 1; i <= students.size(); i++) {
            System.out.println(i + ". " + (ohasAuth.getName(students.get(i - 1))));
        }
        System.out.println("Add class successful.");
    }

    //**************************���ù���*****************************
    //TODO public void setRule() {
        /*System.out.println("What would you like to set?");
        System.out.println("1.Reservation total time limit per month");
        System.out.println("2.Reservation chances per month");
        System.out.println("3.Reservation rule-break chances");
        //TODO System.out.println("4.Reservation rule-break punishment");
        System.out.println("0.Exit");
        int a = scanner.nextInt();
        switch (a) {
            case 2:
                System.out.println("Please input the chances(2~5)");
                ohasRules.setMonthlyChance(scanner.nextInt());
                break;
            case 1:
                System.out.println("Please input the time(60min~120min)");
                ohasRules.setMonthlyTime(scanner.nextInt());
                break;
            case 3:
                System.out.println("Please input the chances(0~2)");
                uiStudent.setRBC(a);
                break;
            /*case 4:
                System.out.println("Please choose punishment:");
                System.out.println("1. forbid from reserving for one week");
                System.out.println("2. forbid from reserving for one month");
                System.out.println("3. forbid from reserving for one semester");
                //**********************************ʵ��
                break;*/
            /*default:
                uiTeacher.setTeaching();
                break;
        }
    }*/
}
