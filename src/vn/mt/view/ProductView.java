package vn.mt.view;

import vn.mt.model.Product;
import vn.mt.service.ProductService;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductView {
    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);
    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " vnđ");

    public void add() {
        int id;
        Random r = new Random();
        int low = 1;
        int high = 9999;
        do {
            id = r.nextInt(high - low) + low;
        } while (productService.exists(id));
        System.out.print("Nhập tên sản phẩm\n➨ \t");
        String nameProduct = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm");
        System.out.printf("➨ \t ");
        double price;
        do {
            price = Double.parseDouble(scanner.nextLine());
            if (!(price > 50000 && price < 1000000)) {
                System.out.print("Nhập sai giá!!! Lưu ý: \n Giá của sản phẩm phải nằm trong khoảng từ 50.000 đến 1.000.000\n ➨ \t");
                price = Double.parseDouble(scanner.nextLine());
            }
        } while (!(price > 50000 && price < 1000000));

        System.out.print("Nhập số lượng: ");
        int quantity;
        do {
            quantity = Integer.parseInt(scanner.nextLine());
            if (!(quantity > 0 && quantity <= 100)) {
                System.out.println("Số lượng không được nhỏ hơn 0 và lớn hơn 100");
                System.out.print("➨ \t ");
            }
        } while (!(quantity > 0 && quantity <= 100));

        Product product = new Product(id, nameProduct, price, quantity);
        productService.addItem(product);
        System.out.println("Sản phẩm đã được thêm thành công!");
        show(productService.getItem());


        boolean flag = true;
        do {
            System.out.printf(" Nhấn 'a' để thêm sản phẩm \n Nhấn 'b' để quay lại \n Nhấn 'e' để thoát \n");
            System.out.print("➨ \t ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "a":
                    add();
                    break;
                case "b":
                    ManagerProductView.create();
                    break;
                case "e":
                    Menu.exit();
                    break;
                default:
                    System.out.println("Xin vui lòng nhập lại!");
                    flag = false;
            }
        } while (!flag);
    }


    public void update() {
        show(productService.getItem());
        System.out.print("Nhập ID cần sửa\n➨ \t ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (productService.exists(id)) {
                Menu.inputUpdate();
                boolean is = true;
                do {
                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case 1:
                                inputPrice(id);
                                break;
                            case 2:
                                inputQuantity(id);
                                break;
                            case 3:
                                ManagerProductView.create();
                                break;
                            default:
                                System.out.print("Chưa hợp lệ!! Mời Nhập Lại\n");
                                is = false;
                        }
                    } catch (Exception e) {
                        update();
                    }
                } while (!is);
                boolean flag = true;
                do {
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật \nNhấn 'b' để quay lại \nNhấn 'e' để thoát... \n=> \t");
                    String chon = scanner.nextLine();
                    switch (chon) {
                        case "c":
                            update();
                            break;
                        case "b":
                            ManagerProductView.create();
                            break;
                        case "e":
                            Menu.exit();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Mời Nhập Lại");
                            flag = false;
                    }
                } while (!flag);
            } else {
                System.out.println("Mời Nhập Lại");
                update();
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void show(List<Product> productList) {

        System.out.println("✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽" + " DANH SÁCH SẢN PHẨM ✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽" + "✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽");
        System.out.println("");
        System.out.printf("%-10s %-30s %-20s %-10s", "ID", "Tên Sản Phẩm", "Giá: ", "Số lượng");
        System.out.println(" ");
        for (Product product : productList) {
            System.out.printf("%-10s %-30s %-20s %-10s\n", product.getProductID(), product.getName(), decimalFormat.format(product.getPrice()), product.getQuantity());
        }
        System.out.println("");
        System.out.println("✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽✽\n");
    }


    public void showProduct() {
        show(productService.getItem());
        boolean flag = true;
        do {
            System.out.println("✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿");
            System.out.println("✿            Nhấn 'c' để trở lại                       ✿");
            System.out.println("✿            Nhấn 'e' để thoát chương trình            ✿");
            System.out.println("✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿");
            System.out.printf(" ➨ \t");
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "c":
                        ManagerProductView.create();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");
                        flag = false;
                }
            } catch (Exception e) {
                System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
            }
        } while (!flag);
    }

    public void inputPrice(int id) {
        Product product = productService.getProductByID(id);
        System.out.printf("Nhập giá sản phẩm: \n➨ \t");
        double price = Double.parseDouble(scanner.nextLine());
        product.setPrice(price);
        productService.update(product);
        show(productService.getItem());
        System.out.println("Cập nhật thành công!");
    }

    public void inputQuantity(int id) {
        Product product = productService.getProductByID(id);
        System.out.print("Nhập số lượng: \n➨ \t");
        int quantity = Integer.parseInt(scanner.nextLine());
        product.setQuantity(quantity);
        productService.update(product);
        show(productService.getItem());
        System.out.println("Cập nhật thành công!");
    }


    public void remove() {
        List<Product> productList = productService.getItem();
        show(productList);
        System.out.printf("Nhập ID sản phẩm \n➨ \t");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productService.getProductByID(id);
        if (product != null) {
            boolean check = true;
            Menu.removeConfirm();
            String chon = scanner.nextLine();
            try {
                switch (chon) {
                    case "y":
                        productService.remove(product.getProductID());
                        System.out.println("Xóa thành công sản phẩm");
                        show(productService.getItem());
                        do {
                            System.out.println("✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿");
                            System.out.println("✿       Nhấn '1' để quay lại       ✿");
                            System.out.println("✿       Nhấn '2' để thoát          ✿");
                            System.out.println("✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿");
                            System.out.printf("➨ \t");
                            byte choice = Byte.parseByte(scanner.nextLine());
                            switch (choice) {
                                case 1:
                                    ManagerProductView.create();
                                    break;
                                case 2:
                                    Menu.exit();
                                    break;
                                default:
                                    System.out.println("\t Nhập lại!!! ");
                                    check = false;
                            }
                        } while (!check);
                        break;
                    case "c":
                        ManagerProductView.create();
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
            }
        }
    }
}
