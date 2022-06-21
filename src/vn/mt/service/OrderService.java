package vn.mt.service;

import vn.mt.model.Order;
import vn.mt.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    List<Order> orders = new ArrayList<>();
    public static String path = "data/order.csv";

    //    private static OrderItemService orderItemService = new OrderItemService();
    @Override
    public List<Order> getOrders() {
        List<Order> newOrders = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrders.add(new Order(record));
        }
        return orders = newOrders;
    }


    @Override
    public Order getOrderByID(long id) {
        List<Order> orderList = getOrders();
        for (Order order : orderList) {
            if (order.getOrderID() == id) {
                return order;
            }
        }
        return null;
    }


    @Override
    public void add(Order newOrder) {
        newOrder.setCreatedAt(Instant.now());
        orders.add(newOrder);
        CSVUtils.write(path, orders);
    }


    @Override
    public void remove(Order order) {
        orders.remove(order);
        update();
    }

    @Override
    public void update() {
        List<Order> orderList = getOrders();
        CSVUtils.write(path, orders);
    }

    @Override
    public boolean existByPhone(String phone) {
        for (Order order : orders) {
            if (order.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateFullName(String fullName) {
        for (Order order : orders) {
            if (order.getName().equals(fullName))
                return true;
        }
        return false;
    }

    @Override
    public boolean exists(long id) {
        return getOrderByID(id) != null;
    }
}
