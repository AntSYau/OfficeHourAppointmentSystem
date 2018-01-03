import java.util.ArrayList;
import java.util.Scanner;

public class uiTeacher {
	public static Scanner scanner = new Scanner(System.in);
	private int rulebreakchance;
	public int getRBC() {
		return this.rulebreakchance;
	}
	public void setRBC(int a) {
		this.rulebreakchance = a;
	}
	public static void MainMenu() {
    	System.out.println("Hello, "+ uiLogin.user1.getUsername()+"What service would you like to apply?");
    	System.out.println("1. Personal Information");
    	System.out.println("2. Reservation");
    	System.out.println("3. Set Reservation Rules");
    	System.out.println("4. Set Classes and Rules");
    	System.out.println("0. Exit");
    	if (scanner .nextInt() == 1) {
    		uiTeacher.PersonalInfo();
    	}else if (scanner .nextInt() == 2) {
    		uiTeacher.CheckReservtion();
    	}else if (scanner .nextInt() == 3) {
    		uiTeacher.setReservationRules();
    	}else if(scanner .nextInt() == 4) {
    		uiTeacher.setTeaching();
    	}else {
    		//exit
    	}
    }
	
	public static void setReservationRules() {
		System.out.println("What would you like to set?");
		System.out.println("1. set office hour");
		System.out.println("2. set Work days");
		System.out.println("3. set reservation time Interval");
		System.out.println("0. exit");
		switch (scanner.nextInt()) {
		case 1:
			uiTeacher.setOfficeHour();
			break;
        case 2:
			uiTeacher.setWorkDays();
			break;
        case 3:
	       uiTeacher.setReserInTime();
	        break;
		default:
			uiTeacher.MainMenu();
			break;
		}
	}
	
	public static void setWorkDays() {
		System.out.println("Please input your work day's end in the form of DD/MM/YYYY:");
		
		//******************************************
		System.out.println("Successful.");
		scanner.nextLine();
		uiTeacher.setReservationRules();
	}
	public static void setReserInTime() {
		//����ԤԼ����ʱ����*****************************************
	}
	//*************************����*********************************
	
	public static void setUsername() {
    	System.out.println("Your current Username:"+ uiLogin.user1.getUsername());
    	System.out.println("Do you want to change it? Y/N");
    	String a = uiLogin.sc.nextLine();
    	if (a == "Y") {
    		System.out.println("Please enter the new account name:");
    		String str = uiLogin.sc.nextLine();
    		uiLogin.user1.setUsername(str);// str ��ֵ��account�е��û�����
    		//database 
    	}else {
    		uiTeacher.MainMenu();
        }
    }
    
	public static void setPassword() {
    	System.out.println("Your current password:"+Account.getPassword());
    	System.out.println("Do you want to change it? Y/N");
    	String a = uiLogin.sc.nextLine();
    	if (a == "Y") {
    		System.out.println("Please enter the new password:");
    		String str = uiLogin.sc.nextLine();
    		uiLogin.user1.setPassword(str);
    		//database  ����user1 �Ķ�Ӧ����
    	}else {
    		System.out.println("***********Changes discarded***********");
    		uiTeacher.MainMenu();
        }
    }
    
