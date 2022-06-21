package View;

import Model.Role;
import Model.User;
import services.IUserService;
import services.UserSevice;
import untils.AppUntils;
import untils.InstantUntils;
import untils.ValidateUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = UserSevice.getInstance();
    }

    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail(InputOption.ADD);
                User user = new User(id, username, password, fullName, phone, address, email, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm thành công!\uD83C\uDF8A");
            } catch (Exception e) {
                System.out.println("Nhập sai. vui lòng nhập lại!");
            }
        } while (AppUntils.isRetry(InputOption.ADD));
    }


    public void setRole(User user) {
        System.out.println("┌---  SET ROLE ---┐");
        System.out.println("|   1. USER       |");
        System.out.println("|   2. ADMIN      |");
        System.out.println("└-----------------┘");
        System.out.println("Chọn Role: ");
        System.out.print(" ⭆ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                setRole(user);
        }
    }

    public void showUsers(InputOption inputOption) {
        System.out.println("----------------------------------------- DANH SÁCH NGƯỜI DÙNG--------------------------------------- ");
        System.out.printf("%-15s %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n",
                    user.getId(),
                    user.getName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getRole(),
                    InstantUntils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : InstantUntils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("-----------------------------------------------------------------------------------------------------  ");
        if (inputOption == InputOption.SHOW) AppUntils.isRetry(InputOption.SHOW);
    }

    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(InputOption.UPDATE);
                int id = inputId(InputOption.UPDATE);
                System.out.println("┌-----------------SỬA -----------------┐");
                System.out.println("|  1. Sửa  tên người dùng              |");
                System.out.println("|  2. Sửa số điện thoại                |");
                System.out.println("|  3. Sửa địa chỉ                      |");
                System.out.println("|  4. Sửa email                        |");
                System.out.println("|  5. Quay lại                         |");
                System.out.println("└------------------------------------- ┘");

                int option = AppUntils.retryChoose(1, 5);
                User newUser = new User();
                newUser.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullName(InputOption.UPDATE);
                        newUser.setName(name);
                        userService.update(newUser);
                        System.out.println(" Đổi tên thành công!\uD83C\uDF89");
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Đổi số điện thoại thành công\uD83C\uDF89");
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Đổi địa chỉ thành công\uD83C\uDF89");
                        break;
                    case 4:
                        String email = inputEmail(InputOption.UPDATE);
                        newUser.setEmail(email);
                        userService.update(newUser);
                        System.out.println("Đổi email thành công\uD83C\uDF89 ");
                        break;
                    case 5:
                        break;
                }
                isRetry = option != 5 && AppUntils.isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (isRetry);
    }


    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id");
                break;
            case UPDATE:
                System.out.println("Nhập id bạn muốn sửa");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUntils.retryParseInt();
            boolean exist = userService.existById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại. Vui lòng nhập id khác!");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy id! Vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    public String inputUsername() {
        System.out.println("Nhập Username (không bao gồm dấu cách, kí tự đặc biệt)");
        System.out.print(" ⭆ ");
        String username;

        do {
            if (!ValidateUtils.isUsernameValid(username = AppUntils.retryString("Username"))) {
                System.out.println(username + " của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByUsername(username)) {
                System.out.println("Username này đã tồn tại. Vui lòng nhập lại");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return username;
    }

    private String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập họ và tên (vd:Le Minh Tri) ");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa đổi");
                break;
        }

        System.out.print(" ⭆ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
            System.out.println("Tên " + fullName + "không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
            System.out.println("Nhập tên (vd: Minh Tri) ");
            System.out.print(" ⭆ ");
        }
        return fullName;
    }

    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số điện thoại (vd: 0833430807): ");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại mà bạn muốn đổi");
                break;
        }
        System.out.print(" ⭆ ");
        String phone;
        do {
            phone = scanner.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0833430807)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByPhone(phone)) {
                System.out.println("Số này đã tồn tại! Mời bạn nhập lại");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);

        return phone;
    }

    private String inputEmail(InputOption option) {
        System.out.println("Nhập email (vd: abcd@gmail.com)");
        System.out.print(" ⭆ ");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
                System.out.println("Email " + email + "của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại ");
                System.out.println("Nhập email (vd: abcd@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByEmail(email)) {
                System.out.println("Email " + email + "của bạn đã tồn tại! vui lòng kiểm tra lại");
                System.out.println("Nhập email (vd: abcd@gmail.com)");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return email;
    }

    private String inputPassword() {
        System.out.println("Nhập mật khẩu( mật khẩu phải > 8 kí tự )");
        System.out.print(" ⭆ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("Mật khẩu yếu! Vui lòng nhập lại ");
            System.out.print(" ⭆ ");
        }
        return password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ (vd: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ mà bạn muốn đổi");
                break;
        }
        System.out.print(" ⭆ ");
        return scanner.nextLine();
    }

    public void removeID() {
        showUsers(InputOption.DELETE);
        System.out.println("Nhập id cần xóa :");
        int id;
        while (!userService.existById(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm id cần xóa");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUntils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        }

        System.out.println("┌-------REMOVE COMFIRM -----┐");
        System.out.println("|  ☛ 1. Nhấn 1 để xoá       |");
        System.out.println("|  ☛ 2. Nhấn 2 để quay lại  |");
        System.out.println("└---------------------------┘");
        int option = AppUntils.retryChoose(1, 2);
        if (option == 1) {
            userService.removeID(id);
            System.out.println("Đã xoá thằng mặt nồi thành công! \uD83C\uDF8A");
            AppUntils.isRetry(InputOption.DELETE);
        }

    }
//    public void sortByName() {
//        showUsers(InputOption.SHOW);
//        List<User> users = userService.findAll();
//        users.sort(new Comparator<User>() {
//            @Override
//
//            public int compare(User o1,User o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//        System.out.println("----------------------------------------- DANH SÁCH NGƯỜI DÙNG--------------------------------------- ");
//        System.out.printf("%-15s %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
//        for (User user : users
//        ) {
//            System.out.printf("%-15d %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n",
//                    user.getId(),
//                    user.getName(),
//                    user.getPhone(),
//                    user.getAddress(),
//                    user.getEmail(),
//                    user.getRole(),
//                    InstantUntils.instantToString(user.getCreatedAt()),
//                    user.getUpdatedAt() == null ? "" : InstantUntils.instantToString(user.getUpdatedAt())
//            );
//        }
//        System.out.println("-----------------------------------------------------------------------------------------------------  ");
//    }
}

