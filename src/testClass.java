public class testClass {
    public static void main(String[] args) {
        try {
            System.out.println(new dbStudent(21).toString());
            dbStudent student = ohasAuth.stuSignIn(21, "QIUSHI");
            System.out.println(student.exist());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
