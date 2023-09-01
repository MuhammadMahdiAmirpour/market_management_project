package ir.ac.kntu.objects;

public class Admin {

    private static Admin instance;

    private static String username = "admin";
    private static String password = "12341234";


    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void setPassword(String input) {
        password = input;
    }
    public void setUsername(String input) {
        username = input;
    }
    public String getPassword() {
        return password;
    }


    public String getUserName() {
        return username;
    }

    public Admin() {}
}
