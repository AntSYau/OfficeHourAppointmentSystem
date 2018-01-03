import java.util.Scanner;

public class uiAdministrator {
    public static Scanner scanner = new Scanner(System.in);
	public static Account user2 = new Account();
	private int dayLimit;
	public int getDayLimit() {
		return this.dayLimit;
	}
	
	public static void MainMenu() {
    	System.out.println("Hello, "+ uiLogin.user1.getUsername()+"What service would you like to apply?");
    	System.out.println("1. Personal Information");
    	System.out.println("2. Account Setting");
    	System.out.println("3. Set Reservation Day Limit");
    	System.out.println("4. Set try chances");
    	System.out.println("5. Set rule break ");
    	System.out.println("6. clear rule break chances");
    	System.out.println("0. Exit");
    	switch(scanner .nextInt()) {
    	case 1: 
    		uiAdministrator.PersonalInfo();
    		break;
    	case 2: 
    		uiAdministrator.AccountMenu();
    		break;
    	case 3: 
    		uiAdministrator.setDayLimit();
    		break;
    	case 4:
    		uiAdministrator.setTryChances();
    		break;
    	case 5:
    		uiAdministrator.setRuleBreakChance();
    		break;	
    	case 6:
    		uiAdministrator.ClearRuleBreakChanceAll();
    		break;
    	default:
            //exit
    		break;
    	}
    	
    }
	//*************************����*********************************
	
	public static void setUsername() {
    	System.out.println("Your current Username:"+ uiLogin.user1.getUsername());
    	System.out.println("Do you want to change it? Y/N");
    	String a = uiLogin.sc.nextLine();
    	if (a == "Y") {
    		System.out.println("Please enter the new account name:");
    		String str = uiLogin.sc.nextLine();
    		uiLogin.user1.setUsername(str);
    		//database  ����user1 ���û���
    	}else {
    		uiAdministrator.MainMenu();
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
    		//database  ����user1 ������
    	}else {
    		System.out.println("***********Changes discarded***********");
    		scanner.nextLine();
    		uiAdministrator.MainMenu();
        }
    }
    
    public static void PersonalInfo() {
		System.out.println("Your personal account Infomstion");
		System.out.println("Username:"+ uiLogin.user1.getUsername());
		System.out.println("ID      :"+ uiLogin.EnteredID);
		System.out.println("Password:"+ uiLogin.user1.getPassword());
		System.out.println("Classes:"+ uiLogin.user1.getClasses());//�༶�б�����������
		System.out.println("What to do next? ");
		System.out.println("1. Change Username");
		System.out.println("2. Change Password");
		System.out.println("0. Exit ");
		System.out.println("Your Choice: ");
		int a = scanner.nextInt();
		switch(a){
			case 1: 
				uiAdministrator.setUsername();
				break;
			case 2:
				uiAdministrator.setPassword();
				break;
			default: 
				uiAdministrator.MainMenu();
				break;
		}
	}
    //*******************������Ϣ*********************************************
    public static void addAccount() {
		Account user = new Account();
    	System.out.println("Please enter Username:");
		user.setUsername(scanner.nextLine());
		System.out.println("Please enter Password:");
		user.setPassword(scanner.nextLine());
		System.out.println("Please enter Id:");
		user.setId(scanner.nextInt());
		System.out.println("Please enter Identity");
		user.setIdentity(uiLogin.Identity.getIdentity(scanner.nextInt()));
        //System.out.println("Please enter Classes");
		//user.setClasses(scanner.nextLine());	
		System.out.println("Account Infomstion");
		System.out.println("Username:"+user.getUsername());
		System.out.println("ID      :"+user.getId());
		System.out.println("Password:"+user.getPassword());
		System.out.println("Classes:"+user.getClasses());
		System.out.println("Identity:"+ Account.getIdentity());
		System.out.println("Do you comfirm to add this account?");
		System.out.println("1. Yes");
		System.out.println("0. No");
		int a =scanner.nextInt();
		if (a == 1) {
			//database ����Account����������
			System.out.println("Successful");
			scanner.nextLine();
			uiAdministrator.AccountMenu();
		}else {
			System.out.println("Canceled");
			scanner.nextLine();
			uiAdministrator.AccountMenu();
		}
    }
    public static Account getAccount(int id) {
    	//ͨ��idѰ���˻�����ȡ��Ӧ��Ϣ
    	//����user��Ϣ
    	Account user = new Account();
    	return user;
    }
    public static void deleteAccount() {
		System.out.println("Please enter ID:");
		int a = scanner.nextInt();
		uiAdministrator.getAccount(a);
		System.out.println("Account Infomstion");
		System.out.println("Username:"+user2.getUsername());
		System.out.println("ID      :"+a);
		System.out.println("Password:"+user2.getPassword());
		System.out.println("Classes:"+user2.getClasses());
		System.out.println("Identity:"+ Account.getIdentity());
		System.out.println("Do you confirm to delete this Account? Y/N");
		if (scanner.nextLine() == "Y") {
			if(Account.getIdentity()!= uiLogin.Identity.ADMINISTRATOR) {
				System.out.println("Please confirm your password.");
				if (scanner.nextLine() == uiLogin.getTempPassword()) {
					//delete Account
					System.out.println("Deleted.");
					uiAdministrator.deleteAccount();
				}else {
					System.out.println("Wrong Password!");
					uiAdministrator.deleteAccount();
				}
			}else {
				System.out.println("You can't delete an administrator!");
				uiAdministrator.deleteAccount();
			}
			
		}else {
			uiAdministrator.deleteAccount();
		}
	}
    public static void setAccount() {
    	System.out.println("Please enter ID:");
		int a = scanner.nextInt();
		uiAdministrator.getAccount(a);
		System.out.println("Account Infomstion");
		System.out.println("Username:"+user2.getUsername());
		System.out.println("ID      :"+a);
		System.out.println("Password:"+user2.getPassword());
		System.out.println("Classes:"+user2.getClasses());
		System.out.println("What to do next?  :");
		System.out.println("1. set Username");
		System.out.println("2. set Password");
		System.out.println("3. set ID");
		//System.out.println("4. set Classes");
		System.out.println("4. set Identity");
		System.out.println("0. exit");
		switch (scanner.nextInt()) {
		case 1:
			System.out.println("Please enter the new Username");
			String string = scanner.nextLine();
			user2.setUsername(string);
			//database  ���ĸ��û�������
			System.out.println("Operation Completed. Continue? 1. Yes 0. No");
			if(scanner.nextInt() == 1) {
				uiAdministrator.setAccount();
			}else {
			    uiAdministrator.MainMenu();
			}
			break;
		case 2:
			System.out.println("Please enter the new Password");
			String string2 = scanner.nextLine();
			user2.setPassword(string2);
			//database  ���ĸ��û�������
			System.out.println("Operation Completed. Continue? 1. Yes 0. No");
			if(scanner.nextInt() == 1) {
				uiAdministrator.setAccount();
			}else {
			    uiAdministrator.MainMenu();
			}
			break;
		case 3:
			System.out.println("Please enter the new ID");
			int Int = scanner.nextInt();
			user2.setId(Int);
			//database   ���ĸ��û���id
			System.out.println("Operation Completed. Continue? 1. Yes 0. No");
			if(scanner.nextInt() == 1) {
				uiAdministrator.setAccount();
			}else {
			    uiAdministrator.MainMenu();
			}
			break;
		//case 4:
			/*System.out.println("Please enter the new Classes");
			String string3  = scanner.nextLine();
			ArrayList<Classes> str = getClasses(string3);
			user2.setClasses(str);
			//database
			System.out.println("Operation Completed. Continue? 1. Yes 0. No");
			if(scanner.nextInt() == 1) {
				uiAdministrator.setAccount();
			}else {
			    uiAdministrator.MainMenu();
			}*/
			//uiAdministrator.MainMenu();
			//break;
		case 4:
			System.out.println("Please enter the new Identity");
			System.out.println("[1] uiTeacher");
			System.out.println("[2] Student");
			int Int2 = scanner.nextInt();
			user2.setIdentity(uiLogin.Identity.getIdentity(Int2));
			//database  �޸ĸ��˻������
			System.out.println("Operation Completed. Continue? 1. Yes 0. No");
			if(scanner.nextInt() == 1) {
				uiAdministrator.setAccount();
			}else {
			    uiAdministrator.MainMenu();
			}
			break;
		default:
			uiAdministrator.MainMenu();
			break;
		}
    }
    
