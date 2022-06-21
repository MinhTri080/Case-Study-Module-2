package Model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;
    List<Order> orders = new ArrayList<>();


    public User() {
    }

    public User(long id, String username, String password, String name, String phone, String address, String email, Model.Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public static User parseUser(String row) {
        User user = new User();
        String[] fields = row.split(",");
        user.id = Long.parseLong(fields[0]);
        user.username = fields[1];
        user.password = fields[2];
        user.name = fields[3];
        user.phone = fields[4];
        user.address = fields[5];
        user.email = fields[6];
        user.role = Role.parseRole(fields[7]);
        user.createdAt = Instant.parse(fields[8]);
        String temp = fields[9];
        if (temp != null && !temp.equals("null")) {
            user.updatedAt = Instant.parse(temp);
        }

        return user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Model.Role getRole() {
        return role;
    }

    public void setRole(Model.Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id +
                "," + username +
                "," + password +
                "," + name +
                "," + phone +
                "," + email +
                "," + address +
                "," + role +
                "," + createdAt +
                "," + updatedAt;

    }
}
