package services;

import Model.Role;
import Model.User;
import untils.CSVUtils;
import untils.InstantUntils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class UserSevice implements IUserService {
    public static String PATH = "data/user.csv";
    private static UserSevice instance;
    private untils.InstantUntils InstantUntils;

    public UserSevice() {

    }

    public static UserSevice getInstance() {
        if (instance == null)
            instance = new UserSevice();
        return instance;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public User adminLogin(String username, String password) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equals(Role.ADMIN)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User newUser) {
        newUser.setCreatedAt(Instant.now());
        List<User> users = findAll();
        users.add(newUser);
        CSVUtils.write(PATH, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                String fullName = newUser.getName();
                if (fullName != null && !fullName.isEmpty())
                    user.setName(fullName);
                String phone = newUser.getPhone();
                if (phone != null && !phone.isEmpty())
                    user.setPhone(newUser.getPhone());
                String address = newUser.getAddress();
                if (address != null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                user.setUpdatedAt(Instant.now());
                CSVUtils.write(PATH, users);
                break;
            }
        }
    }

    @Override
    public boolean existById(int id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String userName) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public User findById(int id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public void removeID(int id) {
        List<User> users = findAll();
        users.removeIf(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getId() == id;
            }
        });
        CSVUtils.write(PATH, users);

    }

    @Override
    public void sortByName(List<User> users) {
        users.sort(new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("----------------------------------------- DANH SÁCH NGƯỜI DÙNG--------------------------------------- ");
        System.out.printf("%-15s %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        for (User user : users
        ) {
            System.out.printf("%-15d %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n",
                    user.getId(),
                    user.getName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getRole(),
                    untils.InstantUntils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : untils.InstantUntils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------  ");
    }
}

