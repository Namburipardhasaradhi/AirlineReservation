package in.srkr.project3.com;

public class AuthenticationManager {
    private static final String USERNAME = "BKPALR";
    private static final String PASSWORD = "BKPFE";

    public static boolean authenticate(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
