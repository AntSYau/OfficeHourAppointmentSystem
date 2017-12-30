public class testClass {
    public static void main(String[] args) {
        try {
            //ohasAuth.signUp(11717,1,"QIU Shi","LALALAND");
            dbTeacher teacher = new dbTeacher(11717);
            System.out.println(teacher.delRegularOfficeHour(1,800));
            System.out.println(teacher.setRegularOfficeHour(1,800,1200,"N/A"));
            System.out.println(teacher.setRegularOfficeHour(1,1600,2000,"N/A"));
            System.out.println(teacher.setRegularOfficeHour(1,1100,1300,"N/A"));
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }
}
