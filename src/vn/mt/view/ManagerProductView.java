package vn.mt.view;

import java.util.Scanner;

public class ManagerProductView {
    private static Scanner scanner = new Scanner(System.in);

    public static void create() {
        int num;
        boolean flag = true;
        try {
            do {
                ProductView productView = new ProductView();
                Menu.menuProduct();
                num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1:
                        productView.add();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        SearchMenu.searchMenu();
                        break;
                    case 4:
                        MenuSort.option();
                        break;
                    case 5:
                        productView.remove();
                        break;
                    case 6:
                        Menu.startMenu();
                        break;
                    case 0:
                        Menu.exit();
                        System.exit(0);
                    default:
                        System.out.println("Không hợp lệ, vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
