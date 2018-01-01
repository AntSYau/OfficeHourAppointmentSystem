public class testClass {
    public static void main(String[] args) {
        try {
            dbTeacher Teacher = new dbTeacher(11717);
            Teacher.addClass("CS102A");
            dbClass dbclass = new dbClass("CS102A");
            dbclass.addStudent(11710714);
            dbClass dbc1 = new dbClass("CS103A");
            dbc1.addStudent(11710714);
            Teacher.getOfficeHour(java.sql.Date.valueOf("2018-1-1"));
        } catch (Exception e) {
            sqlCommands.errorPrint(e);
        }
    }
}
