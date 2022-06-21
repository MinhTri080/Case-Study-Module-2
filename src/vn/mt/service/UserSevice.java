package vn.mt.service;

import vn.mt.model.User;
import vn.mt.utils.CSVUtils;
import vn.mt.utils.InstantUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class UserSevice implements IUserService {
    private static final String PATH = "data/users.csv";

    @Override
    public User adminlogin(String username, String password) {
        List<User> users = getUser();
        for (User user : users) {
            if (user.getUserName().equals(username) &&
                    user.getPassword().equals(password) &&
                    user.getRole().equals("ADMIN")) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUser() {
        List<String> lines = CSVUtils.read(PATH);
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            User user = User.parseUser(line);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserByID(int id) {
        List<User> userList = getUser();
        for (User user : userList) {
            if (user.getUserID() == id) {
                return user;
            }
        }
        return null;
    }
}
