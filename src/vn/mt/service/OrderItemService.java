package vn.mt.service;

import vn.mt.model.OrderItem;
import vn.mt.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOrderItemService{
    public List<OrderItem> orderItems = new ArrayList<>();
    public static String path = "data/orderItem.csv";
    @Override
    public List<OrderItem> getOrderItem() {
        List<OrderItem> newOrderItems = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newOrderItems.add(new OrderItem(record));
        }
        return orderItems = newOrderItems;
    }

    @Override
    public OrderItem getOrderItemByID(int id) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderID() == id)
                return orderItem;
        }
        return null;
    }

    @Override
    public void add(OrderItem newOI) {
        newOI.setCreatedAt(Instant.now());
        orderItems.add(newOI);
        CSVUtils.write(path, orderItems);
    }

    @Override
    public void update(OrderItem newODI) {
        CSVUtils.write(path, orderItems);
    }

}