    public static void AccountMenu() {
    	System.out.println("Please choose your operation:");
		System.out.println("1. set Account");
		System.out.println("2. add Account");
		System.out.println("3. delete Account");
		System.out.println("0. exit");
		switch(scanner.nextInt()) {
		case 1 :
			uiAdministrator.setAccount();
			break;
		case 2 :
			uiAdministrator.addAccount();
		    break;
		case 3 : 
			uiAdministrator.deleteAccount();
			break;
		default:
			uiAdministrator.MainMenu();
		}
    }
//********************************* account ******************************
    public static void setDayLimit() {
    	uiAdministrator administrator = new uiAdministrator();
    	System.out.println("Current day limit:"+administrator.getDayLimit());
    	System.out.println("Do you want to change Day Limit?");
    	System.out.println("1. Yes");
    	System.out.println("0. No");
    	if (scanner.nextInt() == 1) {
    		System.out.println("Please enter a day limit (3~15):");
    		int a = scanner.nextInt();
    		if (a<=15&&a>=3) {
    			administrator.dayLimit = a;	
    		}else {
    			System.out.println("Invalid integer!");
    			scanner.nextLine();
    			uiAdministrator.setDayLimit();
    		} 
    	}else {
			uiAdministrator.MainMenu();
		}
    }
  //********************************* dayLimit ******************************  
    public static void setTryChances() {
    	System.out.println("Current day limit:"+ uiLogin.getTryChances());
    	System.out.println("Do you want to change Try Chances?");
    	System.out.println("1. Yes");
    	System.out.println("0. No");
    	if (scanner.nextInt() == 1) {
    		System.out.println("Please enter the number of the attempts (3~10):");
    		int a = scanner.nextInt();
    		if (a<=10&&a>=3) {
    			uiLogin.setTryChances();
    			System.out.println("Operation complete.");
    			scanner.nextLine();
    			uiAdministrator.setTryChances();
    		}else {
    			System.out.println("Invalid integer!");
    			scanner.nextLine();
    			uiAdministrator.setTryChances();
    		} 
    	}else {
			uiAdministrator.MainMenu();
		}
    }
  //********************************* TryChances****************************** 
    public static void setRuleBreakChance() {
    	System.out.println("Please input the ID of the Student:");
    	Account s1= uiTeacher.getStudent(scanner.nextInt());
    	System.out.println("Please input the times that rule broken by this Student:");
    	Account.setRuleBreakChance(scanner.nextInt());
    	//database  break �ı�
    	System.out.println("Successful");
    	scanner.nextLine();
    	uiAdministrator.MainMenu();
    }
    public static void ClearRuleBreakChanceAll() {
    	//���ݿ���������break��Ϊ0
    	System.out.println("Successful");
    	scanner.nextLine();
    	uiAdministrator.MainMenu();
    }
}
