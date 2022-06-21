package View;

import Model.Order;
import Model.OrderItem;
import Model.Product;
import services.*;
import untils.AppUntils;
import untils.InstantUntils;
import untils.ValidateUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final IProductService productService;
    private final IOrderService orderService;
    private final IOrderItemService oderItemService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        productService = ProductService.getInstance();
        orderService = OrderService.getInstance();
        oderItemService = OrderItemService.getInstance();
    }

    public OrderItem addOrderItems(long orderId) {
        oderItemService.findAll();
        ProductView productView = new ProductView();
        productView.showProducts(InputOption.ADD);
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập id bánh: ");
        System.out.print(" ⭆ ");
        int bakeryId = scanner.nextInt();
        scanner.nextLine();
        while (!productService.existsById(bakeryId)) {
            System.out.println("Id bánh không tồn tại");
            System.out.println("Nhập id bánh: ");
            System.out.print(" ⭆ ");
            bakeryId = scanner.nextInt();
        }
        Product product = productService.findById(bakeryId);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng");
        System.out.print(" ⭆ ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        while (!checkQualityBakery(product, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print(" ⭆ ");
            quantity = scanner.nextInt();
        }
        String bakeryName = product.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, bakeryId, bakeryName, total);
        return orderItem;
    }

    public boolean checkQualityBakery(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            orderService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên (vd: Luong Luon Leo) ");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Luon Leo) ");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name);
            order.setCreatedAt(Instant.now());
            order.setTotal(orderItem.getQuantity()*orderItem.getPrice());
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
                System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
                System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
                System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        AppUntils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai. vui lòng nhập lại!");
            e.printStackTrace();
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s\n|", "   Id", "Tên khách hàng", "Tên bánh", "Số lượng", "Giá");
            System.out.printf("|%-15d %-20s %-15s %-15d %-15f \n|", order.getId(), order.getFullname(), orderItem.getProductName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println(" ------------------------------------------------------------------------------------------------- Tổng tiền:" + order.getTotal());
            System.out.println("---------------------------------------------Bán Cho Vui-----------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUntils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = oderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------LIST ORDER--------------------------------------------------------------------");
            System.out.println("|                                                                                                                                      |");
            System.out.printf("|%-15s %-20s %-10s %-10s %-15s %-21s %-10s\n|", "   Id", "Tên khách hàng", "Tên bánh", "Số lượng", "   Giá", "   Tổng" , "  Thời Gian             |");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-10s %-10d %-15f %-21f %-10s %-10s\n|",
                        order.getId(),
                        order.getFullname(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        newOrderItem.getPrice(),
                        newOrderItem.getPrice() * newOrderItem.getQuantity(),
                        InstantUntils.instantToString(order.getCreatedAt()),
                        "|");
            }
            System.out.println("                                                                                                                                      |");
            System.out.println("---------------------------------------------Bán Cho Vui----------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUntils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}