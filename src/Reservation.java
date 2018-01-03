import java.util.ArrayList;
import java.util.Calendar;

public class Reservation {
    private Classes Class;
    private String date;
    private String time;
    private String address;
    private Account teacher;
    private int teacherId;
    private Account student;
    private int studentId;
    private boolean presence = true;
    private int reservationNum;
    
	public Reservation() {
    	String Class;
    	String date;
    	String time;
    	String address;
    	String teacher;
    	int teacherId;
    	String student;
    	int studentId;
    	boolean presence;
    	int reservationNum;
    }
    
	public Classes getClasses() {
		return this.Class;
	}
	public String getDate() {
		return this.date;
	}
	public String getTime() {
		return this.time;
	}
	public String getAddress() {
		return this.address;
	}
	public Account getTeacher() {
		return this.teacher;
	}
	public Account getStudent() {
		return this.student;
	}
	public int getTeacherId() {
		return this.teacherId;
	}
	public int getStudentId() {
		return this.studentId;
	}
	public int getReservationNum() {
		return this.reservationNum;
	}
	public boolean getPresence() {
		return this.presence;
	}
	
	public void setPresence(boolean a) {
		this.presence = a;
	}
	public void  setClasses(Classes classes) {
		  this.Class = classes;
	}
	public void  setDate(String str) {
		  this.date= str;
	}
	public void  setTime(String str) {
		  this.time= str;
	}
	public void  setAddress(String str) {
		  this.address= str;
	}
	public void  setTeacher(int id) {
		  Account str = Teacher.getTeacher(id);
		  this.teacher= str;
	}
	public void  setTeacher(Account str) {
		  this.teacher= str;
	}
	public void  setStudent(int id) {
		Account str = Teacher.getStudent(id);
		  this.student= str;
	}
	public void  setTeacherId(int str) {
		  this.teacherId= str;
	}
	public void  setStudentId(int str) {
		  this.studentId= str;
	}
	public void setReservationNum(int str) {
		this.reservationNum = str;
	}
	
	public static ArrayList<Reservation> getReservationList() {
		ArrayList<Reservation> ReservationList = new ArrayList<>(); 
		if (Account.getIdentity() == Login.Identity.TEACHER) {
			Reservation r1 = new Reservation();//temp
			//数据库调用TeacherName为Account.Name的 所有预约
			ReservationList.add(r1);//temp
		}else if (Account.getIdentity() == Login.Identity.STUDENT) {
			Reservation r1 = new Reservation();//temp
			//数据库调用Name为Account.Name的 所有预约
			ReservationList.add(r1);
		}
		return ReservationList;
	}
	
	
	public void addReservation() {
		Reservation reservation = new Reservation();
		//数据库添加预约
	}
	
	public void cancelReservation() {
		//数据库删除预约
	}
	public String toString() {
		System.out.println("Reservation Number:"+ this.getDate());
		System.out.println("Class Name:"+ this.getClasses());
		System.out.print("Date:"+ this.getDate());
		System.out.println("\tTime:"+ this.getTime());
		System.out.println("Address:"+ this.getAddress());
		System.out.print("Teacher:"+ this.getTeacher());
		System.out.println("\tStudent:"+ this.getStudent());
		System.out.println("Student ID:"+ this.getStudentId());
		return null;
	}

	public Calendar getStartTime() {
		Calendar c1 = Calendar.getInstance();
    	//获取开始时间
    	return c1;
	}
	
}