    public static void PersonalInfo() {
		System.out.println("Your personal account Infomstion");
		System.out.println("Username:"+ uiLogin.user1.getUsername());
		System.out.println("ID      :"+ uiLogin.EnteredID);
		System.out.println("Password:"+ uiLogin.user1.getPassword());
		System.out.println("Classes:"+ uiLogin.user1.getClasses());
		System.out.println("What to do next? ");
		System.out.println("1. Change Username");
		System.out.println("2. Change Password");
		System.out.println("0. Exit ");
		System.out.println("Your Choice: ");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				uiTeacher.setUsername();
				break;
			case 2:
				uiTeacher.setPassword();
				break;
			default: 
				uiTeacher.MainMenu();
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
		System.out.println("1. Cancel Reseravtion");
		System.out.println("2. Set Reseravtion");
		System.out.println("0. Exit");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				uiTeacher.cancleReservation();
				break;
			case 2:
				uiTeacher.setReservation();
			default: 
				uiTeacher.MainMenu();
				break;
		}
	}
   
    public static void cancleReservation() {
    	ArrayList<Reservation> list = Reservation.getReservationList();
    	System.out.println("Please enter the number of the Reservation you want to cancel: (enter 0 to cancel operation)");
		int a = scanner.nextInt();
		if (a>0) {
		int b = list.get(a-1).getReservationNum();
		//refer to reservation according to reservationNum
		//��Ӧɾ��database�е�ԤԼ
		list.remove(a-1);
		System.out.println("Removed successfully.");
		uiTeacher.CheckReservtion();
		}else {
			uiTeacher.CheckReservtion();
		}
    }
    public static void setReservation() {
        	ArrayList<Reservation> list = Reservation.getReservationList();
        	System.out.println("Please enter the number of the Reservation you want to set: (enter 0 to cancel operation)");
    		int a = scanner.nextInt();
    		Reservation r1 = list.get(a-1);
    		if (a>0) {
    			System.out.println("What would you like to change?");
    			System.out.println("1. Date");
    			System.out.println("2. Time");
    			System.out.println("3. Address");
    			System.out.println("4. uiTeacher");
    			System.out.println("5. presence");
    			System.out.println("0. exit");
    			switch (scanner.nextInt()) {
				case 1:
					System.out.println("Please input the date you want to apply in the form of DD/MM/YYYY");
					String str = scanner.nextLine();
					//ת��Ϊcalendar
					r1.setDate(str);
					//database  ��������
					System.out.println("Successful.");
					scanner.nextLine();
					uiTeacher.CheckReservtion();
					break;
				case 2:
					System.out.println("Please input the time you want to apply");
					//�б�
					r1.setTime("");//*********************************************
					//database  ʱ������
					System.out.println("Successful.");
					scanner.nextLine();
					uiTeacher.CheckReservtion();
					break;
					
				case 3:
					System.out.println("Please input the address you want to switch to");
					String str3 = scanner.nextLine();
					r1.setAddress(str3);
					//database  �ص�����
					System.out.println("Successful.");
					scanner.nextLine();
					uiTeacher.CheckReservtion();
					break;
				case 4:
					System.out.println("Please input the id of the teacher you want to switch to");
					a = scanner.nextInt();
					r1.setTeacher(a);
					//database  ��ʦ����
					System.out.println("Successful.");
					scanner.nextLine();
					uiTeacher.CheckReservtion();
				case 5: 
					System.out.println("Please input the presence of the uiStudent    1. good 0. bad");
					a = scanner.nextInt();
					if (a ==0 ) {
						Account.breakRule();
						r1.setPresence(false);
					}
					System.out.println("Successful.The presence of the uiStudent is" + r1.getPresence());
					uiTeacher.setReservation();
					break;
				default:
					uiTeacher.CheckReservtion();
					break;
				}
    		}
    		
    	
	}
    
    //*************************ԤԼ����**************************
    public static void setTeaching() {
    	System.out.println("What would you like to do? ");
		System.out.println("1. Set Class");
		System.out.println("2. Set Reservation Rules");
		System.out.println("0. Exit ");
		System.out.println("Your Choice: ");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				uiTeacher.ClassMenu();
				break;
			case 2:
				uiTeacher.setRule();
				break;
			default: 
				uiTeacher.MainMenu();
				break;
		}
    }
    //**********************�༶*****************************
    public static void ClassMenu() {
    	System.out.println("What would you like to do? ");
		System.out.println("1. Add New Class");
		System.out.println("2. Check Previous Class");
		System.out.println("0. Exit ");
		System.out.println("Your Choice: ");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				uiTeacher.addNewClass();
				break;
			case 2:
				uiTeacher.checkClass();
				break;
			default: 
				uiTeacher.MainMenu();
				break;
		}
    }
    
    public static void checkClass() {
		int a = uiLogin.user1.getClasses().size();
		for (int i=0;i<a;i++) {
			uiLogin.user1.getClasses().get(i).toString();
		}
    	System.out.println("What would you like to do? ");
    	System.out.println("1. set Class");
    	System.out.println("2. delete Class");
    	System.out.println("0. exit");
    	switch (scanner.nextInt()) {
		case 1:
			uiTeacher.setClasses();
			break;
		case 2:
			uiTeacher.deleteClass();
			break;
		default:
			uiTeacher.ClassMenu();
			break;
		}
	}
    public static Classes getClasses(int i) {
		Classes classes = new Classes();
    	//database
    	return classes;
	}
    
    public static void setClasses() {
    	System.out.println("Please enter Class Number:");
    	int a = scanner.nextInt();
		Classes classes = uiTeacher.getClasses(a);
		System.out.println("Please choose your operation");
		System.out.println("1. set Class Name");
		System.out.println("2. set Class uiTeacher");
		System.out.println("3. set Class Students");
		System.out.println("0. exit");
    	switch (scanner.nextInt()) {
		case 1:
			classes.setClassName();
			//database
			System.out.println("Successful");
			scanner.nextLine();
			uiTeacher.setClasses();
			break;
		case 2:
			classes.getTeachersSimple();
			System.out.println("Please choose your operation");
			System.out.println("1. Remove");
			System.out.println("2. Add");
			System.out.println("0.exit");
			switch (scanner.nextInt()) {
			case 1:
				do {
				classes.getTeachersSimple();
				System.out.println("Please choose the teacher you want to Remove:");
				a = scanner.nextInt();
				classes.getTeachers().remove(a-1);
				//database  ��classȥ������ʦ
				//database  ����ʦɾ����classes
				System.out.println("Successful.Continue?  1. Yes  2.No");
				a=scanner.nextInt();
				}while(a==1);
				uiTeacher.setClasses();
				break;
			case 2:
				do {
					System.out.println("Please enter uiTeacher's ID");
					a = scanner.nextInt();
					Account t1 = getTeacher(a);
					System.out.println("Name"+getTeacher(a).getUsername());
					System.out.println("ID"+getTeacher(a).getId());
					System.out.println("Confirm to add?  1.Yes  2.No" );
					a = scanner.nextInt();
					if (a == 1) {
						classes.getTeachers().add(t1);
						//database ��class ��Ӹ���ʦ
						//database �����ʦ��Ӹ�classes
						System.out.println("Successful.");
					}else {
				       System.out.println("Discarded");
					}
					System.out.println("Continue? 1.Yes 2.No");
					a = scanner.nextInt();
				}while (a ==1);
				uiTeacher.setClasses();
				break;
			default:
				uiTeacher.setClasses();
				break;
			}
			
		case 3:
			classes.getStudentsSimple();
			System.out.println("Please choose your operation");
			System.out.println("1. Remove");
			System.out.println("2. Add");
			System.out.println("0.exit");
			switch (scanner.nextInt()) {
			case 1:
				do {
				classes.getStudentsSimple();
				System.out.println("Please choose the uiStudent you want to Remove:");
				a = scanner.nextInt();
				classes.getStudents().remove(a-1);
				//database  �ڸ�classes��ɾ����ѧ��
				//database  �ڸ�ѧ����ɾ����class
				System.out.println("Successful.Continue?  1. Yes  2.No");
				a=scanner.nextInt();
				}while(a==1);
				uiTeacher.setClasses();
				break;
			case 2:
				do {
					System.out.println("Please enter Student's ID");
					a = scanner.nextInt();
					Account t1 = getStudent(a);
					System.out.println("Name"+getStudent(a).getUsername());
					System.out.println("ID"+getStudent(a).getId());
					System.out.println("Confirm to add?  1.Yes  2.No" );
					a = scanner.nextInt();
					if (a == 1) {
						classes.getStudents().add(t1);
						//database  �ڸ�classes����Ӹ�ѧ��
						//database  �ڸ�ѧ������Ӹ�class
						System.out.println("Successful.");
					}else {
				       System.out.println("Discarded");
					}
					System.out.println("Continue? 1.Yes 2.No");
					a = scanner.nextInt();
				}while (a ==1);
				uiTeacher.setClasses();
				break;
			default:
				uiTeacher.setClasses();
				break;
			}break;
		
		default:
			uiTeacher.checkClass();
		break;
    	}
	}
    
    public static void deleteClass() {
    	int a;
    	do {
    		a = uiLogin.user1.getClasses().size();
    		for (int i=0;i<a;i++) {
    			System.out.println("["+(i+1)+"]");
    			uiLogin.user1.getClasses().get(i).toString();
    		}
    		System.out.println("Please input Class you want to remove:");
    		a = scanner.nextInt();
    		Classes classes = uiLogin.user1.getClasses().get(a-1);
    		classes.toString();
			System.out.println("Do you want to remove this Class? 1.Yes 2.No");
			a = scanner.nextInt();
			uiLogin.user1.getClasses().remove(uiLogin.user1.getClasses().get(a-1));
			//database ���к���classes��Account ɾ����classes
			System.out.println("Successful.Continue?  1. Yes  2.No");
			a=scanner.nextInt();
			}while(a==1);
			uiTeacher.setClasses();
	}
    
    public static void setOfficeHour() {
    	ArrayList<Integer> a1 = new ArrayList<>();
		System.out.println("Please input your office hour:(Mutiple choises, end up with 0)");
		System.out.println("Mon(1)  Tue(2)  Wed(3)  Thu(4)  Fri(5)  Sat(6)  Sun(7)");
		int a = scanner.nextInt();
		while(a != 0) {
			a1.add(a);
		}
		
		System.out.println("");
	}
    
    
    public static void addNewClass() {
    	Classes classes = new Classes();
    	System.out.println("Please enter Class name:");
		classes.setClassName();
		System.out.println("Please enter Class id:");
		classes.setClassID();
		System.out.println("Please add teachers to the Class:");
		int a ;
		do  {
			System.out.println("Please enter ID of the uiTeacher");
			a =scanner.nextInt();
			Account t1 = getTeacher(a);
			classes.getTeachers().add(t1);
			System.out.println("Continue to add teachers? ");
			System.out.println("1. Yes");
			System.out.println("0. No");
			a = scanner.nextInt();
		}while (a==1);
		System.out.println("Please add students to the Class:");
		do  {
			System.out.println("Please enter ID of the Student");
			a =scanner.nextInt();
			Account t1 = getStudent(a);
			classes.getStudents().add(t1);
			System.out.println("Continue to add students? ");
			System.out.println("1. Yes");
			System.out.println("0. No");
			a = scanner.nextInt();
		}while (a==1);
		System.out.println("Class Infomstion");
		System.out.println("Class Name:"+classes.getClassName());
		System.out.println("Class ID�� " + classes.getClassID());
		System.out.println("Teachers:");
		classes.getTeachersSimple();
		System.out.println("Students��");
		classes.getStudentsSimple();
		System.out.println("Do you comfirm to add this Class?");
		System.out.println("1. Yes");
		System.out.println("0. No");
		a =scanner.nextInt();
		if (a == 1) {
			//database  �����ἰ��Account��Ӹ�classes
			System.out.println("Successful");
			scanner.nextLine();
			uiAdministrator.AccountMenu();
		}else {
			System.out.println("Canceled");
			scanner.nextLine();
		}
    }
    
    //**************************��ȡ��ʦ*************************
    public static Account getTeacher(int id) {
    	Account teacher1 = new Account();
    	//����id��ȡ��ʦ
    	return teacher1;
    }
    public static Account getStudent(int id) {
    	Account student1 = new Account();
    	//����id��ȡѧ��
    	return student1;
    }
    
    //**************************���ù���*****************************
    public static void setRule() {
		System.out.println("What would you like to set?");
		System.out.println("1.Reservation total time limit per month");
		System.out.println("2.Reservation chances per month");
		System.out.println("3.Reservation rule-break chances");
		System.out.println("4.Reservation rule-break punishment");
		System.out.println("0.Exit");
		int a  = scanner.nextInt();
		switch (a) {
		case 2:
			System.out.println("Please input the chances(2~5)");
			uiStudent.setMonthChance(scanner.nextInt());
			break;
        case 1:
        	System.out.println("Please input the time(60min~120min)");
			uiStudent.setTotalTime(scanner.nextInt());
            break;
        case 3:
        	System.out.println("Please input the time(0~2)");
			uiStudent.setRBC(a);
			break;
        case 4:
        	System.out.println("Please choose punishment:");
			System.out.println("1. forbid from reserving for one week");
			System.out.println("2. forbid from reserving for one month");
			System.out.println("3. forbid from reserving for one semester");
			//**********************************ʵ��
			break;
		default:
			uiTeacher.setTeaching();
			break;
		}
    }
}
