package View;

import Model.Role;
import Model.User;
import services.IUserService;
import services.UserSevice;
import untils.AppUntils;
import untils.ValidateUtils;

import java.util.Scanner;

public class UserViewLauncher extends UserView {
    public static void main(String[] args) {
        UserViewLauncher userViewLauncher = new UserViewLauncher();
        userViewLauncher.launch();
    }
    public static void launch() {
        Scanner scanner = new Scanner(System.in);

        UserView userView = new UserView();
        int option = 1;
        do {
            menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng :");
                    System.out.print(" ⭆ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 6 || option < 1) {
                        System.out.println("Chọn chức năng ko đúng,mời nhập lại : ");
                    }

                } while (option > 6 || option < 1);

                switch (option) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.showUsers(InputOption.SHOW);
                    case 4:
                        UserSevice user = new UserSevice();
                        user.sortByName(user.findAll());
                        break;
                    case 5:
                        userView.removeID();
                        break;
                    case 6:
                        menuUser();
                        break;
                    default:
                        System.out.println("Chọn chức năng ko đúng,mời chọn lại : ");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai,mời nhập lại");
            }
        } while (option != 5);
    }

    public static void menuUser() {
        System.out.println("┌-----------  USERS MANAGER  ----------┐");
        System.out.println("|                                      |");
        System.out.println("|   ☛ 1. Thêm người dùng               |");
        System.out.println("|   ☛ 2. Sửa thông tin người dùng      |");
        System.out.println("|   ☛ 3. Hiện danh sách người dùng     |");
        System.out.println("|   ☛ 4. Sắp xếp người dùng            |");
        System.out.println("|   ☛ 5. Xóa người dùng                |");
        System.out.println("|   ☛ 6. Quay lại                      |");
        System.out.println("|                                      |");
        System.out.println("└--------------------------------------┘");
    }

}
