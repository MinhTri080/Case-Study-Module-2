package vn.mt.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public Menu() {
        startMenu();
    }

    public static void mainMenu() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                      MAIN MENU                       ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤                 1. Quản lí sản phẩm                  ✤");
        System.out.println("✤                 2. Quản lí đơn hàng                  ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.print("Chọn chức năng \n➨ \t");

    }

    public static void orderMenu() {
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                   QUẢN LÝ ĐƠN HÀNG                   ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤                   1. Tạo đơn hàng                    ✤");
        System.out.println("✤                   2. Xem danh sách                   ✤");
        System.out.println("✤                   0. Quay lại                        ✤");
        System.out.println("✤                                                      ✤");
        System.out.println("✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤✤");
    }

    public static void menuProduct() {

        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println("✷                  QUẢN LÝ SẢN PHẨM                    ✷");
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println("✷                                                      ✷");
        System.out.println("✷                 1. Thêm sản phẩm                     ✷");
        System.out.println("✷                 2. Sửa thông tin sản phẩm            ✷");
        System.out.println("✷                 3. Tìm kiếm sản phẩm                 ✷");
        System.out.println("✷                 4. Hiển thị sản phẩm                 ✷");
        System.out.println("✷                 5. Xóa sản phẩm                      ✷");
        System.out.println("✷                 6. Quay về menu chính                ✷");
        System.out.println("✷                 0. Thoát chương trình                ✷");
        System.out.println("✷                                                      ✷");
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println();
        System.out.printf("Chọn chức năng  \n➨ \t");
    }

    public static void exit() {
        Exit exit = new Exit();
        Thread thread1 = new Thread(exit);
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\t\t Bạn vừa thoát khỏi chương trình");
        System.exit(0);
    }

    public  static void startMenu() {
        try {
            boolean flag = true;
            do {
                mainMenu();
                String num = scanner.nextLine();
                switch (num) {
                    case "1":
                        ManagerProductView.create();
                        break;
                    case "2":
                        ManagerOrderView.start();
                        break;
                    default:
                        System.out.println("Không hợp lệ, xin vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (InputMismatchException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void inputOrder() {
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println("✷                                               ✷");
        System.out.println("✷           1. Nhấn 'a' để tiếp tục tạo đơn     ✷");
        System.out.println("✷           2. Nhấn 'o' để quay lại             ✷");
        System.out.println("✷           3. Nhấn 's' để in hóa đơn           ✷");
        System.out.println("✷           4. Nhấn 'e' để thoát                ✷");
        System.out.println("✷                                               ✷");
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.print("➨ \t");
    }

    public static void inputUpdate() {
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println("✷        1. Cập nhật giá                ✷");
        System.out.println("✷        2. Cập nhật số lượng           ✷");
        System.out.println("✷        0. Quay lại                    ✷");
        System.out.println("✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷✷");
        System.out.println("Chọn chức năng");
        System.out.printf("➨ \t");
    }


    public static void removeConfirm() {
        System.out.println("❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂");
        System.out.println("❂                  BẠN CHẮC CHẮN MUỐN XÓA            ❂");
        System.out.println("❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂");
        System.out.println("❂                                                    ❂");
        System.out.println("❂              1. Nhấn y để xác nhận xóa             ❂");
        System.out.println("❂              2. Nhấn c để quay lại                 ❂");
        System.out.println("❂                                                    ❂");
        System.out.println("❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂❂");
        System.out.printf("➨ \t");
    }
}
