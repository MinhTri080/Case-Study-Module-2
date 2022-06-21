package vn.mt.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String role;

    public User() {
    }

    public static User parseUser(String raw) {
        String[] array = raw.split(",");
        User user = new User();
        user.setUserID(Integer.parseInt(array[0]));
        user.setUserName(array[1]);
        user.setPassword(array[2]);
        user.setRole(array[3]);
        return user;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", userID, userName, password, role);
    }
}
