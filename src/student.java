import sun.rmi.runtime.Log;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class student {

    dbStudent operation;

    student(dbStudent operation) {
        this.operation = operation;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void setUsername() {
        System.out.println("Your current Username:" + Login.user1.getUsername());
        System.out.println("Do you want to change it? Y/N");
        String a = Login.sc.nextLine();
        if (a == "Y") {
            System.out.println("Please enter the new account name:");
            String str = Login.sc.nextLine();
            Login.user1.setUsername(str);// str ��ֵ��account�е��û�����
            //database ����user1 ���û���
        } else {
            //�ص�������
        }
    }

    public static void setPassword() {
        System.out.println("Your current password:" + Account.getPassword());
        System.out.println("Do you want to change it? 1.Yes 2.No");
        int a = scanner.nextInt();
        if (a == 1) {
            System.out.println("Please enter the new password:");
            String str = scanner.nextLine();
            Login.user1.setPassword(str);// str ��ֵ��account �е�������
            //database ����user1 ������
        } else {
            student.PersonalInfo();//�ص�������
        }
    }

    public static void PersonalInfo() {
        System.out.println("Your personal account Infomstion");
        System.out.println("Username:" + Login.user1.getUsername());
        System.out.println("ID      :" + Login.EnteredID);
        System.out.println("Password:" + Login.user1.getPassword());
        System.out.println("Classes:");
        Login.user1.getClasses().toString();
        System.out.println("What to do next? ");
        System.out.println("1. Change Username");
        System.out.println("2. Change Password");
        System.out.println("0. Exit ");
        System.out.println("Your Choice: ");
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                student.setUsername();
                break;
            case 2:
                student.setPassword();
                break;
            default:
                student.MainMenu();
                break;
        }
    }
    //*******************������Ϣ********************************************* 

    public static int CheckReservtion() {
        int key = 0;
        while (key == 0) {
            ArrayList<Reservation> list = Reservation.getReservationList();
            for (int i = 0; i <= list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
            System.out.println("What would you like to do? ");
            System.out.println("1. Add Reservation");
            System.out.println("2. Cancel Reseravtion");
            System.out.println("0. Exit");
            int a = scanner.nextInt();

            switch (a) {
                case 1:
                    key = student.Reserve();
                    break;
                case 2:
                    key = student.CancelReservation();
                    break;
                default:
                    return 0;
            }
        }
        return 0;
    }


    public int Reserve() {
        Reservation r1 = new Reservation();
        if (operation.getReservationChance() >= ohasRules.getMonthlyChance()) {
            System.out.println("Sorry, you are out of reservation chances this month!");
            scanner.nextLine();
            return 0;
        }
        if (operation.getRuleBreakTime() >= ohasRules.getRuleBreakChance()) {
            System.out.println("Sorry, you have break the rules too much that you are forbidon to reserve now!");
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
        ArrayList<dbClass> classlist = dbStudent.getClasses();
        for (int i = 1; i <= classlist.size(); i++) {
            System.out.println(i + ". " + classlist.get(i - 1).getName());
        }
        int a = scanner.nextInt();
        dbClass chosenClass;
        if (a == 0) {
            return 0;
        } else {
            chosenClass=classlist.get(a-1);
        }
        System.out.println("Please choose a Teacher:     ");
        System.out.println("(Teacher# : select teacher. \"0\": cancel.)");
        ArrayList<Integer> TeacherList = chosenClass.getTeachers();
        //��Ӹÿγ̵���ʦ��list�б�
        for (int i = 1; i <= TeacherList.size(); i++) {
            System.out.println(i + ". " + TeacherList.get(i - 1));
        }
        int b = scanner.nextInt();
        if (b == 0) {
            student.MainMenu();
        } else {
            for (int i = 1; i <= TeacherList.size(); i++) {
                if (i == b) {
                    r1.setTeacher(TeacherList.get(b - 1));
                    break;
                }
            }
        }
        System.out.println("Please choose a date and time");
        //

        //continued
        System.out.println(r1.toString());
        System.out.println("Confirm the new Reservation? Yes/1  No/0");
        if (scanner.nextInt() == 1) {
            //���r1 �����ݿ⣬�Զ����ɱ��
        } else {
            return 0;
        }
        return 0;
    }

    public static int CancelReservation() {
        Calendar calendar = Calendar.getInstance();
        ArrayList<Reservation> list = Reservation.getReservationList();
        System.out.println("Please enter the number of the Reservation you want to cancel: (enter 0 to cancel operation)");
        int a = scanner.nextInt();
        Calendar reservation = list.get(a - 1).getStartTime();
        if (reservation.getTimeInMillis() - calendar.getTimeInMillis() >= 43200000) {//��ǰ12Сʱ
            list.get(a - 1).getReservationNum();
            //��Ӧɾ��database�е�ԤԼ
            list.remove(a - 1);
            System.out.println("Removed successfully.");
            return 0;
        } else {
            System.out.println("Sorry, canceling invalid due to time limit");
            scanner.nextLine();
            return 0;
        }
    }

    public static void MainMenu() {
        int key = 0;
        while (key == 0) {
            System.out.println("Hello, " + Login.user1.getUsername() + "What service would you like to apply?");
            System.out.println("1. Personal Information");
            System.out.println("2. Reservation");
            System.out.println("0. Exit");
            if (scanner.nextInt() == 1) {
                student.PersonalInfo();
            } else if (scanner.nextInt() == 2) {
                key = student.CheckReservtion();
            } else {
                key = 1;
            }
        }
    }


}