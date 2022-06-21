package vn.mt.service;

import vn.mt.model.User;

import java.util.List;

public interface IUserService {
    User adminlogin(String username, String password);

    List<User> getUser();

    User getUserByID(int id);
}
