package View;

import Model.Product;
import services.IProductService;
import services.ProductService;
import untils.AppUntils;
import untils.InstantUntils;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public void add() {
        do {
            //    int id = inputId(InputOption.ADD);
            long id = System.currentTimeMillis() / 1000;
            String name = inputName(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            Product product = new Product(id, name, price, quantity);
            productService.add(product);
            System.out.println("Bạn đã thêm bánh thành công\n");

        } while (AppUntils.isRetry(InputOption.ADD));
    }

    public void update() {
        boolean isRetry;
        do {
            showProducts(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("┌ - - SỬA - - -  ┐");
            System.out.println("| 1.Sửa tên bánh |");
            System.out.println("| 2.Sửa số lượng |");
            System.out.println("| 3.Sửa giá bánh |");
            System.out.println("| 4.Quay lại MENU|");
            System.out.println("└ - - - - - - -  ┘");
            System.out.println("Chọn chức năng: ");
            int option = AppUntils.retryChoose(1, 4);
            Product newProduct = new Product();
            newProduct.setId(id);
            switch (option) {
                case 1:
                    String name = inputName(InputOption.UPDATE);
                    newProduct.setName(name);
                    productService.update(newProduct);
                    System.out.println("Tên bánh đã cập nhật thành công");
                    break;
                case 2:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Số lượng bánh đã cập nhật thành công");
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Bạn đã sửa giá bánh thành công");
                    break;
            }
            isRetry = option != 4 && AppUntils.isRetry(InputOption.UPDATE);
        }
        while (isRetry);
    }
    public void showProducts(InputOption inputOption) {
        System.out.println("-----------------------------------------DANH SÁCH BÁNH-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s \n", "Id", "Tên bánh", "Giá bánh", "Số lượng", "Ngày tạo", "Ngày cập nhật");
        for (Product product : productService.findAll()) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s \n",
                    product.getId(),
                    product.getName(),
                    AppUntils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUntils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUntils.instantToString(product.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUntils.isRetry(InputOption.SHOW);
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUntils.retryParseDouble();
            if (price <= 0)
                System.out.println("Giá phải lớn hơn 0 (giá > 0)");
        } while (price <= 0);
        return price;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUntils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Số lượng phải lớn hơn 0 (giá > 0)");
        } while (quantity <= 0);
        return quantity;
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
            case DELETE:
                System.out.println("Nhập id bạn cần xoá: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUntils.retryParseInt();
            boolean exist = productService.existsById(id);
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

    private String inputName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên bạn muốn sửa: ");
                break;
        }
        System.out.print("⭆ ");
        return scanner.nextLine();
    }

    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH BÁNH-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s \n", "Id", "Tên bánh", "Giá bánh", "Số lượng", "Ngày tạo", "Ngày cập nhật");
        for (Product product : products) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s \n",
                    product.getId(),
                    product.getName(),
                    AppUntils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUntils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUntils.instantToString(product.getUpdatedAt())
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUntils.isRetry(InputOption.SHOW);
    }
    public void remove() {
        showProducts(InputOption.DELETE);
        int id;
        while (!productService.exist(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa");
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

        System.out.println("┌---- REMOVE COMFIRM -----┐");
        System.out.println("|  1. Nhấn 1 để xoá       |");
        System.out.println("|  2. Nhấn 2 để quay lại  |");
        System.out.println("└-------------------------┘");
        int option = AppUntils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("Đã xoá sản phẩm thành công! \uD83C\uDF8A");
            AppUntils.isRetry(InputOption.DELETE);
        }

    }

    public void sortByPriceOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }

    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceDESC());
    }
}
