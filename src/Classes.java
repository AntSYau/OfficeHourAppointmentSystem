import java.util.ArrayList;
import java.util.Scanner;

public class Classes {
    private String ClassName;
    private int ClassID;
    private int studentNum;
    private ArrayList<Account> Teachers;
    private ArrayList<Account> Students;

    public static Scanner scanner = new Scanner(System.in);

    public Classes(int ClassID) {
        this.ClassID = ClassID;
        getClassName();
        Teachers = getTeachers();
        Students = getStudents();
    }

    public void setClassName() {
        System.out.println("Please enter a new Name:");
        this.ClassName = scanner.nextLine();
    }

    public void setClassID() {
        System.out.println("Please enter a new ID:");
        this.ClassID = scanner.nextInt();
    }

    public String getClassName() {
        return this.ClassName;
    }

    public int getClassID() {
        return this.ClassID;
    }

    public int getStudentNumber() {
        this.studentNum = Students.size();
        return this.studentNum;
    }

    public void getTeachersSimple() {
        for (int i = 0; i < getTeachers().size(); i++) {
            System.out.println("[" + (i + 1) + "]" + getTeachers().get(i).getUsername() + "   " + getTeachers().get(i).getId());
        }
    }

    public void getStudentsSimple() {
        for (int i = 0; i < Students.size(); i++) {
            System.out.println(Students.get(i).getUsername() + "   " + Students.get(i).getId());
        }
    }

    public ArrayList<Account> getTeachers() {
        return Teachers;
    }

    public ArrayList<Account> getStudents() {
        return Students;
    }

    public void setTeachers(ArrayList<Account> teachers) {
        Teachers = teachers;
    }

    public String toString() {
        System.out.println("Class Number" + this.ClassID);
        System.out.println("Class Name" + this.ClassName);
        System.out.println("Student Number" + this.studentNum);
        System.out.println("Teachers:");
        this.getTeachersSimple();
        return null;
    }

}
