package services;

import Model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User adminLogin(String username, String password);

    void add(User newUser);

    void update(User newUser);

    boolean existById(int id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String userName);

    User findById(int id);
    void removeID(int id);


    void sortByName(List<User> users);
}
