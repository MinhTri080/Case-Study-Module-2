package vn.mt.view;

import vn.mt.model.User;
import vn.mt.service.UserSevice;

import java.util.Scanner;

public class UserView {
    static Scanner scanner = new Scanner(System.in);
    private UserSevice userService;

    public UserView() {
        userService = new UserSevice();
        loginUser();
    }

    public void loginUser() {
        Login threadLogin = new Login();
        Thread thread = new Thread(threadLogin);
        thread.start();
        try {
            thread.join();
        }catch (Exception e){
            System.out.println("  ");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                    ✤");
        System.out.println("✤        |ĐĂNG NHẬP HỆ THỐNG|        ✤");
        System.out.println("✤                                    ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");

        try {
            boolean flag = true;
            do {
                System.out.print("\tTên đăng nhập:\t");
                String username = scanner.nextLine();
                System.out.print("\tMật khẩu:\t");
                String password = scanner.nextLine();

                for (User user : userService.getUser()) {
                    if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                        Menu.startMenu();
                    } else {
                        System.out.println("Đăng nhập không thành công! Vui lòng nhập lại!");
                        flag = false;
                    }
                }
            } while (!flag);
        } catch (Exception ex) {
            System.out.println("Không hợp lệ, mời nhập lại");
        }
    }
}
