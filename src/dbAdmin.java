//author: qiu shi

public class dbAdmin {
    private int id;
    private String name;

    dbAdmin(int id) {
        this.id = id;
        name = ohasAuth.getName(id);
    }

    int getID() {
        return id;
    }

    boolean exist() {
        return !(name.equals("error"));
    }
}
