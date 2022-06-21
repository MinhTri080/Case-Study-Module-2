package View;

import untils.AppUntils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuProduct {
//    public static void main(String[] args) {
//        MenuProduct menuProduct = new MenuProduct();
//        menuProduct.run();
//    }
    public static void run() {
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
            menuBakery();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("⭆ ");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        productView.add();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        productView.remove();
                        break;
                    case 4:
                        productView.showProducts(InputOption.SHOW);
                        break;
                    case 5:
                        productView.sortByPriceOrderByASC();
                        break;
                    case 6:
                        AppUntils.exit();
                        break;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        run();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void menuBakery() {
        System.out.println();
        System.out.println("┌---------------------MENU BAKERY-----------------------┐");
        System.out.println("|                                                       |");
        System.out.println("|   ☛ 1. Thêm bánh                                      |");
        System.out.println("|   ☛ 2. Sửa thông tin bánh                             |");
        System.out.println("|   ☛ 3. Xoá bánh                                       |");
        System.out.println("|   ☛ 4. Hiển thị tất cả bánh                           |");
        System.out.println("|   ☛ 5. Hiển thị tất cả bánh theo giá tăng dần         |");
        System.out.println("|   ☛ 6. Thoát                                          |");
        System.out.println("|                                                       |");
        System.out.println("└-------------------------------------------------------┘");
    }
}
