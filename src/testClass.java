public class testClass {
    public static void main(String[] args) {
        try {
            dbTeacher Teacher = new dbTeacher(11717);
            Teacher.getOfficeHour(java.sql.Date.valueOf("2018-1-1"));
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }
}
