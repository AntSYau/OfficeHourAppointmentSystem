import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class student {
	private static int rulebreakchance;
	private static int monthChance;
	private static int totalTime;
	public static int getMonthChance() {
		return student.monthChance;
	}
	public static void setMonthChance(int a) {
		student.monthChance = a;
	}public static int getTotalTime() {
		return student.totalTime;
	}
	public static void setTotalTime(int a) {
		student.totalTime = a;
	}
	public static int getRBC() {
		return student.rulebreakchance;
	}
	public static void setRBC(int a) {
		student.rulebreakchance = a;
	}
	
	
	/*public static boolean Existed() {
    	for(int i =0;;) {//�������ݿ�account��Ŀ
    		if(student.str==Login.DataBaseAccount[i]) {
    			return true;
    		}else {
    			return false;
    		}
    	}
	}*/
	public static Scanner scanner = new Scanner(System.in);
	
	public static void setUsername() {
    	System.out.println("Your current Username:"+Login.user1.getUsername());
    	System.out.println("Do you want to change it? Y/N");
    	String a = Login.sc.nextLine();
    	if (a == "Y") {
    		System.out.println("Please enter the new account name:");
    		String str = Login.sc.nextLine();
    		Login.user1.setUsername(str);// str ��ֵ��account�е��û�����
    		//database ����user1 ���û���
    	}else {
            //�ص�������
    	}
    }
    
	public static void setPassword() {
    	System.out.println("Your current password:"+Account.getPassword());
    	System.out.println("Do you want to change it? 1.Yes 2.No");
    	int a = scanner.nextInt();
    	if (a == 1) {
    		System.out.println("Please enter the new password:");
    		String str = scanner.nextLine();
    		Login.user1.setPassword(str);// str ��ֵ��account �е�������
    		//database ����user1 ������
    	}else {
    		student.PersonalInfo();//�ص�������
    	}
    }
    
    public static void PersonalInfo() {
		System.out.println("Your personal account Infomstion");
		System.out.println("Username:"+Login.user1.getUsername());
		System.out.println("ID      :"+Login.EnteredID);
		System.out.println("Password:"+Login.user1.getPassword());
		System.out.println("Classes:");
		Login.user1.getClasses().toString();
		System.out.println("What to do next? ");
		System.out.println("1. Change Username");
		System.out.println("2. Change Password");
		System.out.println("0. Exit ");
		System.out.println("Your Choice: ");
		int a = scanner.nextInt();
		switch(a){
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
    
    public static void CheckReservtion() {
		ArrayList<Reservation> list = Reservation.getReservationList();
		for (int i = 0; i<=list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println("What would you like to do? ");
		System.out.println("1. Add Reservation");
		System.out.println("2. Cancel Reseravtion");
		System.out.println("0. Exit");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				student.Reserve();
				break;
			case 2:
				student.CancelReservation();
				break;
			default: 
				student.MainMenu();
				break;
		}
	}
    

	public static void Reserve() {
    	Reservation r1 = new Reservation();
    	if (!Login.user1.ifOutOfMChance()) {
    		if(!Login.user1.ifOutOfRBChance()) {
    			if(!Login.user1.ifOutOfTTime()) {
		System.out.println("Please choose a Class you attended:     ");
		System.out.println("(Class# : select class. \"0\": cancel.)");
		Login.user1.getClasses().toString();
		for (int i =1; i<=Login.user1.getClasses().size();i++) {
			System.out.println(i+". "+Login.user1.getClasses().get(i-1));
		}
		int a = scanner.nextInt();
		if (a ==0) {
			student.MainMenu();
		}else {
		for (int i =1; i<=Login.user1.getClasses().size();i++) {
			if (i == a) {
				r1.setClasses(Login.user1.getClasses().get(i-1));
				break;
			}
		}
		}
		System.out.println("Please choose a Teacher:     ");
		System.out.println("(Class# : select class. \"0\": cancel.)");
		ArrayList<Account> TeacherList = new ArrayList<>();
		//��Ӹÿγ̵���ʦ��list�б�
		for (int i =1; i<=TeacherList.size();i++) {
			System.out.println(i+". "+TeacherList.get(i-1));
		}
		int b = scanner.nextInt();
		if (b ==0) {
			student.MainMenu();
		}else {
		for (int i =1; i<=TeacherList.size();i++) {
			if (i == b ) {
				r1.setTeacher(TeacherList.get(b-1));
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
		}else {
			student.CheckReservtion();
		}
    			}else {
    				System.out.println("Sorry, you are out of reservation time!");
            		scanner.nextLine();
            		student.CheckReservtion();
    			}
    		}else {
    			System.out.println("Sorry, you have break the rules too much that you are forbidon to reserve now!");
        		scanner.nextLine();
        		student.CheckReservtion();
    		}
    	}else {
    		System.out.println("Sorry, you are out of reservation chances this month!");
    		scanner.nextLine();
    		student.CheckReservtion();
    	}
	}
    
    public static void CancelReservation() {
    	Calendar calendar = Calendar.getInstance();
    	ArrayList<Reservation> list = Reservation.getReservationList();
    	System.out.println("Please enter the number of the Reservation you want to cancel: (enter 0 to cancel operation)");
		int a = scanner.nextInt();
		Calendar reservation  = list.get(a-1).getStartTime();
		if (reservation.getTimeInMillis()-calendar.getTimeInMillis()>= 43200000) {//��ǰ12Сʱ
		list.get(a-1).getReservationNum();
		//��Ӧɾ��database�е�ԤԼ
		list.remove(a-1);
		System.out.println("Removed successfully.");
		student.CheckReservtion();
    	}else {
    		System.out.println("Sorry, canceling invalid due to time limit");
    		scanner.nextLine();
    		student.CheckReservtion();
    	}	
	}
    
    public static void MainMenu() {
    	System.out.println("Hello, "+Login.user1.getUsername()+"What service would you like to apply?");
    	System.out.println("1. Personal Information");
    	System.out.println("2. Reservation");
    	System.out.println("0. Exit");
    	if (scanner .nextInt() == 1) {
    		student.PersonalInfo();
    	}else if (scanner .nextInt() == 2) {
    		student.CheckReservtion();
    	}else {
    		//exit
    	}
    }
    

}