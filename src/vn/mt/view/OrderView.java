package vn.mt.view;

import vn.mt.model.Order;
import vn.mt.model.OrderItem;
import vn.mt.model.Product;
import vn.mt.service.*;
import vn.mt.utils.ConvertUtils;
import vn.mt.utils.InstantUtils;
import vn.mt.utils.ValidationUtils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OrderView {
    private IProductService productService;
    private IOrderService orderService;
    private OrderItemService orderItemService = new OrderItemService();
    Scanner scanner = new Scanner(System.in);
    DecimalFormat format = new DecimalFormat("###,###,###" + " VNĐ");


    public OrderView() {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }
    public OrderItem addOrderItems(long orderID) {
        orderItemService.getOrderItem();
        ProductView productView = new ProductView();
        int id;
        Random r = new Random();
        int low = 1;
        int high = 9999;
        do {
            id = r.nextInt(high - low) + low;
        } while (productService.exists(id));
        List<Product> productList = productService.getItem();
        productView.show(productList);
        System.out.printf("Nhập ID sản phẩm:  \n➨ \t");
        int productID = Integer.parseInt(scanner.nextLine());
        while (!productService.exists(productID)) {
            System.out.println("ID không tồn tại ");
            System.out.print("Nhập id sản phẩm  \n➨ \t");
            productID = Integer.parseInt(scanner.nextLine());
        }

        Product product = productService.getProductByID(productID);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.print("Nhập số lượng: \n➨\t ");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!checkQuantityProduct(product, quantity)) {
            System.out.println("Vượt quá số lượng! Mời nhập lại");
            System.out.println("Nhập số lượng: \n➨ \t");
            quantity = Integer.parseInt(scanner.nextLine());
        }

        String productName = product.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        productService.update(product);

        OrderItem orderItem = new OrderItem(id, orderID, productID, productName, price, quantity, total);
        return orderItem;
    }



    public boolean checkQuantityProduct(Product product, int quantity) {
        if (quantity <= product.getQuantity()) {
            return true;
        } else
            return false;
    }

    public void addOrder(String name,String phone,String address) {
        try {
            ArrayList<OrderItem> list = new ArrayList<>();
            orderService.getOrders();
            long orderID = System.currentTimeMillis() / 1000;
            System.out.println(orderID);
            OrderItem orderItem = addOrderItems(orderID);
            Order order = new Order(orderID, name, phone, address);
            orderItemService.add(orderItem);
            list.add(orderItem);
            orderService.add(order);
            System.out.println("Bạn đã tạo đơn hàng thành công!");
            boolean flag = true;
            do {
                Menu.inputOrder();
                String choice = scanner.nextLine();
                switch (choice) {
                    case "a":
                        addOrder(name,phone,address);
                        break;
                    case "o":
                        ManagerOrderView.start();
                        break;
                    case "s":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Nhập sai! mời nhập lại");
        }
    }

    public void enterInfoAdd(){
        System.out.println("Nhập họ và tên: (vd: Lươn Anh) ");
        System.out.print(" ➨ ");
        String name = scanner.nextLine();
        String namecheck;
        namecheck = ConvertUtils.removeAccent(name);
        while (!ValidationUtils.isNameValid(namecheck)) {
            System.out.println("Tên " + namecheck + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên: (Ví dụ: Lượng Lươn Lẹo) ");
            System.out.print(" ➨ ");
            namecheck = scanner.nextLine();
        }

        System.out.print("Nhập số điện thoại: \n ➨ \t ");
        String phone = scanner.nextLine();
        while (!ValidationUtils.isPhoneValid(phone)) {
            System.out.print("Số " + phone + " chưa hợp lệ . Mời nhập lại (Số điện thoại bắt đầu bằng số 0) \n " +
                    " \t (SĐT phải đúng 10 số) (vd: 0833445566) \n ➨ \t");
            phone = scanner.nextLine();
        }
        while (orderService.existByPhone(phone)) {
            System.out.println("Số này đã tồn tại, xin vui lòng nhập lại!");
            phone = scanner.nextLine();
        }


        System.out.print("Nhập địa chỉ: \n ➨ \t");
        String address = scanner.nextLine();
//        while (!ValidationUtils.isAddressValid(address)) {
//            System.out.println("Địa chỉ " + address + " chưa hợp lệ. Mời nhập lại (Địa chỉ bắt đầu bằng số) \n " +
//                    "\t (vd: 110 Nguyễn Lộ Trạch");
//            address = scanner.nextLine();
//        }
        addOrder(name,phone,address);
}


    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃" +
                    " Tổng Thiệt Hại ❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃");
            System.out.println("");
            System.out.printf("%-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    "Tên",
                    "Địa chỉ",
                    "OrderID",
                    "ProductID",
                    "Tên Sản phẩm",
                    "Giá",
                    "Số lượng",
                    "Tổng");
            System.out.printf("%-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    order.getName(),
                    order.getAddress(),
                    order.getOrderID(),
                    orderItem.getProductID(),
                    orderItem.getProductName(),
                    format.format(orderItem.getPrice()),
                    orderItem.getQuantity(),
                    format.format(orderItem.getTotal()));
            System.out.print("Ngày tạo đơn: " + order.getCreatedAt());
            System.out.println("");
//           for (OrderItem orderItem1 : orderItemService.orderItems ) {
//               if (order.getName() == order.getName() && order.getAddress() == order.getAddress() && order.getOrderID() == order.getOrderID()) {
//                   System.out.printf("%-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
//                           order.getName(),
//                           order.getAddress(),
//                           orderItem.getOrderID(),
//                           orderItem1.getProductID(),
//                           orderItem1.getProductName(),
//                           format.format(orderItem1.getPrice()),
//                           orderItem1.getQuantity(),
//                           format.format(orderItem1.getTotal()));
//                   System.out.print("Ngày tạo đơn: " + orderItem.getCreatedAt());
//                   System.out.println("");
//                   break;
//               }
            System.out.println("❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃");
            boolean flag = true;
            do {
                System.out.print("Nhấn 'c' để quay lại \nNhấn 'e' để thoát chương trình \n➨\t");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "c":
                        ManagerOrderView.start();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }


    public void showAllOrder() {
        List<Order> orderList = orderService.getOrders();
        List<OrderItem> orderItems = orderItemService.getOrderItem();
        OrderItem newOI = new OrderItem();
        List<OrderItem> orders = orderItemService.getOrderItem();
        double total = 0;
        for (OrderItem orderItem : orders) {
            total += orderItem.getTotal();
        }

        try {
            System.out.println("❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃" +
                    " DANH SÁCH ORDER ❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃");
            System.out.println("");
            System.out.printf("%-12s %-13s %-23s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    " Ngày tạo",
                    "   Tên",
                    "    Địa chỉ",
                    "  OrderID",
                    "ProductID",
                    "  Tên Sản phẩm",
                    "  Giá",
                    "  Số lượng",
                    "Tổng");
            for (Order order : orderList) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderID() == order.getOrderID()) {
                        newOI = orderItem;
                        break;
                    }
                }
                System.out.printf("%-12s %-13s %-23s %-15s %-12s %-25s %-20s %-10s %-20s\n",
                        InstantUtils.instantToFormat(order.getCreatedAt()),
                        order.getName(),
                        order.getAddress(),
                        order.getOrderID(),
                        newOI.getProductID(),
                        newOI.getProductName(),
                        format.format(newOI.getPrice()),
                        newOI.getQuantity(),
                        format.format(newOI.getTotal()));
            }
            System.out.println("");
            System.out.printf("%150s\n", "Tổng doanh thu: " + format.format(total));
            System.out.println("");
            System.out.println("❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃❃");
            boolean flag = true;
            do {
                System.out.print("\tNhấn 'b' để quay lại \n\tNhấn 'e' để thoát chương trình \n\t➨ \t");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        ManagerOrderView.start();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
