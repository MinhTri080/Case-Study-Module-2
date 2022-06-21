package View;

import java.util.Scanner;

public class OrderViewLauncher {
    public static void run() {
        MainLauncher.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OrderView orderView = new OrderView();
        System.out.println("\nChọn chức năng");
        System.out.print("⭆ ");
        int choices = scanner.nextInt();
        switch (choices) {
            case 1:
                orderView.addOder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            default:
                System.out.println("Chọn sai! vui lòng nhập lại");
                run();

        }
    }
}
